package com.libretaDigital.interfaces;


import java.util.List;

import com.libretaDigital.fileupload.ProcessorContext;

import com.libretaDigital.fileupload.FileLine;


public interface BlockProcessor {

	public  void importBlock(List<FileLine> lines, ProcessorContext context);

}