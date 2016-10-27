package com.libretaDigital.fileupload;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.interfaces.*;

public class FileUploadBlockManagerFactory  {
		
	UploaderProperties properties;
	
	public FileUploadBlockManager create(String urlFile, String originalFileName, String user, IFileParser fileParser) throws MalformedURLException, IOException {
		return new FileUploadBlockManager(urlFile, originalFileName, user, fileParser, properties, Boolean.FALSE); 
	}
	
	public FileUploadBlockManager createLocalFileUploadBlockManager(String urlFile, String originalFileName, String user, IFileParser fileParser) throws MalformedURLException, IOException {
		return new FileUploadBlockManager(urlFile, originalFileName, user, fileParser, properties, Boolean.TRUE); 
	}

	public UploaderProperties getProperties() {
		return properties;
	}
	
	@Required
	public void setProperties(UploaderProperties properties) {
		this.properties = properties;
	}

}
