/**
 * 
 */
package com.ones.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ones.exception.NotFoundException;
import com.ones.service.CDNServiceAbs;
import com.ones.utils.AppConfig;
import com.ones.utils.ApplicationMessage;
import com.ones.utils.FileUtils;

/**
 * @author son.truong
 *
 *         Oct 28, 2015
 */
@Service
public class CDNLocalService extends CDNServiceAbs {

	@Value("${cdn.local.root}")
	protected String cdnRoot;

	BufferedOutputStream bos = null;

	@Override
	public String uploadFile(String token, final MultipartFile file, String uploadDirPath, boolean cleanFolder, String username) {
		if (file.isEmpty()) {
			return null;
		}

		try {
			File uploadDir = new File(cdnRoot + uploadDirPath);
			if (uploadDir.exists()) {
				if (cleanFolder) {
					org.apache.commons.io.FileUtils.cleanDirectory(new File(uploadDirPath));
				}
			} else {
				uploadDir.mkdirs();
			}

			String uploadFileName = createNewFileName(file.getOriginalFilename());
			byte[] fileContent = file.getBytes();
			File f = new File(uploadDir, uploadFileName);
			bos = new BufferedOutputStream(new FileOutputStream(f, false));
			bos.write(fileContent);
			bos.flush();
			bos.close();
			return uploadDirPath + "/" + f.getName();
		} catch (IOException e) {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
				}
			}
			return null;
		}
	}

	@Override
	public void downloadFile(HttpServletResponse response, String filePath, String fileName) throws Exception {
		BufferedInputStream bis = null;

		try {
			File f = new File(cdnRoot + filePath);
			if (!f.exists()) {
				throw new NotFoundException(ApplicationMessage.ATTACHMENT_NOT_FOUND);
			}
			bis = new BufferedInputStream(new FileInputStream(f));
			byte[] imageBytes = IOUtils.toByteArray(bis);
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
			if (null != FileUtils.getMimeType(fileName)) {
				response.setContentType(FileUtils.getMimeType(fileName));
			} else {
				response.setContentType(AppConfig.APPLICATION_CONTENT_TYPES);
			}

			response.getOutputStream().write(imageBytes);
			response.getOutputStream().flush();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e1) {
					bis = null;
				}
			}
		}

	}

	@Override
	public String copyFile(String src, String des) throws IOException {
		File f = new File(cdnRoot + src);
		if (!f.exists()) {
			throw new NotFoundException(ApplicationMessage.ATTACHMENT_NOT_FOUND);
		}
		FileUtils.copyFile(cdnRoot, src, des);
		return null;
	}
}
