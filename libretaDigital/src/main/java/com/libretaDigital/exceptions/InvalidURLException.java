package com.libretaDigital.exceptions;

public class InvalidURLException  extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public InvalidURLException() {
		super();
	}

	public InvalidURLException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidURLException(String message) {
		super(message);
	}

	public InvalidURLException(Throwable cause) {
		super(cause);
	}
}
