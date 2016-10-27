package com.libretaDigital.fileupload;

public class FileUploadAuditObject {
	
	private String fileName;
	
	public FileUploadAuditObject(String name){
		this.fileName = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}