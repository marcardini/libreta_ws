package com.libretaDigital.controller;


import java.util.Iterator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.libretaDigital.beans.FileUploadBean;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView FileUpload() {
		ModelAndView page = new ModelAndView("fileUpload");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos Masiva");
		page.addObject("codMenu", "D1");
		return page;
	}

	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	public ResponseEntity<Void> test() {
		// System.out.println("------------------------------------------------");
		// System.out.println("------------------------------------------------");
		// System.out.println("------------------------------------------------");
		// System.out.println("--- TEST ----");
		return ResponseEntity.ok().build();
	}

	@RequestMapping(
            value = "api/upload",
            method = RequestMethod.POST
        )
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
            }
            catch (Exception e) {
                return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }

}
