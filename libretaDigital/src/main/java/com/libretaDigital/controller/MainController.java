package com.libretaDigital.controller;

import java.math.BigDecimal;
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
import com.libretaDigital.beans.StudentDayBean;
import com.libretaDigital.datatypes.StudentEventRegistration;

import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.services.UserServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

@Controller
public class MainController {

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {		
		System.out.println(this.getPrincipal());
		session.setAttribute("loggedUser", userService.getUser(this.getPrincipal()));
		
		System.out.println(session.getAttribute("loggedUser"));
		return new ModelAndView("index");
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


	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView helloWorld() {			
		return new ModelAndView("redirect:/index");
	}

	@RequestMapping(value = "/main/saveStudentDay", method = RequestMethod.POST)
	public void SaveAbsences(@RequestBody List<StudentDayBean> events, HttpServletResponse response) {
	
		try {
			List<StudentEventRegistration> studentsAssistanceRegistrationList = new ArrayList<StudentEventRegistration>();
			for (StudentDayBean aux : events) {
				StudentEventRegistration ser = new StudentEventRegistration();
				ser.setStudentId(aux.getStudentId());
				ser.setCourseId(1L);
				ser.setGroupId(1L);
				ser.setSubjectId(1L);
				ser.setComment(aux.getComment());
				if (aux.getOid() > 0) {
					ser.setClassDayStudentId(aux.getOid());
				}
				ser.setEventRegistrationType(EventRegistrationType.valueOf(aux.getEventRegistrationType()));
				if (ser.getEventRegistrationType() != EventRegistrationType.FALTA
						&& ser.getEventRegistrationType() != EventRegistrationType.MEDIA_FALTA
						&& ser.getEventRegistrationType() != EventRegistrationType.JUSTIFICADA) {
					ser.setValue(aux.getValue());
				} else {
					ser.setValue(BigDecimal.ZERO);
				}
				studentsAssistanceRegistrationList.add(ser);
			}
			studentService.saveStudentDay(studentsAssistanceRegistrationList, null);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/main/deleteStudentDay", method = RequestMethod.POST)
	public void DeleteStudentDay(@RequestBody List<Long> items, HttpServletResponse response) {		

		try {
			studentService.deleteStudentDay(items);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}
	
	
	public StudentServiceImpl getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	


}
