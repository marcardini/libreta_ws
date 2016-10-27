package com.libretaDigital.fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.interfaces.IFileParser;

public class FileUploadBlockManager {

	private static int DEFAUT_concurrentThreads = 1;

	public enum BlockManagerStatus {QUEUED, RUNNING, PAUSED, FINISHED, ABORTED}
	private BlockManagerStatus status;

	private UploaderProperties properties;
	
	private int concurrentThreads;
	private long blockSize;

	private String user;
	private URL url;
	private String localPath;
	private String originalFileName;
	private boolean resetBalance; 	//Esta variable indica si se debe resetear el balance(true) o una no(false) 
	
	private long startTimestamp;
	private long fileLines;
	private long deliveredBlocks;
	private long currentLine;
	
	private BufferedReader input;
	private ProcessorContext context;
	private IFileParser parser;

	public FileUploadBlockManager(String urlFile, String originalFileName, String user, IFileParser fileParser,
			UploaderProperties properties, boolean localFile) throws MalformedURLException, IOException  {
		
		this.concurrentThreads = DEFAUT_concurrentThreads;
		this.properties = properties;
		this.blockSize = properties.getBlockSize();
		
		this.parser = fileParser;
		this.user = user;
		this.originalFileName = originalFileName;
		if (localFile) {
			this.localPath = urlFile;
			fileLines = copyFileAndCountLines(localPath,properties.getSwitchTEMPFolderPath().concat(originalFileName));
		} else {
			this.url = new URL(urlFile);
			fileLines = copyFileAndCountLines(url,properties.getSwitchTEMPFolderPath().concat(originalFileName));
		}
		
		startTimestamp = System.currentTimeMillis();
		status = BlockManagerStatus.QUEUED;
		currentLine = 0;
		deliveredBlocks = 0;
	}

