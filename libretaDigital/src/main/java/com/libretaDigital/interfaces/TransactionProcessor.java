package com.libretaDigital.interfaces;

import com.libretaDigital.fileupload.BlockUploadContext;

public interface TransactionProcessor {

	public void importBlock(BlockUploadContext context);
	
	public void transactionFailed(BlockUploadContext context);
	
}