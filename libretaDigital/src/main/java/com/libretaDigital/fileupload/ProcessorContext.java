package com.libretaDigital.fileupload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

public class ProcessorContext {
	
	public static Logger logger = Logger.getLogger(ProcessorContext.class);

	AtomicBoolean showFile;
	UploaderProperties properties;
	private long errorCount;
	private long processedBlockCount;
	private double blockProcessSecondsPondeatedAverage;
	private long endBlockTimestamp;
	private List<ItemError> errors;
	private String logFileName;
	private String logFileNameConsole;
	private PrintWriter fileWriter;
	private DecimalFormat decFormat;
	
	
	public ProcessorContext( String logFileName, UploaderProperties properties) throws IOException {
		this.showFile = new AtomicBoolean(false);
		this.logFileName = logFileName;
		this.logFileNameConsole = String.valueOf(System.currentTimeMillis()) + logFileName;
		this.properties = properties;
		
		errorCount=0;
		processedBlockCount = 0;
		blockProcessSecondsPondeatedAverage = -1;
		decFormat = new DecimalFormat("#.###");
		
		if (properties.isDumpFileErrors()) {
			fileWriter = new PrintWriter(new FileWriter(properties.getSwitchTEMPFolderPath() + this.logFileName,true));
			fileWriter.println("\r\n" + (new Timestamp(System.currentTimeMillis())).toString() + properties.getStartFileUploadMSG() + "\r\n");
			fileWriter.flush();
		} else
			this.errors = new ArrayList<ItemError>();
	}	
	
	public void addError(long lineNumber, String lineText, String mensajeError) {
		if (properties.isDumpFileErrors()){
			fileWriter.println((new Timestamp(System.currentTimeMillis())).toString() + properties.getErrorOnLineMSG().replace(":lineNum", String.valueOf(lineNumber)).replace("errorMsg", mensajeError)+ "\r\n"+lineText + "\r\n");
			fileWriter.flush();
		} else
			errors.add(new ItemError(lineText, mensajeError));
		
		errorCount++;
	}
	
	public void addProcessedBlock(long timeInMillis, long blockInitLine, long blockEndLine) {
		endBlockTimestamp = System.currentTimeMillis();
		processedBlockCount++;
		if (blockProcessSecondsPondeatedAverage == -1){
			blockProcessSecondsPondeatedAverage = (double)timeInMillis/1000;
		} else
			blockProcessSecondsPondeatedAverage =  (properties.getLastTimePonderateWeight())*((double)timeInMillis/1000) +  (1-properties.getLastTimePonderateWeight())*(blockProcessSecondsPondeatedAverage);
		
		if (properties.isDumpFileErrors()){
			fileWriter.println((new Timestamp(endBlockTimestamp)).toString() + properties.getEndBlockMSG().replace(":blockInitLine", String.valueOf(blockInitLine)).replace(":blockEndLine", String.valueOf(blockEndLine)).replace(":blockTime", String.valueOf(decFormat.format((double)timeInMillis/1000))));
			fileWriter.flush();
		}
	}
		
	public void closeErrorsFile(boolean isAborted) {
		endBlockTimestamp = System.currentTimeMillis();
		if (processedBlockCount == 0) { processedBlockCount++; }
		if (properties.isDumpFileErrors()){
			if (isAborted) {
				fileWriter.println("\r\n" + (new Timestamp(endBlockTimestamp)).toString() + properties.getAbortFileUploadMSG());
			} else {
				fileWriter.println("\r\n" + (new Timestamp(endBlockTimestamp)).toString() + properties.getEndFileUploadMSG());
			}
			try {
				fileWriter.close(); 
			}catch (Exception ex) {}
			
			try {
				//the log file is copied to the console location
				fileWriter = new PrintWriter(new FileWriter(properties.getConsoleLOGSFolderPath() + this.logFileNameConsole));
				BufferedReader br = new BufferedReader(new FileReader(properties.getSwitchTEMPFolderPath() + this.logFileName));
				String line ="";
				while ((line = br.readLine()) != null) {
					fileWriter.println(line);
				}
				fileWriter.close();
				br.close();
				this.showFile.set(true);
			} catch (Exception ex) {
				logger.warn("No se pudo copiar resultado de carga a Consola "+ ex.getLocalizedMessage(), ex);
			}
		}
	}
	
	public String getLogFileName() {
		return logFileName;
	}
	public String getLogFileNameConsole() {
		return logFileNameConsole;
	}
	public AtomicBoolean getShowFile() {
		return showFile;
	}
	public long getProcessedBlockCount() {
		return processedBlockCount;	
	}
	public double getBlockProcessSecondsPondeatedAverage() {
		return blockProcessSecondsPondeatedAverage;	
	}
	public long getEndBlockTimestamp() {
		return endBlockTimestamp;	
	}
	public long getErrorCount() {
		return errorCount;
	}
	public List<ItemError> getErrorList() {
		return errors;
	}
	
}