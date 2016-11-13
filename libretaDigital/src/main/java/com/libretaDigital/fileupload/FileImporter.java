package com.libretaDigital.fileupload;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.libretaDigital.fileupload.FileUploadBlockManager.BlockManagerStatus;
import com.libretaDigital.interfaces.BlockProcessor;
import com.libretaDigital.parameters.ParameterControl;
import com.libretaDigital.parameters.ParameterId;
import com.libretaDigital.parameters.ParametersControlFacade;

public class FileImporter  {
	
	public static Logger logger = Logger.getLogger(FileImporter.class);
	
	private ThreadPoolTaskExecutor taskExecutor;
	private BlockProcessor blockProcessor;
	private ParametersControlFacade parametersFacade;
	
	private boolean inTheEnd;
	private int uploadConcurrentThreads;
	private long cycleSleepMillis;
	
	// If we got no values of the parameters, we will take these ones
	private static final int DEFAULT_uploadConcurrentThreads = 1;
	private static final long DEFAULT_cycleSleepMillis = 0;
	
	//This constant defines the time to sleep, which is used to handle the concurrency
	private static final int SLEEP_MULT = 100;
	
	
	public void init() {
		boolean crearParam = false;
		long paramValue;
		try {
			ParameterControl parameter = null;
			if(parametersFacade != null) 
				parametersFacade.getParameterByName(ParameterId.UPLOAD_CONCURRENT_THREADS);
			paramValue = parameter.getValue().intValue();
		} catch (Throwable tr) { 
			crearParam = true;
			paramValue = DEFAULT_uploadConcurrentThreads;
		}
		uploadConcurrentThreads= (int)paramValue;
		if (crearParam || paramValue <1) {
			try {
				ParameterControl parameter = new ParameterControl();
				parameter.setName(ParameterId.UPLOAD_CONCURRENT_THREADS);
				parameter.setEditableFromConsole(true);
				parameter.setValue(new Long(paramValue));
				parametersFacade.saveParameter(parameter);
			} catch (Throwable tr) {
				logger.warn("Error tryng to save Control Parameter " + ParameterId.UPLOAD_CONCURRENT_THREADS, tr);
			}
		}
		
		crearParam = false;
		paramValue = -1;
		try {
			ParameterControl parameter = null;
					
			if(parametersFacade != null)
				parametersFacade.getParameterByName(ParameterId.CYCLE_SLEEP_MILLIS);
			paramValue = parameter.getValue().intValue();
		} catch (Throwable tr) { 
			crearParam = true;
			paramValue = DEFAULT_cycleSleepMillis;
		}
		cycleSleepMillis = paramValue;
		if (crearParam || paramValue <0) {
			try {
				ParameterControl parameter = new ParameterControl();
				parameter.setName(ParameterId.CYCLE_SLEEP_MILLIS);
				parameter.setEditableFromConsole(true);
				parameter.setValue(new Long(paramValue));
				parametersFacade.saveParameter(parameter);
			} catch (Throwable tr) {
				logger.warn("Error tryng to save Control Parameter "+ ParameterId.CYCLE_SLEEP_MILLIS, tr);
			}
		}
	}
	
	
	public List<ItemError> processFileImport(FileUploadBlockManager blockManager) throws IOException {
		//We refresh from the DB the key Ranges we keep in memory during the upload.
		uploadConcurrentThreads = taskExecutor.getMaxPoolSize();
		inTheEnd = false;
		checkBlockSizeParameter(blockManager);
		blockManager.setStartProcess();
		
		//logger.debug("Starting process file " + urlFilgetFileURL()e + ", at " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(blockManager.getStartTime())));
		logger.info("Comenzando a importar archivo: ".concat(blockManager.getFileName()));
		
		ProcessorContext context = blockManager.getContext();
		
		while(blockManager.hasMoreBlocks()) {
			int threadsDiff = uploadConcurrentThreads;

			threadsDiff =  threadsDiff - uploadConcurrentThreads;
			//Se setea al thread pool el numero de hilos segun el parametro configurado
			if (threadsDiff!=0 || blockManager.getConcurrentThreads()!=uploadConcurrentThreads ) {
				blockManager.setConcurrentThreads(uploadConcurrentThreads);
				taskExecutor.setCorePoolSize(blockManager.getConcurrentThreads());
				taskExecutor.setMaxPoolSize(blockManager.getConcurrentThreads());
			}
			if (threadsDiff>0) {
				if (taskExecutor.getActiveCount()>0) {
					//Se le da tiempo a terminar a los hilos que estaban corriendo
					try { Thread.sleep((long)(blockManager.getContext().getBlockProcessSecondsPondeatedAverage()*1000)); } catch (InterruptedException ex) { }
				}
				//Al reducir el numero de hilos que estaban corriendo se debe ejecutar esta sentencia ya que en caso contrario no reduce el numero de hilos
				taskExecutor.afterPropertiesSet();
			}
			
			try {
				importBlockOnThread(blockManager.getNextBlock(), context);
			}catch (Throwable e) {
				context.addError(0, "CRITICAL ERROR ON THREAD MANAGER", e.getLocalizedMessage());
				logger.error("CRITICAL ERROR UPLOADING FILE :" + blockManager.getFileName(), e);
			} 
		}
		//If we had less blocks than the core pool size the log file was closed before the threads start
		//So this is to give time to the threads to start.
		try { Thread.sleep(SLEEP_MULT); } catch (InterruptedException ex) { }
		
		synchronized (this) {
			//This is to wait until the end of all threads that are running or on the queue
			inTheEnd = true;
			while (taskExecutor.getActiveCount()>0) {
				try { wait(blockManager.getBlockSize()*SLEEP_MULT); } catch (InterruptedException ex) { }
				try { Thread.sleep(SLEEP_MULT); } catch (InterruptedException ex) { } // This is to give time to end to the thread whose notified 
			}
		}
		context.closeErrorsFile(blockManager.getStatus()==BlockManagerStatus.ABORTED);
		logger.info(blockManager.getEndProcessMessage().concat(" - ").concat(blockManager.getFileName()));
		
		return context.getErrorList();
	}
	
	
	 public void importBlockOnThread(final List<FileLine> block, final ProcessorContext context) {
		 final FileImporter aux = this;
         taskExecutor.execute(new Thread() {
        	 public void run() {
        		 if (cycleSleepMillis>0) {
        			 try { Thread.sleep(cycleSleepMillis); } catch (InterruptedException ex) { }
        		 }
              	 blockProcessor.importBlock(block, context);
              	 if (inTheEnd) {
	              	 synchronized (aux) {
	              		 try { aux.notifyAll(); } catch (Throwable ex) { System.out.println(ex.getStackTrace()); }
		             }
              	 }
             }
		 });
	 }
	
	
	 
