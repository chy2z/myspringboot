package com.springboot.redis;

public class SizeNotEqualException extends Exception {
	private static final long serialVersionUID = 8665458102500447138L;

	public SizeNotEqualException() {
		super();
	}

	public SizeNotEqualException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SizeNotEqualException(String message, Throwable cause) {
		super(message, cause);
	}

	public SizeNotEqualException(String message) {
		super(message);
	}

	public SizeNotEqualException(Throwable cause) {
		super(cause);
	}

}
