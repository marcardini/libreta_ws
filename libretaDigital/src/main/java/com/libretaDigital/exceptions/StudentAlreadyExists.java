package com.libretaDigital.exceptions;

public class StudentAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;

	public StudentAlreadyExists() {
		super();
	}

	public StudentAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentAlreadyExists(String message) {
		super(message);
	}

	public StudentAlreadyExists(Throwable cause) {
		super(cause);
	}
}