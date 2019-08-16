/**
 * 
 */
package com.ones.exception;

/**
 * @author son.truong
 * Apr 13, 2017 11:03:56 PM
 */
public class PermissionDenyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PermissionDenyException() {
	}

	public PermissionDenyException(String message) {
		super(message);
	}

	public PermissionDenyException(String message, Throwable cause) {
		super(message, cause);
	}
}
