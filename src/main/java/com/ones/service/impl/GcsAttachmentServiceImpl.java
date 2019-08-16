package com.ones.service.impl;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.Storage.Objects.Copy;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.StorageObject;
import com.google.common.collect.ImmutableMap;
import com.ones.exception.GcsInitialisationException;
import com.ones.exception.NotFoundException;
import com.ones.service.GcsAttachmentService;
import com.ones.type.AttachmentType;
import com.ones.utils.AppConfig;
import com.ones.utils.ApplicationMessage;

@Service
public class GcsAttachmentServiceImpl implements GcsAttachmentService {

	private static final Logger LOG = LoggerFactory.getLogger(GcsAttachmentServiceImpl.class);

	public static final String ATTR_FILENAME = "user.filename";
	public static final String ATTR_UPDATED_BY = "user.updatedBy";
	public static final String ATTR_MODIFIED_ON = "user.modifiedOn";
	public static final String PRIVATE_KEY_SECTION = "PRIVATE KEY";

	@Value("${cdn.gcs.serviceAccountEmail}")
	private String serviceAccountEmail;

	@Value("${cdn.gcs.serviceAccountPrivateKey}")
	private String serviceAccountPrivateKey;

	@Value("${cdn.gcs.bucket}")
	private String bucket;

	@Value("${cdn.gcs.appName}")
	private String appName;

	@Value("${cdn.gcs.rootPath}")
	private String rootPath;

	@Autowired
	ResourcePatternResolver resourcePatternResolver;

	private Storage storageClient;

	@Override
	public String addFile(String token, MultipartFile file, String uploadPath, String username) throws IOException {
		LOG.info("Uploading new file {} for entity {}, name {}", file, uploadPath);

		String identifier = UUID.randomUUID().toString();

		insertIntoBucket(token, file.getInputStream(), file.getOriginalFilename(), uploadPath, identifier, username);
		return identifier;
	}

	@Override
	public String copyFile(String src, String des) throws IOException {
		initClient();
		Storage.Objects.Get getRequest = storageClient.objects().get(bucket, src);
		Copy copy = storageClient.objects().copy(bucket, src, bucket, des, getRequest.execute());
		return copy.getDestinationObject();
	}
	
	@Override
	public InputStream downloadFile(String fileIdentifier) throws IOException, NotFoundException {
		try {
			String uri = buildUri(fileIdentifier);
			LOG.info("Downloading uri {}", uri);
			initClient();
			Storage.Objects.Get getRequest = storageClient.objects().get(bucket, uri);
			InputStream inputStream = getRequest.executeMediaAsInputStream();
			return inputStream;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new NotFoundException(ApplicationMessage.ATTACHMENT_NOT_FOUND);
		}
	}

	@Override
	public void deleteFile(AttachmentType AttachmentType, Long entityId, String fileIdentifier) throws IOException {
		String entityCode = AttachmentType.name() + entityId;
		LOG.info("Deleting file for entity {}, name {}", entityCode, fileIdentifier);
		initClient();
		String uri = buildUri(entityCode, fileIdentifier);
		storageClient.objects().delete(bucket, uri).execute();
	}

	@Override
	public List<String> getFileListForEntityCode(AttachmentType attachmentType, Long entityId, String entityCode)
			throws IOException {
		LOG.info("Listing files for entity {}", entityCode);

		List<StorageObject> items = storageClient.objects().list(bucket).setPrefix(buildUri(entityCode)).execute()
				.getItems();

		if (items != null) {
			return items.stream()
					.map(storageObject -> mapStorageToAttachment(storageObject))
					.collect(toList());
		} else {
			return emptyList();
		}
	}

	/*
	 * private long getModifiedOn(AttachmentDTO fileAttributes) { return
	 * fileAttributes.getUploadDate() != null ?
	 * fileAttributes.getUploadDate().toInstant(ZoneOffset.UTC).toEpochMilli() :
	 * System.currentTimeMillis(); }
	 */

	private String buildUri(String... paths) {
		UriComponents builder = UriComponentsBuilder.fromPath(rootPath).pathSegment(paths).build();
		return builder.toUriString().replaceFirst("^/", "");
	}

	private String mapStorageToAttachment(StorageObject storageObject) {
		UriComponents builder = UriComponentsBuilder.fromUriString(storageObject.getName()).build();
		List<String> pathSegments = builder.getPathSegments();
		if (!pathSegments.isEmpty()) {
			String identifier = pathSegments.get(pathSegments.size() - 1);
			return identifier;
		}

		return "";
	}

	private void insertIntoBucket(String token, InputStream file, String fileName, String uploadPath, String identifier, String username)
			throws IOException {
		StorageObject object = new StorageObject();
		object.setContentType(AppConfig.APPLICATION_CONTENT_TYPES);
		object.setName(buildUri(uploadPath, identifier));
		object.setMetadata(ImmutableMap.of(ATTR_FILENAME, fileName, ATTR_UPDATED_BY,
				username, ATTR_MODIFIED_ON, new Date().toString()));

		initClient();
		storageClient.objects().insert(bucket, object, new InputStreamContent(object.getContentType(), file)).execute();
	}

	private void initClient() {
		try {
			if (null == storageClient) {
				final NetHttpTransport transport = createTransport();
				JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
				storageClient = new Storage.Builder(transport, jsonFactory, authorize(transport, jsonFactory))
						.setApplicationName(appName).build();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private NetHttpTransport createTransport() {
		try {
			return GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException | IOException e) {
			throw new GcsInitialisationException("Failed to initialise Google Cloud Engine Storage", e);
		}
	}

	private Credential authorize(NetHttpTransport transport, JsonFactory jsonFactory)
			throws GeneralSecurityException, IOException, IllegalArgumentException {
		List<String> scopes = new ArrayList<String>();
		scopes.add(StorageScopes.CLOUD_PLATFORM);
		scopes.add(StorageScopes.DEVSTORAGE_FULL_CONTROL);
		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		byte[] data = Base64.getDecoder().decode(serviceAccountPrivateKey);

		InputStream inputStream = new ByteArrayInputStream(data);
		File key = File.createTempFile("weddy", ".p12");
		try {
			FileUtils.copyInputStreamToFile(inputStream, key);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY).setServiceAccountId(serviceAccountEmail)
				.setServiceAccountPrivateKeyFromP12File(key).setServiceAccountScopes(scopes).build();
		return credential;
	}
}
