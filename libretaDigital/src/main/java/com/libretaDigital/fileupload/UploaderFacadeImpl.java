package com.libretaDigital.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.libretaDigital.exceptions.InvalidURLException;
import com.libretaDigital.fileupload.FileUploadBlockManager.BlockManagerStatus;
import com.libretaDigital.interfaces.IFileParser;
import com.libretaDigital.interfaces.UploaderFacade;
import com.libretaDigital.parameters.ParameterControl;
import com.libretaDigital.parameters.ParameterId;

public class UploaderFacadeImpl implements UploaderFacade {
	
	public static Logger logger = Logger.getLogger(UploaderFacadeImpl.class);
	
	private int uploadHistorySize;
	private ThreadPoolTaskExecutor asynchTaskExcecutor;
	private FileImporter fileImporter;
	private FileUploadBlockManagerFactory fileUploadManagerFactory;
	private List<FileUploadSummaryDTO> alreadyUploadedFiles;
	private List<FileUploadBlockManager> uploadingAndToUpload;
	
	public void init() { }
	
	public UploaderFacadeImpl  () {
		alreadyUploadedFiles = new ArrayList<FileUploadSummaryDTO>();
		uploadingAndToUpload = new ArrayList<FileUploadBlockManager>();
	}
	
	public List<ItemError> importFile(String urlUploadFile, String originalFileName, String user, boolean resetBalance, IFileParser parser) throws InvalidURLException {
		String[] fileNameSplit = originalFileName.split("/");
		fileNameSplit = fileNameSplit[fileNameSplit.length-1].split("\\\\");
		String originalName = fileNameSplit[fileNameSplit.length-1];
		try {
			final FileUploadBlockManager  currentUploading = fileUploadManagerFactory.create(urlUploadFile, originalName, user, parser);
			currentUploading.setResetBalance(resetBalance);
			List<ItemError> result = fileImporter.processFileImport(currentUploading);
			addToAlreadyUploadedFiles(currentUploading);
			//Se espera a que todos los bloques terminen de procesar el archivo
			while(!currentUploading.getContext().getShowFile().get())
				try {Thread.sleep(1000);} catch (Throwable tr) {}
			return result;
		}catch (IOException ex) {
			logger.error("Error on procces file URL.", ex);
			throw new InvalidURLException();
		}
	}
		
	@Override
	public String asynchImportLocalFile(final String urlUploadFile, final String originalFileName, final String user, boolean resetBalance,final IFileParser parser) throws InvalidURLException {
		String result = "OK";
		String[] fileNameSplit = originalFileName.split("/");
		fileNameSplit = fileNameSplit[fileNameSplit.length-1].split("\\\\");
		String originalName = fileNameSplit[fileNameSplit.length-1];
		try {
			final FileUploadBlockManager  currentUploading = fileUploadManagerFactory.createLocalFileUploadBlockManager(urlUploadFile, originalName, user, parser);
			currentUploading.setResetBalance(resetBalance);
			uploadingAndToUpload.add(currentUploading);
			
			asynchTaskExcecutor.execute( new Runnable() {
				public void run() throws RuntimeException  {
					try {
						parser.beforeParseFile();
						fileImporter.processFileImport(currentUploading);
						addToAlreadyUploadedFiles(currentUploading);
						uploadingAndToUpload.remove(currentUploading);
					}catch (IOException ex) {
						uploadingAndToUpload.remove(currentUploading);
						logger.error("Error on procces file URL.", ex);
					}
		         }
		    });
		}catch (Throwable e) {
			logger.error("Error on procces file URL.", e);
			throw new InvalidURLException();
		}
		return result;
	}
	
	public List<FileUploadSummaryDTO> getUploadsStatus() {
		List<FileUploadSummaryDTO> result = new ArrayList<FileUploadSummaryDTO>();
		
		Iterator<FileUploadBlockManager> itu = uploadingAndToUpload.iterator();
		while (itu.hasNext()) {
			try {
				result.add(createFileUploadSummaryDTO(itu.next()));
			}catch(Exception ex) {}
		}
		result.addAll(alreadyUploadedFiles);
		
		
		return result;
	}
	