	 protected void checkBlockSizeParameter(FileUploadBlockManager blockManager) {
		 try {
			 if (blockManager.getBlockSizeParameterName()!=null) {
				
				 ParameterControl blkSize = null;
				 if(parametersFacade != null)
					 blkSize = this.parametersFacade.getParameterByName(blockManager.getBlockSizeParameterName());
				 if (blkSize == null) {
					 blkSize = new ParameterControl();
					 blkSize.setName(blockManager.getBlockSizeParameterName());
					 blkSize.setValue(blockManager.getBlockSize());
					 this.parametersFacade.saveParameter(blkSize);
				 } else {
					 blockManager.setBlockSize(blkSize.getValue().longValue());
				 }
			 }
		 } catch (Throwable tr) {
			 logger.warn("Problema intentando actualizar parametro de cantidad de registros por bloque: "+ blockManager.getBlockSizeParameterName() +", error: " + tr.getLocalizedMessage(),tr );
		 }
	 }
	
	
	 protected void refreshParameters(){
		int newUploadConcurrentThreads = DEFAULT_uploadConcurrentThreads;
		try {
			ParameterControl parameter = null;
			
			if(parametersFacade != null)
				parametersFacade.getParameterByName(ParameterId.UPLOAD_CONCURRENT_THREADS);
			newUploadConcurrentThreads = parameter.getValue().intValue();
		} catch (Throwable tr) { 
			newUploadConcurrentThreads = DEFAULT_uploadConcurrentThreads;
			logger.warn("Could not get parameter " + ParameterId.UPLOAD_CONCURRENT_THREADS, tr);
		}
		uploadConcurrentThreads = (newUploadConcurrentThreads>0)?newUploadConcurrentThreads:DEFAULT_uploadConcurrentThreads;
		
		long newCycleSleepMillis = DEFAULT_cycleSleepMillis;
		try {
			ParameterControl parameter = parametersFacade.getParameterByName(ParameterId.CYCLE_SLEEP_MILLIS);
			newCycleSleepMillis = parameter.getValue().intValue();
		} catch (Throwable tr) { 
			newCycleSleepMillis = DEFAULT_cycleSleepMillis;
			logger.warn("Couldn not get parameter " + ParameterId.CYCLE_SLEEP_MILLIS, tr);
		}
		cycleSleepMillis = (newCycleSleepMillis>0)?newCycleSleepMillis:DEFAULT_cycleSleepMillis;
	}

	 
	@Required
	public void setBlockProcessor(BlockProcessor blockProcessor) {
		this.blockProcessor = blockProcessor;
	}
	@Required
	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	@Required
	public void setParametersFacade(ParametersControlFacade parametersFacade) {
		this.parametersFacade = parametersFacade;
	}
	public ParametersControlFacade getParametersFacade() {
		return parametersFacade;
	}
	
}
