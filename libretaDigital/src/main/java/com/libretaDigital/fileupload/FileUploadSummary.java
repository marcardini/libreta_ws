package com.libretaDigital.fileupload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", namespace="http://www.geocom.com.uy/geoloyalty")
public class FileUploadSummary {

	@XmlElement(required = true)
	private String status;
	
	@XmlElement(required = true)
	private String fileName;
	
	@XmlElement(required = true)
	private String user;
	
	private boolean charge;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long fileLines;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long startTimestamp;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long endTimestamp;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long processedLines;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long estimatedRemainingSeconds;
	
	@XmlElement(required = true, type = Long.class, nillable = true)
	private Long wrongLinesCounter;
	
	@XmlElement(required = true)
	private String logFileName;
	
	
	private boolean showFile;

	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public Long getFileLines() {
		return fileLines;
	}
	
	public void setFileLines(Long fileLines) {
		this.fileLines = fileLines;
	}
	
	public Long getStartTimestamp() {
		return startTimestamp;
	}
	
	public void setStartTimestamp(Long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	
	public Long getEndTimestamp() {
		return endTimestamp;
	}
	
	public void setEndTimestamp(Long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	
	public Long getProcessedLines() {
		return processedLines;
	}
	
	public void setProcessedLines(Long processedLines) {
		this.processedLines = processedLines;
	}
	
	public Long getEstimatedRemainingSeconds() {
		return estimatedRemainingSeconds;
	}
	
	public void setEstimatedRemainingSeconds(Long estimatedRemainingSeconds) {
		this.estimatedRemainingSeconds = estimatedRemainingSeconds;
	}
	
	public String getLogFileName() {
		return logFileName;
	}
	
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}
	
	public Long getWrongLinesCounter() {
		return wrongLinesCounter;
	}
	
	public void setWrongLinesCounter(Long wrongLinesCounter) {
		this.wrongLinesCounter = wrongLinesCounter;
	}
	
	public boolean isCharge() {
		return charge;
	}
	
	public void setCharge(boolean charge) {
		this.charge = charge;
	}
	
	public boolean isShowFile() {
		return showFile;
	}
	
	public void setShowFile(boolean showFile) {
		this.showFile = showFile;
	}
	
    @Override
	public String toString() {
    	return "";
		//return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE); 
	}
    
}
