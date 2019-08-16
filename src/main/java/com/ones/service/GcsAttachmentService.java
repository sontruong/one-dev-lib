package com.ones.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ones.exception.NotFoundException;
import com.ones.type.AttachmentType;

public interface GcsAttachmentService {
	String addFile(String token, MultipartFile file, String uploadPath, String username) throws IOException;
	String copyFile(String src, String des) throws IOException;
	
	InputStream downloadFile(String fileIdentifier) throws IOException, NotFoundException;

	void deleteFile(AttachmentType attachmentType, Long entityId, String fileIdentifier) throws IOException;

	List<String> getFileListForEntityCode(AttachmentType attachmentType, Long entityId, String entityCode)
			throws IOException;
}
