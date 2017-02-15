package com.libretaDigital.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Notebook;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Subject;
import com.libretaDigital.services.GroupServiceImpl;
import com.libretaDigital.services.NotebookServiceImpl;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.SubjectServiceImpl;
import com.libretaDigital.services.UserServiceImpl;

@Controller
public class PlanningController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private NotebookServiceImpl notebookService;

	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;

	@Autowired
	private SubjectServiceImpl subjectServiceImpl;
	
	private ObjectMapper mapper = new ObjectMapper();

	private Professor loguedProfessor;
	private String logguedUserName;

	@RequestMapping(value = "/planning", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) throws JsonProcessingException {

		ModelAndView page = new ModelAndView("/planning");
		session.setAttribute("loggedUser", userService.getUser(this.getPrincipal()));
		session.setAttribute("logguedUserName", userService.getUser(this.getPrincipal()).getLastName());
		loguedProfessor = professorServiceImpl.getByEmail(session.getAttribute("loggedUser").toString());		

		List<Group> professorsGroup = groupServiceImpl.getProfessorsGroup(loguedProfessor);
		List<Subject> subjectsList = new ArrayList<Subject>();
		if(professorsGroup != null && professorsGroup.size() > 0)
			subjectsList = subjectServiceImpl.getSubjectsByGroupIdAndProfessorId(professorsGroup.get(0).getOid(), loguedProfessor.getOid());
		
		long subjectId = 0L;
		if(subjectsList != null && subjectsList.size() > 0)
			subjectId = subjectsList.get(0).getOid();
		
		page.addObject("notebook", mapper.writeValueAsString(notebookService.getNotebooksListFromSubjectIdAndProfessorId(subjectId, loguedProfessor.getOid())));
		page.addObject("logguedUserName", mapper.writeValueAsString(loguedProfessor.getEmail().toUpperCase()));
		page.addObject("tituloPagina", "Libreta Digital - Planning");
		page.addObject("codMenu", "G3");
		return page;
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

	@RequestMapping(value = "/planning/saveNotebook", method = RequestMethod.POST)
	public void saveProfessor(@RequestBody Notebook notebook, HttpServletResponse response) {

		try {

			if (notebook != null) {
				notebookService.saveNotebook(notebook);
				response.setStatus(HttpServletResponse.SC_OK);
			}else{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}			
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
