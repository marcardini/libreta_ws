package com.libretaDigital.fileupload;


public class FileLine {
	
	protected long lineNumber;		//The number of the line in the file
	protected String originalLine;	//The original text of the line
	protected String upoloadProcessorId;		//An String which will be used on the transaction processor to decide which upload module will process this line
	
	
	public long getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public void setOriginalLine(String originalLine) {
		this.originalLine = originalLine;
	}
	public String getOriginalLine() {
		return originalLine;
	}
	
	public void setUpoloadProcessorId(String upoloadProcessorId) {
		this.upoloadProcessorId = upoloadProcessorId;
	}
	public String getUpoloadProcessorId() {
		return upoloadProcessorId;
	}
	
	
}
