package com.libretaDigital.exceptions;

public class InvalidProfessorInformation extends Exception {

	private static final long serialVersionUID = -506899850016642424L;

	public enum ErrorType {

		NULL_USER,
		
		EMPTY_PROFESSORNAME,
		
		EMPTY_PASSWORD,
	}

	private ErrorType error;

	public InvalidProfessorInformation(ErrorType dtError) {
		error = dtError;
	}

	public InvalidProfessorInformation(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProfessorInformation(String message) {
		super(message);
	}

	public InvalidProfessorInformation(Throwable cause) {
		super(cause);
	}

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}
}