package com.libretaDigital.exceptions;

public class InvalidGroupInformation extends Exception {

	private static final long serialVersionUID = 1L;

	public enum ErrorType {

		EMPTY_GROUPNAME
	}

	private ErrorType error;

	public InvalidGroupInformation(ErrorType dtError) {
		error = dtError;
	}

	public InvalidGroupInformation(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidGroupInformation(String message) {
		super(message);
	}

	public InvalidGroupInformation(Throwable cause) {
		super(cause);
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}
}