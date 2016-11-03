package com.libretaDigital.fileupload;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileUploadSummaryDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String status;
	private String fileName;
	private String user;
	private boolean resetBalance;
	private Long fileLines;
	
	private Long startTimestamp;
	private Long endTimestamp;
	
	private Long processedLines;
	private Long estimatedRemainingSeconds;
	
	private Long wrongLinesCounter;
	private String logFileName;
	
	private AtomicBoolean showFile;
	
	public FileUploadSummaryDTO() {
		resetBalance = true;
		showFile = new AtomicBoolean(false);
	}
	
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
	
	public boolean isResetBalance() {
		return resetBalance;
	}
	public void setResetBalance(boolean resetBalance) {
		this.resetBalance = resetBalance;
	}
	public boolean isShowFile() {
		return showFile.get();
	}
	public void setShowFile(AtomicBoolean showFile) {
		this.showFile = showFile;
	}
	
}
