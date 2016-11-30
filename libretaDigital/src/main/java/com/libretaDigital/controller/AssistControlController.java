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
import com.libretaDigital.beans.AbsenceBean;
import com.libretaDigital.beans.StudentAbsencesBean;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

@Controller
public class AssistControlController {

	@Autowired
	private AssistControlFacadeImpl assistControlFacade;
	@Autowired
	private StudentServiceImpl studentServiceImpl;

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
			page.addObject("students", mapper.writeValueAsString(assistControlFacade.getStudentsAndTodaysAssistance("primero", "1A", "MATEMATICAS")));
			page.addObject("groups", mapper.writeValueAsString(this.getGroupsByProfessor()));
			page.addObject("studentsAbsences", mapper.writeValueAsString(this.getStudentsAbsencesByCode()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@RequestMapping(value = "/assistControl/saveAbsences", method = RequestMethod.POST)
	public void SaveAbsences(@RequestBody List<AbsenceBean> absences, HttpServletResponse response) {	
		
		//ESTO ES PARA PROBAR EL METODO DE LA FICHA:
		/*List<Student> resultado = studentServiceImpl.getStudentsFiles(null, "primero", "1A", 2016, "MATEMATICAS");
		
		for(Student s: resultado){
			System.out.println(s.getName() + " " + s.getLastName());
		}*/
		
		try{
			List<StudentEventRegistration> studentsAssistanceRegistrationList = new ArrayList<StudentEventRegistration> ();
			for (AbsenceBean aux : absences) {
				StudentEventRegistration ser = new StudentEventRegistration();
				ser.setStudentId(aux.getIdStudent());
				ser.setCourseId(1L);
				ser.setGroupId(1L);
				ser.setSubjectId(1L);
				ser.setClassDayStudentId(aux.getClassDayStudentId());
				EventRegistrationType ert;
				if(aux.isLate()){
					ert = EventRegistrationType.HALF_ASSISTANCE;
				}else{
					ert = EventRegistrationType.INASSISTANCE;
				}
				ser.setEventRegistrationType(ert);
				studentsAssistanceRegistrationList.add(ser);
			}
			studentServiceImpl.assistanceControl(studentsAssistanceRegistrationList, null);	
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
			
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

}