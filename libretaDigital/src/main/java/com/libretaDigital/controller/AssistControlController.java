package com.libretaDigital.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.assistControl.AssistControlFacadeImpl;
import com.libretaDigital.beans.StudentAbsencesBean;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.entities.Subject;
import com.libretaDigital.services.BulletinServiceImpl;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.services.UserServiceImpl;
import com.libretaDigital.utils.DateConverter;

@Controller
public class AssistControlController {

	@Autowired
	private AssistControlFacadeImpl assistControlFacade;
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	@Autowired
	private DateConverter dateconverter;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private BulletinServiceImpl bulletinService;
	@Autowired
	private ProfessorServiceImpl professorServiceImpl;
	
	private String groupCode;
	private BigInteger professorId;
	private ObjectMapper mapper = new ObjectMapper();
	private Professor loguedProfessor;
	private String groupName;
	private String subjectName;

	@RequestMapping(value = "/assistControl", method = RequestMethod.GET)
	public ModelAndView AssistControl(HttpSession session) throws JsonProcessingException {
		ModelAndView page = new ModelAndView("assistControl");
		page.addObject("tituloPagina", "Libreta Digital - Control de Asistencias");
		page.addObject("codMenu", "C1");
		page.addObject("codMenu", "G1");
		
		System.out.println(this.getPrincipal());
		session.setAttribute("loggedUser", userService.getUser(this.getPrincipal()));
		loguedProfessor = professorServiceImpl.getByEmail(session.getAttribute("loggedUser").toString());
		
		page.addObject("logguedUserName", mapper.writeValueAsString(loguedProfessor.getEmail().toUpperCase()));
		
		System.out.println(session.getAttribute("loggedUser"));
		
		Timestamp start_date = null;
		Timestamp end_date = null;
		try {
			start_date = dateconverter.createTimestamp("2016/03/01");
			end_date = dateconverter.createTimestamp("2016/12/31");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			//cargamos grupo y materia suponiendo que el profesor tiene un solo grupo y una materia. la posicion 0 de ambas listas.
			List<Group> groupList = loguedProfessor.getGroupsList();
			if(groupList != null && groupList.size() > 0){
				groupName = groupList.get(0).getName();
				page.addObject("studentsAbsences", mapper.writeValueAsString(this.getStudentsAbsencesByCode()));
			
				Subject subject = loguedProfessor.getGroupsList().get(0).getSubjectsList().get(0);
				
				if(subject != null)
					subjectName = subject.getName();
			}
			
			if(groupName != null && !groupName.equals(""))
				page.addObject("groupName", mapper.writeValueAsString(groupName));
			else
				page.addObject("groupName", mapper.writeValueAsString("SIN GRUPO"));
			
			if(subjectName != null && !subjectName.equals(""))
				page.addObject("subjectName", mapper.writeValueAsString(subjectName));
			else
				page.addObject("subjectName", mapper.writeValueAsString("SIN MATERIA"));
			
			if(groupName != null && !groupName.equals("") && subjectName != null && !subjectName.equals(""))
				page.addObject("students", mapper.writeValueAsString(assistControlFacade.getStudentsAndTodaysAssistance(groupName, subjectName)));
			
			page.addObject("groups", mapper.writeValueAsString(this.getGroupsByProfessor()));
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping(value = "/assistControl/studentsAbsences", method = RequestMethod.GET)
	@ResponseBody
	public String StudentsAbsences() {
		String studentsAbsences= "[]";
		try {
			studentsAbsences = mapper.writeValueAsString(this.getStudentsAbsencesByCode());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return studentsAbsences;
	}
	
	

	public List<Student> getStudentsByCode() {
		return assistControlFacade.getStudentsByGroupCode(groupCode);
	}

	public List<StudentAbsencesBean> getStudentsAbsencesByCode() {
		return assistControlFacade.getStudentsAbsencesByCode(groupCode);
	}

	public List<Group> getGroupsByProfessor() {
		return assistControlFacade.getGroupsByProfessorId(professorId);
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public AssistControlFacadeImpl getAssistControlFacade() {
		return assistControlFacade;
	}

	public void setAssistControlFacade(AssistControlFacadeImpl assistControlFacade) {
		this.assistControlFacade = assistControlFacade;
	}

	public BigInteger getProfessorId() {
		return professorId;
	}

	public void setProfessorId(BigInteger professorId) {
		this.professorId = professorId;
	}

	
	public StudentServiceImpl getStudentServiceImpl() {
		return studentServiceImpl;
	}


	public void setStudentServiceImpl(StudentServiceImpl studentServiceImpl) {
		this.studentServiceImpl = studentServiceImpl;
	}


	public BulletinServiceImpl getBulletinService() {
		return bulletinService;
	}


	public void setBulletinService(BulletinServiceImpl bulletinService) {
		this.bulletinService = bulletinService;
	}


	public DateConverter getDateconverter() {
		return dateconverter;
	}


	public void setDateconverter(DateConverter dateconverter) {
		this.dateconverter = dateconverter;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public ProfessorServiceImpl getProfessorServiceImpl() {
		return professorServiceImpl;
	}

	public void setProfessorServiceImpl(ProfessorServiceImpl professorServiceImpl) {
		this.professorServiceImpl = professorServiceImpl;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}