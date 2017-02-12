package com.libretaDigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.services.UserServiceImpl;

@Controller
public class PlanningController {
		
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Professor loguedProfessor;
	private String logguedUserName;

}
