package com.libretaDigital.fileupload;


import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


public class UploadsRejectedExecutionHandler implements RejectedExecutionHandler {	
	
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		try {
			executor.getQueue().put(r);
		 } catch (InterruptedException ie) {
			 throw new RejectedExecutionException(ie);
		 }
	}
 }

	 
	

