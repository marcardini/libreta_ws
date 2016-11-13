package com.libretaDigital.fileupload;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.interfaces.BlockProcessor;
import com.libretaDigital.interfaces.TransactionProcessor;


public class BlockProcessorImpl implements BlockProcessor  {
	
	Logger log = Logger.getLogger(BlockProcessorImpl.class); 
	TransactionProcessor transactionProcessor;
	Map<String, String> errorMapList;
	
	public void importBlock(List<FileLine> lines, ProcessorContext context) {
		
		if (lines != null && lines.size() > 0){
			long blockStartTimestamp = System.currentTimeMillis();
			
			BlockUploadContext blockUploadContext = new BlockUploadContext(lines.iterator(), lines.size());
			try {
				transactionProcessor.importBlock(blockUploadContext);
			} catch (Throwable ex) {
				//if the block transaction fails, we try with a transaction for each line
				//We clean what got to be cleaned because of the transaction fail 
				transactionProcessor.transactionFailed(blockUploadContext);
				
				blockUploadContext = new BlockUploadContext(lines.iterator(), 1);
				
				while (blockUploadContext.getLineIterator().hasNext()) { 
					try {
						transactionProcessor.importBlock(blockUploadContext);
						
					} catch (Throwable e) {
						//We clean what got to be cleaned because of the transaction fail 
						transactionProcessor.transactionFailed(blockUploadContext);
						
						String originalMessageError;
						if (e.getLocalizedMessage() == null ){
							log.warn("Exception without localizedMessaage, stacktrace: ", e);
							originalMessageError = "Undefined";
						} else {
							originalMessageError = e.getLocalizedMessage().toUpperCase();
						}
						String spanishMessageError = null;
						Iterator<Entry<String,String>> errorMapListIter = errorMapList.entrySet().iterator();
						if (errorMapListIter != null){
							while (errorMapListIter.hasNext()){
								Entry<String, String> entry = errorMapListIter.next();
								if (originalMessageError != null && originalMessageError.indexOf(entry.getKey().toUpperCase()) != -1){
									spanishMessageError = entry.getValue();
									break;
								}
							}
						}
						log.debug("Error on line " + blockUploadContext.getCurrentLine().getLineNumber() + ": " + (spanishMessageError != null ? (spanishMessageError + ". OriginalError:" + originalMessageError) : originalMessageError));
						context.addError(blockUploadContext.getCurrentLine().getLineNumber(), blockUploadContext.getCurrentLine().getOriginalLine() ,spanishMessageError != null ? spanishMessageError : originalMessageError);
					}
				}
			}
			context.addProcessedBlock(System.currentTimeMillis()-blockStartTimestamp, lines.get(0).getLineNumber(), lines.get(lines.size()-1).getLineNumber());
		}
	}
	
	
	@Required
	public void setTransactionProcessor(TransactionProcessor transactionProcessor) {
		this.transactionProcessor = transactionProcessor;
	}

	public Map<String, String> getErrorMapList() {
		return errorMapList;
	}

	public void setErrorMapList(Map<String, String> errorMapList) {
		this.errorMapList = errorMapList;
	}
}


