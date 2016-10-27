package com.libretaDigital.fileupload;

import java.util.List;

public interface BlockProcessor {

	public  void importBlock(List<FileLine> lines, ProcessorContext context);

}