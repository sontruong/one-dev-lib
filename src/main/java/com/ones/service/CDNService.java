package com.ones.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.ones.type.AttachmentType;

public interface CDNService {
	
	String uploadFile(String token, final MultipartFile file, String uploadDirPath, boolean cleanFolder, String username);
	String copyFile(String src, String des) throws IOException;
	void downloadFile(HttpServletResponse response, String filePath, String fileName) throws Exception;
	String initRoot(String apiPath);
	String getPath(String username, AttachmentType attachmentType);
	String createNewFileName(String fileName);
	void downloadFile(HttpServletResponse response, File file, String contentType) throws Exception;
}