	public boolean setConcurrentThreads(int threads) {
		if (threads < 1) return false;
		boolean result = false;
		try {
			ParameterControl parameter = this.getFileImporter().getParametersFacade().getParameterByName(ParameterId.UPLOAD_CONCURRENT_THREADS);
			parameter.setValue(new Long(threads));
			this.getFileImporter().getParametersFacade().editParameter(parameter);
			result = true;
		} catch (Throwable tr) {
			logger.warn("Couldn not update concurrent threads parameter", tr);
		}
		return result;
	}
	
	public boolean setCycleDelayInMillis(long millis) {
		if (millis < 0) return false;
		boolean result = false;
		try {
			ParameterControl parameter = this.getFileImporter().getParametersFacade().getParameterByName(ParameterId.CYCLE_SLEEP_MILLIS);
			parameter.setValue(new Long(millis));
			this.getFileImporter().getParametersFacade().editParameter(parameter);
			result = true;
		} catch (Throwable tr) {
			logger.warn("Couldn not update Cycle Delay  parameter", tr);
		}
		return result;
	}
	
	protected void addToAlreadyUploadedFiles(FileUploadBlockManager blockManager) {
		alreadyUploadedFiles.add(0,createFileUploadSummaryDTO(blockManager));
		
		HashSet<String> fileNames = new HashSet<String>(); 
		if (alreadyUploadedFiles.size()> uploadHistorySize){
			for (int i = 0; i < uploadHistorySize; i++) {
				fileNames.add(alreadyUploadedFiles.get(i).getLogFileName());
			}
		}
		
		while (alreadyUploadedFiles.size() > uploadHistorySize) {
			FileUploadSummaryDTO toRemove = alreadyUploadedFiles.get(alreadyUploadedFiles.size()-1);
			alreadyUploadedFiles.remove(toRemove);
			if (!fileNames.contains(toRemove.getLogFileName())) {
				try {
					//old log file is deleted from console if its not on the list 
					File fichero = new File(blockManager.getConsoleLOGSFolderPath().concat(toRemove.getLogFileName()));
					fichero.delete();
				}catch (Exception ex) {	}
			}
		}
	}
	
	protected FileUploadSummaryDTO createFileUploadSummaryDTO(FileUploadBlockManager blockManager) {
		FileUploadSummaryDTO sumaryDTO = new FileUploadSummaryDTO();
		sumaryDTO.setFileName(blockManager.getFileName());
		sumaryDTO.setStatus(blockManager.getStatus().toString());
		sumaryDTO.setStartTimestamp(blockManager.getStartTime());
		sumaryDTO.setFileLines(blockManager.getFileLines());
		if (blockManager.getStatus()!= BlockManagerStatus.QUEUED ) {
			sumaryDTO.setEndTimestamp(blockManager.getEndTime());
			sumaryDTO.setEstimatedRemainingSeconds(Long.valueOf((long)blockManager.getEstimatedRemainingSeconds()));
			sumaryDTO.setProcessedLines(Long.valueOf(blockManager.getProcessedLines()));
			sumaryDTO.setWrongLinesCounter(blockManager.getContext().getErrorCount());
			sumaryDTO.setLogFileName(blockManager.getContext().getLogFileNameConsole());
			sumaryDTO.setShowFile(blockManager.getContext().getShowFile());
		}
		sumaryDTO.setUser(blockManager.getUser());
		
		return sumaryDTO;
	}
	
	public int getUploadHistorySize() {
		return uploadHistorySize;
	}
	@Required
	public void setUploadHistorySize(int uploadHistorySize) {
		this.uploadHistorySize = uploadHistorySize;
	}
	public ThreadPoolTaskExecutor getAsynchTaskExcecutor() {
		return asynchTaskExcecutor;
	}
	@Required
	public void setAsynchTaskExcecutor(ThreadPoolTaskExecutor asynchTaskExcecutor) {
		this.asynchTaskExcecutor = asynchTaskExcecutor;
	}
	public FileUploadBlockManagerFactory getFileUploadManagerFactory() {
		return fileUploadManagerFactory;
	}
	@Required
	public void setFileUploadManagerFactory(FileUploadBlockManagerFactory fileUploadManagerFactory) {
		this.fileUploadManagerFactory = fileUploadManagerFactory;
	}
	public FileImporter getFileImporter() {
		return fileImporter;
	}
	@Required
	public void setFileImporter(FileImporter fileImporter) {
		this.fileImporter = fileImporter;
	}	
}