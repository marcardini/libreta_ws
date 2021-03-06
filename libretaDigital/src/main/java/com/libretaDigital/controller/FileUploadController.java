package com.libretaDigital.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.fileupload.FileUploadFacadeImpl;
import com.libretaDigital.fileupload.FileUtilities;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.utils.FileUploadType;

import java.util.MissingResourceException;
import java.io.IOException;

import org.apache.log4j.Logger;

@Controller
public class FileUploadController {
	
	private static String CATALINA_HOME = System.getenv("CATALINA_HOME");
	private Logger logger = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadFacadeImpl fileUploadFacadeImpl;
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	private FileUploadType selectedFileType;	
	private MultipartFile file;
	private Professor loguedProfessor;
	private String logguedUserName;
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView FileUpload(HttpSession session) throws JsonProcessingException {
		ModelAndView page = new ModelAndView("fileUpload");
		loguedProfessor = professorServiceImpl.getByEmail(session.getAttribute("loggedUser").toString());
		
		page.addObject("logguedUserName", mapper.writeValueAsString(loguedProfessor.getEmail().toUpperCase()));
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos Masiva");
		page.addObject("codMenu", "D1");
		return page;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "fileUpload/upload", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request, @RequestParam(value="type", required=true) String type) {
		try {
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				file = request.getFile(uploadedFile);				
				try {
					if (CATALINA_HOME == null || CATALINA_HOME.equals("")) {						
						this.clearUploadData();
						logger.error("la variable CATALINA_HOME no esta seteada");
						return new ResponseEntity<>("{}", HttpStatus.EXPECTATION_FAILED);						
					} else {
						ResourceBundle rb = ResourceBundle.getBundle("messages_es");
						String uploadDirectory = CATALINA_HOME.replace("\\", "/") + rb.getString("upload_tomcat_directoy");							
							try {
								if (file.getSize() > 0) {
									FileUtilities.copyFile(file, uploadDirectory);
									String localPort = rb.getString("service.port");
									String http_address = rb.getString("http_address");
									String tomcat_address = http_address+":"+ localPort + "/files/";									
									selectedFileType = FileUploadType.valueOf(type);				
									fileUploadFacadeImpl.fileUpload(tomcat_address + file.getName(), file.getName(), logguedUserName, selectedFileType.name());										
								} else
									logger.error("Error archivo vacio");
							} catch (IOException e) {
								logger.error("Error archivo vacio");
							}		
						}
				} catch(MissingResourceException e) {
					logger.error("Hubo un error durante la carga de archivo");
					throw e;
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		clearUploadData();
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
	 


	public void clearUploadData() {
		file = null;
	}
	
	
	public FileUploadFacadeImpl getFileUploadFacadeImpl() {
		return fileUploadFacadeImpl;
	}
	public void setFileUploadFacadeImpl(FileUploadFacadeImpl fileUploadFacadeImpl) {
		this.fileUploadFacadeImpl = fileUploadFacadeImpl;
	}
	public FileUploadType getSelectedFileType() {
		return selectedFileType;
	}
	public void setSelectedFileType(FileUploadType selectedFileType) {
		this.selectedFileType = selectedFileType;
	}


	public ProfessorServiceImpl getProfessorServiceImpl() {
		return professorServiceImpl;
	}


	public void setProfessorServiceImpl(ProfessorServiceImpl professorServiceImpl) {
		this.professorServiceImpl = professorServiceImpl;
	}


	public Professor getLoguedProfessor() {
		return loguedProfessor;
	}


	public void setLoguedProfessor(Professor loguedProfessor) {
		this.loguedProfessor = loguedProfessor;
	}


	public String getLogguedUserName() {
		return logguedUserName;
	}


	public void setLogguedUserName(String logguedUserName) {
		this.logguedUserName = logguedUserName;
	}

}
