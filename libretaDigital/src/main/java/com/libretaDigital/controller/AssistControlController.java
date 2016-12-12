package com.libretaDigital.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
import com.libretaDigital.assistControl.AssistControlFacadeImpl;
import com.libretaDigital.beans.StudentDayBean;
import com.libretaDigital.beans.StudentAbsencesBean;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.LoginServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

@Controller
public class AssistControlController {

	@Autowired
	private AssistControlFacadeImpl assistControlFacade;
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	
	//ESTE BEAN ESTA ACA SOLO PARA PROBAR. BORRAR DESPUES
	@Autowired
	private LoginServiceImpl loginService;
	
	
	private String groupCode;
	private BigInteger professorId;
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/assistControl", method = RequestMethod.GET)
	public ModelAndView AssistControl() {
		ModelAndView page = new ModelAndView("assistControl");
		page.addObject("tituloPagina", "Libreta Digital - Control de Asistencias");
		page.addObject("codMenu", "C1");
		page.addObject("codMenu", "G1");
		
		groupCode = "1A";
		professorId = BigInteger.ONE;

		try {
			page.addObject("students", mapper.writeValueAsString(assistControlFacade.getStudentsAndTodaysAssistance("1A", "MATEMATICAS")));
			page.addObject("groups", mapper.writeValueAsString(this.getGroupsByProfessor()));
			page.addObject("studentsAbsences", mapper.writeValueAsString(this.getStudentsAbsencesByCode()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	
	@RequestMapping(value = "/assistControl/studentsAbsences", method = RequestMethod.GET)
	@ResponseBody
	public String StudentsAbsences() {
		String studentsAbsences = "[]";
		try {
			studentsAbsences = mapper.writeValueAsString(this.getStudentsAbsencesByCode());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
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

	public LoginServiceImpl getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}

}