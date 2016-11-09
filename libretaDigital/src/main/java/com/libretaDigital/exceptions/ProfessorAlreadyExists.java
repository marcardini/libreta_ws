package com.libretaDigital.exceptions;

public class ProfessorAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;

	public ProfessorAlreadyExists() {
		super();
	}

	public ProfessorAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}

	public ProfessorAlreadyExists(String message) {
		super(message);
	}

	public ProfessorAlreadyExists(Throwable cause) {
		super(cause);
	}
}