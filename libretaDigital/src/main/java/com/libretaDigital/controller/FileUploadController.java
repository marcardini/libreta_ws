package com.libretaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	@RequestMapping(value = "/fileUpload", method= RequestMethod.GET)
	public ModelAndView FileUpload(){
		ModelAndView page =  new ModelAndView("fileUpload");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos Masiva");
		page.addObject("codMenu", "D1");
		return page;
	}
	
	
}
