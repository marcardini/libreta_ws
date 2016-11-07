package com.libretaDigital.fileupload;

import com.libretaDigital.interfaces.FileUploadFacade;
import com.libretaDigital.interfaces.UploaderFacade;

public class FileUploadFacadeImpl implements FileUploadFacade {

	private UploaderFacade uploaderFacade;
	private FileUploadProfessorParser fileUploadProfessorParser;
	private String publishFileAddress;
	private String folderTempPath;
	
	@Override
	public void fileUpload(final String requestUrl, final String fileName, final String userName, final String selectedUploadType) {
		
		try {
			new Thread(new Runnable() {
				public void run() {
					try {
						
						String url = FileUtilities.addParametersToFile(requestUrl, getFolderTempPath(), getPublishFileAddress(), selectedUploadType);
						
						if(selectedUploadType.equals("PROFESSORS"))
							uploaderFacade.asynchImportLocalFile(url, fileName, userName, false, getFileUploadProfessorParser());
						
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}).start();
		
		} catch (RuntimeException e) {
			throw e;
		}

	}

	public String getPublishFileAddress() {
		return publishFileAddress;
	}

	public void setPublishFileAddress(String publishFileAddress) {
		this.publishFileAddress = publishFileAddress;
	}

	public String getFolderTempPath() {
		return folderTempPath;
	}

	public void setFolderTempPath(String folderTempPath) {
		this.folderTempPath = folderTempPath;
	}

	public FileUploadProfessorParser getFileUploadProfessorParser() {
		return fileUploadProfessorParser;
	}

	public void setFileUploadProfessorParser(FileUploadProfessorParser fileUploadProfessorParser) {
		this.fileUploadProfessorParser = fileUploadProfessorParser;
	}

	public UploaderFacade getUploaderFacade() {
		return uploaderFacade;
	}

	public void setUploaderFacade(UploaderFacade uploaderFacade) {
		this.uploaderFacade = uploaderFacade;
	}
	
}
