package com.libretaDigital.exceptions;

public class DateConvertorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DateConvertorException() {
		super();
	}

	public DateConvertorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateConvertorException(String message) {
		super(message);
	}

	public DateConvertorException(Throwable cause) {
		super(cause);
	}
}
