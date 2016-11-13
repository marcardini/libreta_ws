package com.libretaDigital.fileupload;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.libretaDigital.interfaces.TransactionProcessor;
import com.libretaDigital.interfaces.UploadProcessor;

public class TransactionProcessorImpl implements TransactionProcessor {
	
	private Map<String, UploadProcessor> uploadProcessorMap; 
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void importBlock(BlockUploadContext context) {
		long processedRows = 0;
		
		while (context.getLineIterator().hasNext() && (processedRows<context.getBlockSize())) {
			FileLine current = context.setNextCurrentLine();
			processedRows++;
			
			UploadProcessor uploadProcessor = uploadProcessorMap.get(current.getUpoloadProcessorId());
			if (uploadProcessor != null) {
				context.addUsedUploadProcessor(uploadProcessor);
				uploadProcessor.invoke(context);
			} else
				throw new RuntimeException("No existe un procesador para esta linea del archivo");
		}
	}

	public void transactionFailed(BlockUploadContext context) {
		for (UploadProcessor u :context.getUsedUploadProcessors())
			u.transactionFailed(context);
	}
	
	@Required
	public void setUploadProcessorMap(Map<String, UploadProcessor> uploadProcessorMap) {
		this.uploadProcessorMap = uploadProcessorMap;
	}
	public Map<String, UploadProcessor> getUploadProcessorMap() {
		return uploadProcessorMap;
	}
}