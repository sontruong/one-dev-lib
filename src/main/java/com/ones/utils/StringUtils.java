/**
 * 
 */
package com.ones.utils;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.ones.exception.ContentException;

/**
 * @author son.truong Apr 13, 2017 11:03:56 PM
 */
public class StringUtils {
	public static Boolean isNotEmpty(String string) {
		if (null == string || string.isEmpty()) {
			return false;
		}
		return true;
	}

	public static Boolean isEmpty(String string) {
		if (null == string || string.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void isEmpty(String string, String msg) {
		if (null == string || string.isEmpty()) {
			throw new ContentException(msg);
		}
	}

	public static void isValidEmail(String str, String msg) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return;
		}
		throw new ContentException(msg);
	}

	public static void isMatchLength(String str, int min, int max, String msg) {
		if (isNotEmpty(str)) {
			if (str.length() >= min && str.length() <= max) {
				return;
			}
		}

		throw new ContentException(msg);
	}

	public static void stringEqual(String str1, String str2, String msg) {
		if (str1.equals(str2)) {
			return;
		}
		throw new ContentException(msg);
	}

	public static String getString(String str) {
		if (null == str) {
			return "";
		}
		return str;
	}

	public static String getString(Date date) {
		if (null == date) {
			return "";
		}
		return date.toString();
	}

	public static String getAfterLastSplash(String fileURL) {
		if (null == fileURL || fileURL.contains("/")) {
			return "";
		}
		String txtResult = fileURL.substring(fileURL.lastIndexOf("/") + 1);
		return txtResult;
	}

	public static String generateBasicAuthentication(String clientId, String clientSecret) {
		String auth = clientId + ":" + clientSecret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		return authHeader;
	}

	public static String selectFirstValue(String... strs) {
		if (null == strs || 0 == strs.length) {
			return null;
		}
		for (String str : strs) {
			if (isNotEmpty(str)) {
				return str;
			}
		}
		return null;
	}
	
	public static String fixStringLength(Long num, int max) {
		String result = "" + num;
		if (result.length() > max) {
			result = result.substring(0, max);
		}
		if (result.length() < max) {
			while (result.length() < max) {
				result = "0" + result;
			}
		}

		return result;
	}
	
	public static String fixStringLength(int num, int max) {
		String result = "" + num;
		if (result.length() > max) {
			result = result.substring(0, max);
		}
		if (result.length() < max) {
			while (result.length() < max) {
				result = "0" + result;
			}
		}

		return result;
	}
	
	public static String getDatePath() {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		result += calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.getTimeInMillis(); 
		return result;
	}
}
