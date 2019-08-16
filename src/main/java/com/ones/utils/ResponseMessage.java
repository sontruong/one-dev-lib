/**
 * 
 */
package com.ones.utils;

import org.springframework.http.HttpStatus;

/**
 * @author sontruong
 *
 */
public class ResponseMessage {
	String message;
	private HttpStatus httpStatus;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
