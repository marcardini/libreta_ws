package com.libretaDigital.fileupload;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.interfaces.IFileParser;

public class FileUploadBlockManagerFactory  {

	UploaderProperties properties;
	private boolean dumpFileErrors = true;
	private long blockSize = 500;
	private String switchTEMPFolderPath;
	private String consoleLOGSFolderPath;
	private double allowedErrorRate = 0.5;
	private long minRecordsToCheckErrorAverage = 2000;
	
	
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
	public boolean isDumpFileErrors() {
		return dumpFileErrors;
	}
	public void setDumpFileErrors(boolean dumpFileErrors) {
		this.dumpFileErrors = dumpFileErrors;
	}
	public long getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(long blockSize) {
		this.blockSize = blockSize;
	}
	public String getSwitchTEMPFolderPath() {
		return switchTEMPFolderPath;
	}
	public void setSwitchTEMPFolderPath(String switchTEMPFolderPath) {
		this.switchTEMPFolderPath = switchTEMPFolderPath;
	}
	public String getConsoleLOGSFolderPath() {
		return consoleLOGSFolderPath;
	}
	public void setConsoleLOGSFolderPath(String consoleLOGSFolderPath) {
		this.consoleLOGSFolderPath = consoleLOGSFolderPath;
	}
	public double getAllowedErrorRate() {
		return allowedErrorRate;
	}
	public void setAllowedErrorRate(double allowedErrorRate) {
		this.allowedErrorRate = allowedErrorRate;
	}
	public long getMinRecordsToCheckErrorAverage() {
		return minRecordsToCheckErrorAverage;
	}
	public void setMinRecordsToCheckErrorAverage(long minRecordsToCheckErrorAverage) {
		this.minRecordsToCheckErrorAverage = minRecordsToCheckErrorAverage;
	}
}
