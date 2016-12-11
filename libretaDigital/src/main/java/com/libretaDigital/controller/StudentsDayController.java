package com.libretaDigital.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.assistControl.StudentsDayFacadeImpl;
import com.libretaDigital.entities.Student;

@Controller 
public class StudentsDayController {
	
	@Autowired
	private StudentsDayFacadeImpl studentsDayFacade;
	
	private String groupCode;
	private long professorId;
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/studentsDay", method = RequestMethod.GET)
	public ModelAndView AssistControl() {
		ModelAndView page = new ModelAndView("studentsDay");
		page.addObject("tituloPagina", "Libreta Digital - Estudiantes");
		page.addObject("codMenu", "G2");
		
		
		
		groupCode = "1A";
		professorId = 1;
		
		try {			
			page.addObject("students" , mapper.writeValueAsString(this.getStudentsFiles()));
//			page.addObject("groups", mapper.writeValueAsString(this.getGroupsByProfessor()));
//			page.addObject("studentsAbsences", mapper.writeValueAsString(this.getStudentsAbsencesByCode()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return page;
	}
	
	public List<Student> getStudentsFiles() {
		//return studentsDayFacade.getStudentsFiles(mail, courseName, groupCode, year, subjectName)
		return studentsDayFacade.getStudentsFiles(null, "1A", 2016, "MATEMATICAS");
	}
	
	public List<Student> getStudentsByCode() {
		return studentsDayFacade.getStudentsByGroupCode(groupCode);
	}

	public StudentsDayFacadeImpl getStudentsDayFacade() {
		return studentsDayFacade;
	}

	public void setStudentsDayFacade(StudentsDayFacadeImpl studentsDayFacade) {
		this.studentsDayFacade = studentsDayFacade;
	}
	

	
}
