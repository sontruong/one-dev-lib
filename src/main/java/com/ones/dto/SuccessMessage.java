/**
 * 
 */
package com.ones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 */
@JsonInclude(value = Include.NON_NULL)
public class SuccessMessage {

	private String code;

	private String message;

	public SuccessMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public SuccessMessage(String code) {
		super();
		this.code = code;
	}

	public SuccessMessage() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
