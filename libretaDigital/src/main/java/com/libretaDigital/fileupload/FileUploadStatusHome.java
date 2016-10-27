package com.libretaDigital.fileupload;

/*@Scope(ScopeType.CONVERSATION)
@Name("fileUploadStatus")*/
public class FileUploadStatusHome {

	/*@In("#{useControlService}")
	private UseControlService useControlService;*/
	
	/*@In
	FacesMessages facesMessages;
	
	private Logger logger = Logger.getLogger(FileUploadStatusHome.class);
	private boolean showLink;
	private FileUploadSummary fileUpload;
	private String http_address;
	private TimeZone timeZone = Time.getTimeZome();
	
	public List<FileUploadSummary> getUploadsStatus() throws UnknownHostException{
		return new ArrayList<FileUploadSummary>();
		
	}
	/*public List<AccountFileUploadStatus> getUploadsStatus() throws UnknownHostException {
		try {
			statusList = this.getUseControlService().getUploadStatus();
			if (statusList==null || statusList.isEmpty())
			else {
				Iterator<AccountFileUploadStatus> iter = statusList.iterator();
				ResourceBundle rb = ResourceBundle.getBundle("messages_es");
				String logFileLocation = TranslateUtils.translate("import.couponsFileUploadLogPath", "usecontrol");
				while(iter.hasNext()){
					
					AccountFileUploadStatus elem = iter.next();
					//Cambiar de ingles a espanol el estado
					String status = elem.getStatus().toString();
					
					String translatedStatus = rb.getString(status);
					
					elem.setStatus(translatedStatus);
					
					//Setarle link al archivo de errores
					if (!status.equals("QUEUED") && !status.equals("RUNNING"))
						elem.setLogFileName(logFileLocation + elem.getLogFileName());
					
				}
			}
			
		} catch (Exception e) {
			logger.error("Error de sistema",e);
		} 
		
		return statusList;
	}

	public FacesMessages getFacesMessages() {
		return facesMessages;
	}

	public void setFacesMessages(FacesMessages facesMessages) {
		this.facesMessages = facesMessages;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public boolean isShowLink() {
		return showLink;
	}

	public void setShowLink(boolean showLink) {
		this.showLink = showLink;
	}

	public FileUploadSummary getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUploadSummary fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getHttp_address() {
		return http_address;
	}

	public void setHttp_address(String http_address) {
		this.http_address = http_address;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	*/
}
