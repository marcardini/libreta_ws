package com.libretaDigital.beans;

public class FileUploadBean {

	private String filename;
	private byte[] file;
	private String mimeType;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public FileUploadBean(String filename, byte[] file, String mimeType) {

		this.file = file;
		this.filename = filename;
		this.mimeType = mimeType;
	}

	public FileUploadBean() {
		// Default Constructor
	}

}
/*http://www.cantangosolutions.com/blog/Easy-File-Upload-Using-DropzoneJS-AngularJs-And-Spring*/