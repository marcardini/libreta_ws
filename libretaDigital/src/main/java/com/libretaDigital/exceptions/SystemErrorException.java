package com.libretaDigital.exceptions;

public class SystemErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SystemErrorException() {
		super();
	}

	public SystemErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemErrorException(String message) {
		super(message);
	}

	public SystemErrorException(Throwable cause) {
		super(cause);
	}
}
