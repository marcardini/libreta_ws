package com.libretaDigital.interfaces;

import java.util.List;

import com.libretaDigital.exceptions.InvalidURLException;
import com.libretaDigital.fileupload.FileUploadSummaryDTO;
import com.libretaDigital.fileupload.ItemError;
import com.libretaDigital.interfaces.IFileParser;


public interface UploaderFacade {
	
	/**
	 * Import a file using the given parser.
	 * @param urlUploadFile		- the url of the file to be uploaded, it will be copied to a local folder
	 * @param originalFileName	- usually to uploada file to be downloaded the name is changed, so we pass here the original name of the file, to identify it.
	 * @param user		- The user that send the file to be charged.
	 * @param parser	- The parser which will be used to parse the file (is a external implementation which extends IFileParser)
	 * @return
	 * @throws InvalidURLException
	 */
	List<ItemError> importFile(String urlUploadFile, String originalFileName, String user, boolean resetBalance, IFileParser parser) throws InvalidURLException;
	
	/**
	 * Import a file asynchronically using the given parser.
	 * @param urlUploadFile		- the url of the file to be uploaded, it will be copied to a local folder
	 * @param originalFileName	- usually to uploada file to be downloaded the name is changed, so we pass here the original name of the file, to identify it.
	 * @param user		- The user that send the file to be charged.
	 * @param parser	- The parser which will be used to parse the file (is a external implementation which extends IFileParser)
	 * @return
	 * @throws InvalidURLException
	 */
	String asynchImportFile(String urlUploadFile, String originalFileName, String user, boolean resetBalance, IFileParser parser) throws InvalidURLException;
	
	/**
	 * returns a list with the status of the files that are currently being uploaded, 
	 * and the ones which are on the queue waiting to be imported
	 * @return
	 */
	List<FileUploadSummaryDTO> getUploadsStatus();
	
	/**
	 * Set the number of threads that upload the file concurrentlly
	 * @param threads
	 * @return
	 */
	boolean setConcurrentThreads(int threads);
	
	/**
	 * Set a sleep time to do before start a thread (default=0)
	 * @param millis
	 * @return
	 */
	boolean setCycleDelayInMillis(long millis);

	/**
	 * Import a local file asynchronically using the given parser.
	 * @param urlUploadFile		- the url of the file to be uploaded, it will be copied to a local folder
	 * @param originalFileName	- usually to uploada file to be downloaded the name is changed, so we pass here the original name of the file, to identify it.
	 * @param user		- The user that send the file to be charged.
	 * @param parser	- The parser which will be used to parse the file (is a external implementation which extends IFileParser)
	 * @return
	 * @throws InvalidURLException
	 */
	String asynchImportLocalFile(String urlUploadFile, String originalFileName, String user, boolean resetBalance,
			IFileParser parser) throws InvalidURLException;
	
}