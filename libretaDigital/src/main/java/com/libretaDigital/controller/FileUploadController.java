package com.libretaDigital.controller;

import java.util.ResourceBundle;

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

import com.libretaDigital.fileupload.FileUploadFacadeImpl;
import com.libretaDigital.fileupload.FileUtilities;
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
	
	private FileUploadType selectedFileType;

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView FileUpload() {
		ModelAndView page = new ModelAndView("fileUpload");
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
				MultipartFile file = request.getFile(uploadedFile);
				
				try {
					if (CATALINA_HOME == null || CATALINA_HOME.equals("")) {
						
						
					} else {
						ResourceBundle rb = ResourceBundle.getBundle("messages_es");
						String uploadDirectory = CATALINA_HOME.replace("\\", "/") + rb.getString("upload_tomcat_directoy");
							
							try {
								if (file.getSize() > 0) {

									FileUtilities.copyFile(file, uploadDirectory);

									String localPort = rb.getString("service.port");
									String http_address = rb.getString("http_address");
									String tomcat_address = http_address+":"+ localPort + "/files/";
									
									//selectedFileType = selec;
									
									fileUploadFacadeImpl.fileUpload(tomcat_address + file.getName(), file.getName(), "admin", type);
										
								} else {
									logger.error("Error archivo vacio");
								}

							} catch (IOException e) {
								logger.error("Error archivo vacio");
							}		
						
					}
				} catch(MissingResourceException e) {
					logger.error("Hubo un error durante la carga de archivo de cupones");
					throw e;
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("{}", HttpStatus.OK);
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

		
	/*public String sendFile() throws Exception {

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
						
							//String cleanFileName = files.get(0).getName();
							
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
	
	@RequestMapping(value = "fileUpload/upload", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

		try {
			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				FileUploadBean newFile = new FileUploadBean(filename, bytes, mimeType);
				System.out.println("------------------------------------------------");
				System.out.println("------ FILE:" + newFile.getFilename() + " ------");
				System.out.println("------------------------------------------------");

			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}*/

}
