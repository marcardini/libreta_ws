package com.libretaDigital.fileupload;


import java.util.HashSet;
import java.util.Iterator;

import com.libretaDigital.interfaces.UploadProcessor;

public class BlockUploadContext {
	
	private Iterator<FileLine> lineIterator;
	private int blockSize;
	private FileLine currentLine;
	
	private HashSet<UploadProcessor> usedUploadProcessors;
	
	
	public BlockUploadContext() {
		this.usedUploadProcessors = new HashSet<UploadProcessor>();
	}
		
	public BlockUploadContext( Iterator<FileLine> itlLine, int blockSize) {
		this.usedUploadProcessors = new HashSet<UploadProcessor>();
		this.lineIterator = itlLine;
		this.blockSize = blockSize;
	}
	
	public FileLine setNextCurrentLine() {
		currentLine = lineIterator.next();
		return currentLine;
	}	
	public FileLine getCurrentLine() {
		return currentLine;
	}

	public Iterator<FileLine> getLineIterator() {
		return lineIterator;
	}
	public void setLineIterator(Iterator<FileLine> itLine) {
		this.lineIterator = itLine;
	}

	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	public void addUsedUploadProcessor(UploadProcessor uploadProc) {
		if (!this.usedUploadProcessors.contains(uploadProc)) {
			this.usedUploadProcessors.add(uploadProc);
		}
	}
	
	public HashSet<UploadProcessor> getUsedUploadProcessors() {
		return this.usedUploadProcessors;
	}
	
}