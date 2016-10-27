package com.libretaDigital.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.*;
import com.libretaDigital.exceptions.InvalidURLException;
import com.libretaDigital.fileupload.*;
import com.libretaDigital.interfaces.*;

import com.libretaDigital.fileupload.FileUploadBlockManager.BlockManagerStatus;


public class FileUploadServiceImpl implements FileUploadService {

	ProfessorDAO professorDAO;
	IFileParser cvsSplitBy;
	String csvFile = "/files/docentes_cvs.csv";
	
	private int uploadHistorySize;
	private FileUploadBlockManagerFactory  fileUploadManagerFactory;
	private List<FileUploadBlockManager> uploadingAndToUpload;
	private ThreadPoolTaskExecutor asynchTaskExcecutor;
	private FileImporter fileImporter;
	private List<FileUploadSummary> alreadyUploadedFiles;
	
	private static Logger log = Logger.getLogger(FileUploadServiceImpl.class);

	@Override
	public void fileUpload(String tomcatAdress, String fileName, String userName, String selectedUploadType) {
		
		asynchImportFile(tomcatAdress, fileName, userName, cvsSplitBy);
		try {
			new Thread(new Runnable() {
				public void run() {
					
				}
			}).start();
		}catch(InvalidURLException e){
			throw e;
		}
	
		if(selectedUploadType.equals(FileUploadType.PROFESSORS.getValue()))
			addProfessors();
		else if(selectedUploadType.equals(FileUploadType.STUDENTS.getValue()))
			addStudents();
		else if(selectedUploadType.equals(FileUploadType.GROUPS.getValue()))
			addGroups();
		else if(selectedUploadType.equals(FileUploadType.PROGRAM.getValue()))
			addPrograms();
	}

	private void addProfessors() {

		String line = "";
		String splitter = cvsSplitBy.toString();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {

				String[] professorLine = line.split(splitter);
				Professor professor = new Professor(professorLine[0], professorLine[1]);
				professorDAO.save(professor);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addStudents() {

	}

	private void addGroups() {

	}

	private void addPrograms() {

	}
	
	private void asynchImportFile(String urlUploadFile, String fileName, String user, IFileParser parser) {
		String[] fileNameSplit = fileName.split("/");
		fileNameSplit = fileNameSplit[fileNameSplit.length-1].split("\\\\");
		String originalName = fileNameSplit[fileNameSplit.length-1];
		try {
			final FileUploadBlockManager currentUploading = fileUploadManagerFactory.createLocalFileUploadBlockManager(urlUploadFile, originalName, user, parser);
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
						log.error("Error on procces file URL.", ex);
					}
		         }
		    });
		}catch (Throwable e) {
			log.error("Error on procces file URL.", e);
			throw new InvalidURLException();
		}
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
			FileUploadSummary toRemove = alreadyUploadedFiles.get(alreadyUploadedFiles.size()-1);
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
	
	protected FileUploadSummary createFileUploadSummaryDTO(FileUploadBlockManager blockManager) {
		FileUploadSummary sumaryDTO = new FileUploadSummary();
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
		}
		sumaryDTO.setUser(blockManager.getUser());
		
		return sumaryDTO;
	}

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

	public List<FileUploadBlockManager> getUploadingAndToUpload() {
		return uploadingAndToUpload;
	}

	public void setUploadingAndToUpload(List<FileUploadBlockManager> uploadingAndToUpload) {
		this.uploadingAndToUpload = uploadingAndToUpload;
	}

	public FileUploadBlockManagerFactory getFileUploadManagerFactory() {
		return fileUploadManagerFactory;
	}

	public void setFileUploadManagerFactory(FileUploadBlockManagerFactory fileUploadManagerFactory) {
		this.fileUploadManagerFactory = fileUploadManagerFactory;
	}

	public ThreadPoolTaskExecutor getAsynchTaskExcecutor() {
		return asynchTaskExcecutor;
	}

	public void setAsynchTaskExcecutor(ThreadPoolTaskExecutor asynchTaskExcecutor) {
		this.asynchTaskExcecutor = asynchTaskExcecutor;
	}
	
	public int getUploadHistorySize() {
		return uploadHistorySize;
	}
	
	@Required
	public void setUploadHistorySize(int uploadHistorySize) {
		this.uploadHistorySize = uploadHistorySize;
	}
}