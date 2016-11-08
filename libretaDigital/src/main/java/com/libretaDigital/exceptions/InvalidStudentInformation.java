package com.libretaDigital.exceptions;

public class InvalidStudentInformation extends Exception {

	private static final long serialVersionUID = 1L;

	public enum ErrorType {

		NULL_USER
	}

	private ErrorType error;

	public InvalidStudentInformation(ErrorType dtError) {
		error = dtError;
	}

	public InvalidStudentInformation(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidStudentInformation(String message) {
		super(message);
	}

	public InvalidStudentInformation(Throwable cause) {
		super(cause);
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}
}