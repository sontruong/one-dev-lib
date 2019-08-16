package com.ones.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import com.ones.type.AttachmentType;

public abstract class CDNServiceAbs implements CDNService {

	@Value("${default.service-home}")
	protected String serviceHome;

	@Override
	public String initRoot(String apiPath) {
		String url = serviceHome + "/" + apiPath + "?res=";
		return url;
	}

	@Override
	public String getPath(String username, AttachmentType attachmentType) {
		Calendar date = Calendar.getInstance();
		String uploadDirPath = "/resource/" + attachmentType + "/" + date.get(Calendar.YEAR) + "/"
				+ date.get(Calendar.MONTH) + date.get(Calendar.DATE) + "/" + username;
		return uploadDirPath;
	}

	@Override
	public String createNewFileName(String fileName) {
		int dot = fileName.lastIndexOf(".");
		String uploadFileName = fileName.substring(0, dot) + "_" + new Date().getTime() + fileName.substring(dot);
		return uploadFileName;
	}
	
	@Override
	public void downloadFile(HttpServletResponse response, File file, String contentType) throws Exception {
		InputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] fileBytes = IOUtils.toByteArray(bis);
			response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
			response.setContentType(contentType);
			response.getOutputStream().write(fileBytes);
			response.getOutputStream().flush();
		} catch (Exception e) {
			throw e;
		}
		finally {
			if(null != bis) {
				bis.close();
			}
		}
	}
}
