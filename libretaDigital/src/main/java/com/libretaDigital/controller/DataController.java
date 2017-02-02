package com.libretaDigital.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.beans.ProfessorBean;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.fileupload.FileUploadFacadeImpl;
import com.libretaDigital.fileupload.FileUtilities;
import com.libretaDigital.services.GroupServiceImpl;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.Grade;

@Controller
public class DataController {
	
	private Logger logger = Logger.getLogger(DataController.class);
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	@Autowired
	private FileUploadFacadeImpl fileUploadFacadeImpl;
	
	private Professor loguedProfessor;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static String CATALINA_HOME = System.getenv("CATALINA_HOME");
	
	private byte[] photo;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ModelAndView data(HttpSession session) {
		ModelAndView page = new ModelAndView("data");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos");
		page.addObject("codMenu", "D2");
		
		try {
			page.addObject("professors", mapper.writeValueAsString(this.getAllProfessors()));
			page.addObject("students", mapper.writeValueAsString(this.getAllStudents()));
			page.addObject("groups", mapper.writeValueAsString(this.getAllGroups()));
			loguedProfessor = professorServiceImpl.getByEmail(session.getAttribute("loggedUser").toString());
			page.addObject("logguedUserName", mapper.writeValueAsString(loguedProfessor.getEmail().toUpperCase()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
		return page;
	}
	
	@RequestMapping(value = "/data/professors", method = RequestMethod.GET)
	@ResponseBody
	public String Professors() {
		String professors = "[]";
		try {
			professors = mapper.writeValueAsString(this.getAllProfessors());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return professors;
	}
	
	@RequestMapping(value = "/data/deleteProfessor", method = RequestMethod.POST)
	public void DeleteProfessor(@RequestBody List<Long> items, HttpServletResponse response) {
		
		try{			
			professorServiceImpl.deleteProfessors(items);	
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/data/saveProfessors", method = RequestMethod.POST)
	public void saveProfessor(@RequestBody List<ProfessorBean> items, HttpServletResponse response) {
		Professor professor = null;
		
		try{			
			for (ProfessorBean item : items) {
				if(item.getOid() == null){
					 
					 professor = new Professor(item.getName(), item.getLastName(), item.getBirthDate(), item.getGender(), item.getEmail(), item.getPassword(),
								Grade.valueOf(item.getGrade()),item.getEmployeeSince()/* photo*/);
				}else{
					 professor = new Professor(item.getName(), item.getLastName(), item.getBirthDate(), item.getGender(), item.getEmail(), item.getPassword(),
							Grade.valueOf(item.getGrade()),item.getEmployeeSince()/*, photo*/);
					professor.setOid(item.getOid());
				}
				
				professorServiceImpl.addProfessor(professor);
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	
	public List<Professor> getAllProfessors(){
		return professorServiceImpl.getAllProfessors();
	}
	
	
	
	
	/*****/
	
	
	
	@RequestMapping(value = "/data/groups", method = RequestMethod.GET)
	@ResponseBody
	public String Groups() {
		String groups = "[]";
		try {
			groups = mapper.writeValueAsString(this.getAllGroups());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return groups;
	}
	
	@RequestMapping(value = "/data/deleteGroup", method = RequestMethod.POST)
	public void DeleteGroup(@RequestBody List<Long> items, HttpServletResponse response) {
		
		try{			
			groupServiceImpl.deleteGroups(items);	
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/data/saveGroups", method = RequestMethod.POST)
	public void saveGroup(@RequestBody List<Group> items, HttpServletResponse response) {
		
		try{			
			for (Group group : items) {
				groupServiceImpl.addGroup(group);
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	
	public List<Group> getAllGroups(){
		return groupServiceImpl.getAllGroups();
	}
	
	
	
	
/*****/
	
	
	
	@RequestMapping(value = "/data/students", method = RequestMethod.GET)
	@ResponseBody
	public String Students() {
		String students = "[]";
		try {
			students = mapper.writeValueAsString(this.getAllStudents());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return students;
	}
	
	@RequestMapping(value = "/data/deleteStudent", method = RequestMethod.POST)
	public void DeleteStudent(@RequestBody List<Long> items, HttpServletResponse response) {
		
		try{			
			studentServiceImpl.deleteStudents(items);	
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/data/saveStudents", method = RequestMethod.POST)
	public void saveStudent(@RequestBody List<Student> items, HttpServletResponse response) {
		
		try{			
			for (Student student : items) {
				studentServiceImpl.addStudent(student);
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	
	public List<Student> getAllStudents(){
		return studentServiceImpl.getAllStudents();
	}
	
	
	/*
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "dataProfessor/uploadPhoto", method = RequestMethod.POST)
	
	public ResponseEntity uploadFile(MultipartHttpServletRequest request, @RequestParam(value="type", required=true) String type) {
		try {
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				photo = request.getFile(uploadedFile);
				
				try {
					if (CATALINA_HOME == null || CATALINA_HOME.equals("")) {						
						this.clearUploadData();
						logger.error("la variable CATALINA_HOME no esta seteada");
						return new ResponseEntity<>("{}", HttpStatus.EXPECTATION_FAILED);						
					} else {
						ResourceBundle rb = ResourceBundle.getBundle("messages_es");
						String uploadDirectory = CATALINA_HOME.replace("\\", "/") + rb.getString("upload_tomcat_directoy");							
							try {
								if (photo.getSize() > 0) {
									FileUtilities.copyFile(photo, uploadDirectory);
									String localPort = rb.getString("service.port");
									String http_address = rb.getString("http_address");
									String tomcat_address = http_address+":"+ localPort + "/files/";																	
									fileUploadFacadeImpl.fileUpload(tomcat_address + photo.getName(), photo.getName(), "admin", null);										
								} else
									logger.error("Error archivo vacio");
							} catch (IOException e) {
								logger.error("Error archivo vacio");
							}		
						}
				} catch(MissingResourceException e) {
					logger.error("Hubo un error durante la carga de foto del profesor");
					throw e;
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
*/
	
	
	public void clearUploadData() {
		photo = null;
	}
	

	public StudentServiceImpl getStudentServiceImpl() {
		return studentServiceImpl;
	}

	public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
		this.studentServiceImpl = studentServiceImpl;
	}

	public ProfessorServiceImpl getProfessorServiceImpl() {
		return professorServiceImpl;
	}

	public void setProfessorServiceImpl(ProfessorServiceImpl professorServiceImpl) {
		this.professorServiceImpl = professorServiceImpl;
	}

	public GroupServiceImpl getGroupServiceImpl() {
		return groupServiceImpl;
	}

	public void setGroupServiceImpl(GroupServiceImpl groupServiceImpl) {
		this.groupServiceImpl = groupServiceImpl;
	}

	public Professor getLoguedProfessor() {
		return loguedProfessor;
	}

	public void setLoguedProfessor(Professor loguedProfessor) {
		this.loguedProfessor = loguedProfessor;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public FileUploadFacadeImpl getFileUploadFacadeImpl() {
		return fileUploadFacadeImpl;
	}

	public void setFileUploadFacadeImpl(FileUploadFacadeImpl fileUploadFacadeImpl) {
		this.fileUploadFacadeImpl = fileUploadFacadeImpl;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public static String getCATALINA_HOME() {
		return CATALINA_HOME;
	}

	public static void setCATALINA_HOME(String cATALINA_HOME) {
		CATALINA_HOME = cATALINA_HOME;
	}
}
