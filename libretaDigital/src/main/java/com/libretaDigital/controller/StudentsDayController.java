package com.libretaDigital.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.assistControl.StudentsDayFacadeImpl;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.services.UserServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

@Controller 
public class StudentsDayController {
	
	@Autowired
	private StudentsDayFacadeImpl studentsDayFacade;
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	@Autowired
	private UserServiceImpl userService;
	
	private String groupCode;
	private long professorId;
	private Professor loguedProfessor;
	private String groupName;
	private String subjectName;
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/studentsDay", method = RequestMethod.GET)
	public ModelAndView AssistControl(HttpSession session) throws JsonProcessingException {
		ModelAndView page = new ModelAndView("studentsDay");
		page.addObject("tituloPagina", "Libreta Digital - Estudiantes");
		page.addObject("codMenu", "G2");
		loguedProfessor = professorServiceImpl.getByEmail(session.getAttribute("loggedUser").toString());
		page.addObject("logguedUserName", mapper.writeValueAsString(loguedProfessor.getEmail().toUpperCase()));
		
		System.out.println(this.getPrincipal());
		session.setAttribute("loggedUser", userService.getUser(this.getPrincipal()));
		System.out.println(session.getAttribute("loggedUser"));
		
		try {			
			
			List<EventRegistrationType> eventsRegistrationTypes = Arrays.asList(EventRegistrationType.values());
			page.addObject("eventsRegistrationTypes" , mapper.writeValueAsString(eventsRegistrationTypes));
			page.addObject("professors", mapper.writeValueAsString(this.getAllProfessors()));
			
			groupName = loguedProfessor.getGroupsList().get(0).getName();
			subjectName = loguedProfessor.getGroupsList().get(0).getSubjectsList().get(0).getName();
			page.addObject("groupName", mapper.writeValueAsString(groupName));
			page.addObject("subjectName", mapper.writeValueAsString(subjectName));
			
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);  
			page.addObject("students" , mapper.writeValueAsString(this.getStudentsFiles(null, groupName, year, subjectName)));
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
		return page;
	}

	public List<Student> getStudentsFiles(String mail, String groupCode, int year, String subjectName) {
		return studentsDayFacade.getStudentsFiles(mail, groupCode, year, subjectName);
	}
	
	public List<Professor> getAllProfessors(){
		return professorServiceImpl.getAllProfessors();
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Professor getLoguedProfessor() {
		return loguedProfessor;
	}
	public void setLoguedProfessor(Professor loguedProfessor) {
		this.loguedProfessor = loguedProfessor;
	}
	public UserServiceImpl getUserService() {
		return userService;
	}
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}	
}