/**
 * 
 */
package com.ones.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ones.service.CDNServiceAbs;
import com.ones.service.GcsAttachmentService;
import com.ones.utils.AppConfig;
import com.ones.utils.FileUtils;

/**
 * @author son.truong
 *
 *         Oct 28, 2015
 */
@Service
public class CDNGcsService extends CDNServiceAbs {

	BufferedOutputStream bos = null;
	
	@Autowired
	GcsAttachmentService gcsAttachmentService;

	@Override
	public String uploadFile(String token, final MultipartFile file, String uploadDirPath, boolean cleanFolder, String username) {
		if (file.isEmpty()) {
			return null;
		}
		try {
			return gcsAttachmentService.addFile(token, file, uploadDirPath, username);
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
			InputStream is = gcsAttachmentService.downloadFile(filePath);
			
			bis = new BufferedInputStream(is);
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
		}
		finally {
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
		return gcsAttachmentService.copyFile(src, des);
	}
}
