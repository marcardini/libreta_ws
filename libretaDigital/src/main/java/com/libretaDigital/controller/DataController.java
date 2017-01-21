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
import com.libretaDigital.beans.ProfessorBean;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.GroupServiceImpl;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.Grade;

@Controller
public class DataController {
	
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ModelAndView data() {
		ModelAndView page = new ModelAndView("data");
		page.addObject("tituloPagina", "Libreta Digital - Carga de Datos");
		page.addObject("codMenu", "D2");
		
		try {
			page.addObject("professors", mapper.writeValueAsString(this.getAllProfessors()));
			page.addObject("students", mapper.writeValueAsString(this.getAllStudents()));
			page.addObject("groups", mapper.writeValueAsString(this.getAllGroups()));
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
							Grade.valueOf(item.getGrade()),item.getEmployeeSince());
				}else{
					 professor = new Professor( item.getName(), item.getLastName(), item.getBirthDate(), item.getGender(), item.getEmail(), item.getPassword(),
							Grade.valueOf(item.getGrade()),item.getEmployeeSince());
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
}
