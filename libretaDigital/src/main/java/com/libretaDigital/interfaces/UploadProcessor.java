package com.libretaDigital.interfaces;

import com.libretaDigital.fileupload.BlockUploadContext;

public interface UploadProcessor {
	
	public abstract void invoke(BlockUploadContext context);

	public abstract void transactionFailed(BlockUploadContext context);	

}
