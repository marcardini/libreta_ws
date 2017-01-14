package com.libretaDigital.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Professor;

import com.libretaDigital.services.ProfessorServiceImpl;

@Controller
public class DataController {
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ModelAndView data() {
		ModelAndView page = new ModelAndView("data");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos");
		page.addObject("codMenu", "D2");
		
		try {
			page.addObject("professors", mapper.writeValueAsString(this.getAllProfessors()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
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
	public void saveProfessor(@RequestBody List<Professor> items, HttpServletResponse response) {
		
		try{			
			for (Professor professor : items) {
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
}
