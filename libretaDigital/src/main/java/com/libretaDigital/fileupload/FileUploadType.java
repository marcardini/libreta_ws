package com.libretaDigital.fileupload;

public enum FileUploadType {

	PROFESSORS("Profesores"),
	STUDENTS("Estudiantes"),
	GROUPS("Grupos"), 
	PROGRAM("Programas");
	
	private final String value;

	private FileUploadType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}
