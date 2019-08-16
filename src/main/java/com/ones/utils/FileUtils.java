/**
 * 
 */
package com.ones.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLConnection;

import javax.activation.MimetypesFileTypeMap;

/**
 * @author Son Truong
 *
 */
public class FileUtils {
	public static String getMimeType(String url) {
		String type = null;
		try {
			type = URLConnection.guessContentTypeFromName(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == type) {
			try {
				MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
				type = mimeTypesMap.getContentType(url);
			} catch (Exception e) {
			}
		}

		return type;
	}

	public static void copyFile(String cdnRoot, String path, String output) {
//		File uploadDir = new File(cdnRoot + "/" + output);
//		if (!uploadDir.exists() | !uploadDir.isDirectory()) {
//			uploadDir.mkdirs();
//		}
		
		FileInputStream fis = null;
        FileOutputStream fos = null;
        try {

            fis = new FileInputStream(cdnRoot + "/" + path);
            fos = new FileOutputStream(cdnRoot + "/" + output);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Problems when copying file.");
        } finally {
            if (path != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                  System.out.println(e);
                }
            }
            if (output != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
	}
}
