package com.libretaDigital.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.BulletinServiceImpl;
import com.libretaDigital.services.LoginServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;

@Controller
public class AssistControlController {

	@Autowired
	private AssistControlFacadeImpl assistControlFacade;
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	
	//ESTE BEAN ESTA ACA SOLO PARA PROBAR. BORRAR DESPUES
	@Autowired
	private LoginServiceImpl loginService;
	@Autowired
	private BulletinServiceImpl bulletinService;
	
	
	private String groupCode;
	private BigInteger professorId;
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/assistControl", method = RequestMethod.GET)
	public ModelAndView AssistControl() {
		ModelAndView page = new ModelAndView("assistControl");
		page.addObject("tituloPagina", "Libreta Digital - Control de Asistencias");
		page.addObject("codMenu", "C1");
		page.addObject("codMenu", "G1");
		
		Timestamp start_date = new Timestamp(System.currentTimeMillis());
		Timestamp end_date = new Timestamp(System.currentTimeMillis());
		
		bulletinService.generateBulletin(1L, start_date, end_date, 1L, 8, "buena conducta", false, 3);

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

}