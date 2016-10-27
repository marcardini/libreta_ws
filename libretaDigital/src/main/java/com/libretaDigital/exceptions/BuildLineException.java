package com.libretaDigital.exceptions;

public class BuildLineException extends Exception {

	private static final long serialVersionUID = 1L;

	public BuildLineException() { }

	public BuildLineException(String message) {
		super(message);
	}

	public BuildLineException(Throwable cause) {
		super(cause);
	}

	public BuildLineException(String message, Throwable cause) {
		super(message, cause);
	}
}