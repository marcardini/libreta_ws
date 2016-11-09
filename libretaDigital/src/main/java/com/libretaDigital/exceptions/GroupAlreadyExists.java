package com.libretaDigital.exceptions;

public class GroupAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;

	public GroupAlreadyExists() {
		super();
	}

	public GroupAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupAlreadyExists(String message) {
		super(message);
	}

	public GroupAlreadyExists(Throwable cause) {
		super(cause);
	}
}