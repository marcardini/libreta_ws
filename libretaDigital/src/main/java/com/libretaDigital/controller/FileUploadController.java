package com.libretaDigital.controller;

import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.libretaDigital.fileupload.FileUploadFacadeImpl;
import com.libretaDigital.fileupload.FileUtilities;
import com.libretaDigital.utils.FileUploadType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

@Controller
public class FileUploadController {
	
	private static String CATALINA_HOME = System.getenv("CATALINA_HOME");
	private Logger logger = Logger.getLogger(FileUploadController.class);
	private ArrayList<File> files;
	private FileUploadFacadeImpl fileUploadFacadeImpl;
	private FileUploadType selectedFileType;
	
	@RequestMapping(value = "/fileUpload", method= RequestMethod.GET)
	public ModelAndView FileUpload(){
		ModelAndView page =  new ModelAndView("fileUpload");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos Masiva");
		page.addObject("codMenu", "D1");
		return page;
	}
	
	
	public String sendFile() throws Exception {

		String toReturn = "";
		
		try {
			if (CATALINA_HOME == null || CATALINA_HOME.equals("")) {
				
				toReturn = "";
				
			} else {
				ResourceBundle rb = ResourceBundle.getBundle("messages_es");
				String uploadDirectory = CATALINA_HOME.replace("\\", "/") + rb.getString("upload_tomcat_directoy");

				for (Iterator<File> iterator = this.getLoadFiles().iterator(); iterator.hasNext();) {
					File file = (File) iterator.next();
					
					try {
						if (file.length() > 0) {

							FileUtilities.copyFile(file, uploadDirectory);

							String localPort = rb.getString("service.port");
							String http_address = rb.getString("http_address");
							String tomcat_address = http_address+":"+ localPort + "/files/";
						
							String cleanFileName = files.get(0).getName();
							
							
							fileUploadFacadeImpl.fileUpload(tomcat_address + file.getName(), getNameFile(file), "admin", selectedFileType.name());

							toReturn = "sucessfull_upload";
							
						} else {
							//facesMessages.addFromResourceBundle(Severity.ERROR,"upload_label_error_empy_file");
							logger.error("Error archivo vacio");
							toReturn = "";
						}

					} catch (IOException e) {
						//facesMessages.addFromResourceBundle(Severity.ERROR,"upload_label_error_empy_file");
						logger.error("Error archivo vacio");
						toReturn = "";
					}		
				}
			}
		} catch(MissingResourceException e) {
			//facesMessages.addFromResourceBundle(Severity.ERROR, "upload.systemerror");
			logger.error("Hubo un error durante la carga de archivo de cupones");
			throw e;
		}
		
		return toReturn;
	}
	
	public List<File> getLoadFiles() {
		return new ArrayList<File>();
	}
	
	private String getNameFile(File file) {
		return "prueba"; 
	}
	
}
