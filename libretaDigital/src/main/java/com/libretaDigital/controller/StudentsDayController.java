package com.libretaDigital.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller 
public class StudentsDayController {
	
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
		
//		try {			
//			page.addObject("students" , mapper.writeValueAsString(this.getStudentsByCode()));
//			page.addObject("groups", mapper.writeValueAsString(this.getGroupsByProfessor()));
//			page.addObject("studentsAbsences", mapper.writeValueAsString(this.getStudentsAbsencesByCode()));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		return page;
	}
	
}
