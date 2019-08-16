package com.ones.exception;

/**
 * Created by Atlantis on 6/6/2017.
 */
public class NotLoginException extends SecurityException {
    private static final long serialVersionUID = 1L;

    public NotLoginException() {
    }
    public NotLoginException(String unauthorised) {
        super(unauthorised);
    }
    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
