package com.libretaDigital.utils;

public enum FileUploadType {

	PROFESSORS("PROFESSORS"), 
	STUDENTS("STUDENTS"),
	GROUPS("GROUPS"),
	PROGRAM("PROGRAM");
	
	private final String value;

	private FileUploadType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}
