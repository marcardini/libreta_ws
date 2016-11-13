package com.libretaDigital.beans;

public class FileUploadBean {

	private String filename;
	private byte[] file;
	private String mimeType;
	private String uploadType;

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

	public FileUploadBean(String filename, byte[] file, String mimeType, String uploadType) {

		this.file = file;
		this.filename = filename;
		this.mimeType = mimeType;
		this.uploadType = uploadType;
	}

	public FileUploadBean() {
		// Default Constructor
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

}
/*http://www.cantangosolutions.com/blog/Easy-File-Upload-Using-DropzoneJS-AngularJs-And-Spring*/