/**
 * 
 */
package com.ones.exception;

/**
 * @author son.truong
 * Apr 13, 2017 11:03:56 PM
 */
public class ContentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = "";

	public ContentException(String msg) {
		super(msg);
	}

	public ContentException(String id, String msg) {
		super(msg);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
