package com.libretaDigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;
import com.libretaDigital.fileupload.AssistControlFacadeImpl;

@Controller
public class AssistControlController {
	
	@Autowired
	private AssistControlFacadeImpl assistControlFacadeImpl;
	
	private String groupCode;
	private Long professorId;
	
	@RequestMapping(value = "/assistControl", method = RequestMethod.GET)
	public ModelAndView FileUpload() {
		ModelAndView page = new ModelAndView("assistControl");
		page.addObject("tituloPagina", "Libreta Digital - Control de Asistencias");
		page.addObject("codMenu", "C1");
		return page;
	}
	
	public List<Student> getStudentsByCode(){
		return assistControlFacadeImpl.getStudentsByCode(groupCode);
	}
	
	public List<Group> getGroupsByProfessor(){
		return assistControlFacadeImpl.getGroupsByProfessorId(professorId);
	}
	
	
	
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public AssistControlFacadeImpl getAssistControlFacadeImpl() {
		return assistControlFacadeImpl;
	}
	public void setAssistControlFacadeImpl(AssistControlFacadeImpl assistControlFacadeImpl) {
		this.assistControlFacadeImpl = assistControlFacadeImpl;
	}
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

}