	public void setStartProcess() throws IOException  {
		startTimestamp = System.currentTimeMillis();

		String logFileName = "";
		if (properties.isDumpFileErrors()) {
			String[] splitFileName = originalFileName.split("\\.");
			logFileName =  "_LUC_".concat(splitFileName[0]).concat(".txt");
		}
		context = new ProcessorContext(logFileName, properties);

		if (url != null)
			input = new BufferedReader(new InputStreamReader(url.openStream()));
		
		if (localPath != null)
			input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(localPath))));

		status = BlockManagerStatus.RUNNING;
	}

	public List<FileLine> getNextBlock() {
		List<FileLine> lines = new ArrayList<FileLine>();

		String line = null;
		int i = 0;
		try {
			while ((i < this.blockSize) && ((line = input.readLine()) != null)) {
				currentLine++;
				i++;
				try {
					FileLine fileLine = parser.parseLine(line, this);
					fileLine.setLineNumber(currentLine);
					fileLine.setOriginalLine(line);
					lines.add(fileLine);
				} catch (BuildLineException e) {
					context.addError(currentLine , line, properties.getInvalidFormatLine() + e.getLocalizedMessage());
				}
			}
		} catch (IOException e) {
			currentLine++;
			context.addError(currentLine , line, properties.getExceptionReadingBlock() + e.getLocalizedMessage());
		}
		this.deliveredBlocks++;
		if (line == null) {
			//The upload was successfully  completed
			status = BlockManagerStatus.FINISHED;
			try { input.close(); } catch (Exception e) {}
		}
		return lines;
	}

	public boolean hasMoreBlocks() {
		checkCutProcessConditions();
		return (status == BlockManagerStatus.RUNNING);
	}
	
	public void setBlockSize(long blockSize) {
		this.blockSize = blockSize;
	}
	public long getBlockSize() {
		return this.blockSize;
	}

	public ProcessorContext getContext() {
		return context;
	}

	public long getStartTime() {
		return startTimestamp;
	}

	public long getEndTime() {
		if ((context == null) || (context.getProcessedBlockCount()<1)) { 	return 0; }
		return context.getEndBlockTimestamp() + (long)(1000*getEstimatedRemainingSeconds());
	}

	public long getFileLines() {
		return fileLines;
	}

	public String getFileName() {
		return originalFileName;
	}

	public BlockManagerStatus getStatus() {
		return status;
	}

	public int getConcurrentThreads() {
		return concurrentThreads;
	}
	public void setConcurrentThreads(int concurrentThreads) {
		this.concurrentThreads = concurrentThreads;
	}

	public String getUser() {
		return user;
	}
	
	public boolean isResetBalance() {
		return resetBalance;
	}
	public void setResetBalance(boolean resetBalance) {
		this.resetBalance = resetBalance;
	}
	
	public String getConsoleLOGSFolderPath() {
		return properties.getConsoleLOGSFolderPath();
	}

	public String getEndProcessMessage() {
		if (status == BlockManagerStatus.ABORTED) {
			double errorRate = (double) context.getErrorCount() / (context.getProcessedBlockCount() * this.blockSize);
			return "Proceso de Importacion interrumpido en linea ".concat(Long.toString(currentLine)).concat(" debido a que la tasa de errores es demasiado grande (")
					.concat(Long.toString((long) (errorRate * 100))).concat("%)");
		} else {
			return "Proceso de Importacion finalizo exitosamente, lineas procesadas: ".concat(Long.toString(currentLine)).concat(" , errores detectados: ")
					.concat(Long.toString(context.getErrorCount()));
		}
	}

	public long getProcessedPercent() {
		return (context.getProcessedBlockCount() * this.blockSize * 100) / fileLines;
	}

	public long getProcessedLines() {
		if (this.status== BlockManagerStatus.FINISHED) {
			return this.fileLines;
		}
		return context.getProcessedBlockCount() * this.blockSize ;
	}

	public double getEstimatedRemainingSeconds() {
		if ((this.status== BlockManagerStatus.FINISHED) || (this.status == BlockManagerStatus.ABORTED)) {
			return 0;
		}
		if (context!=null && context.getProcessedBlockCount() > 0) {
			return (((double) fileLines / this.blockSize) - context.getProcessedBlockCount()) * context.getBlockProcessSecondsPondeatedAverage() / concurrentThreads;
		}
		// Can nott estimate because there is not enough information
		return -1;
	}

	/**
	 * This method copy the file that will be uploaded to  a server located file, and count the lines on the file
	 * @param surce	the source location of the file
	 * @param dest	the file in which source file will be copied 
	 * @return 		The lines on the file
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private long copyFileAndCountLines(URL surce, String dest) throws MalformedURLException, IOException {
		//Copy to a local Path the upload file and test it�s created.
		PrintWriter fileWriter = new PrintWriter(new FileWriter(dest));
		long result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(surce.openStream()));
		String line ="";
		while ((line = br.readLine()) != null) {
			fileWriter.println(line);
			result++;
		}
		try { br.close(); } catch (Exception e) {};
		fileWriter.close();
		//Test the created file could be opened 
		this.url = new URL("file:///".concat(dest));
		br = new BufferedReader(new InputStreamReader(url.openStream()));
		try { br.close(); } catch (Exception e) {};
		// Count file lines takes 1 sec./1 million lines aprox.
		return result;
	}
	
	/**
	 * This method copy the local file and count the lines on the file
	 * @param surce	the source location of the file
	 * @param dest	the file in which source file will be copied 
	 * @return 		The lines on the file
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private long copyFileAndCountLines(String localPath, String dest) throws MalformedURLException, IOException {
		//Copy to a local Path the upload file and test it�s created.
		PrintWriter fileWriter = new PrintWriter(new FileWriter(dest));
		long result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(localPath))));
		String line ="";
		while ((line = br.readLine()) != null) {
			fileWriter.println(line);
			result++;
		}
		try { br.close(); } catch (Exception e) {};
		fileWriter.close();
			//Test the created file could be opened 
			//this.url = new URL("file:///".concat(dest));
			//br = new BufferedReader(new InputStreamReader(url.openStream()));
		try { br.close(); } catch (Exception e) {};
		// Count file lines takes 1 sec./1 million lines aprox.
		return result;
	}
	
	
	private void checkCutProcessConditions() {
		long processedlines = context.getProcessedBlockCount() * this.blockSize;
		if (currentLine > properties.getMinRecordsToCheckErrorAverage()) {
			if (processedlines==0 || (((double) context.getErrorCount() / processedlines) > properties.getAllowedErrorRate())) {
				// Upload process interrupted because error rate is too big
				status = BlockManagerStatus.ABORTED;
				try { input.close(); } catch (Exception e) {}
			}
		}
	}
	
	protected String getBlockSizeParameterName() {
		return parser.getBlockSizeParameterName();
	}

	public long getDeliveredBlocks() {
		return deliveredBlocks;
	}
	public void setDeliveredBlocks(long deliveredBlocks) {
		this.deliveredBlocks = deliveredBlocks;
	}
}