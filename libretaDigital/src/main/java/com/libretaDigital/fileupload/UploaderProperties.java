package com.libretaDigital.fileupload;

public class UploaderProperties {
	
	private boolean dumpFileErrors = true;
	private long blockSize = 500;
	private String switchTEMPFolderPath;
	private String consoleLOGSFolderPath;
	private double allowedErrorRate = 0.5;
	private long minRecordsToCheckErrorAverage = 2000;
	private double lastTimePonderateWeight = 0.5;
	private String startFileUploadMSG = " ----- COMIENZA CARGA DE ARCHIVO --------------------------------------------------------";
	private String errorOnLineMSG = 	" - Error en linea ${lineNum}, MENSAJE: ${errorMsg}";
	private String endBlockMSG = 		" - El Bloque [${blockInitLine}-${blockEndLine}] se proceso completamente en ${blockTime} segundos --";
	private String abortFileUploadMSG = " ----- SE ABORTA CARGA DE ARCHIVO TASA DE ERRORES MUY GRANDE-----------------------------";
	private String endFileUploadMSG = 	" ----- SE FINALIZA CARGA DE ARCHIVO COMPLETAMENTE ---------------------------------------";
	private String invalidFormatLine;
	private String exceptionReadingBlock;
	
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
	public String getStartFileUploadMSG() {
		return startFileUploadMSG;
	}
	public void setStartFileUploadMSG(String startFileUploadMSG) {
		this.startFileUploadMSG = startFileUploadMSG;
	}
	public String getErrorOnLineMSG() {
		return errorOnLineMSG;
	}
	public void setErrorOnLineMSG(String errorOnLineMSG) {
		this.errorOnLineMSG = errorOnLineMSG;
	}
	public String getEndBlockMSG() {
		return endBlockMSG;
	}
	public void setEndBlockMSG(String endBlockMSG) {
		this.endBlockMSG = endBlockMSG;
	}
	public String getAbortFileUploadMSG() {
		return abortFileUploadMSG;
	}
	public void setAbortFileUploadMSG(String abortFileUploadMSG) {
		this.abortFileUploadMSG = abortFileUploadMSG;
	}
	public String getEndFileUploadMSG() {
		return endFileUploadMSG;
	}
	public void setEndFileUploadMSG(String endFileUploadMSG) {
		this.endFileUploadMSG = endFileUploadMSG;
	}	
	public double getLastTimePonderateWeight() {
		return lastTimePonderateWeight;
	}
	public void setLastTimePonderateWeight(double lastTimePonderateWeight) {
		this.lastTimePonderateWeight = lastTimePonderateWeight;
	}
	public String getInvalidFormatLine() {
		return invalidFormatLine;
	}
	public void setInvalidFormatLine(String invalidFormatLine) {
		this.invalidFormatLine = invalidFormatLine;
	}
	public String getExceptionReadingBlock() {
		return exceptionReadingBlock;
	}
	public void setExceptionReadingBlock(String exceptionReadingBlock) {
		this.exceptionReadingBlock = exceptionReadingBlock;
	}
}