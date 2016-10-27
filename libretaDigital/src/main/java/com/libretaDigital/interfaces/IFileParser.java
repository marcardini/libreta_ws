package com.libretaDigital.interfaces;

import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.fileupload.*;

public interface IFileParser {
	
	String getBlockSizeParameterName();
	
	void beforeParseFile();
	
	FileLine parseLine(String line, FileUploadBlockManager manager) throws BuildLineException;

}
