package com.libretaDigital.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.libretaDigital.beans.StudentDayBean;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.services.LoginServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

@Controller
public class MainController {
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	
	//ESTE BEAN ESTA ACA SOLO PARA PROBAR. BORRAR DESPUES
	@Autowired
	private LoginServiceImpl loginService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {

		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView helloWorld() {

		String message = "<br><div style='text-align:center;'>" + "<h3>********** Hello World **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	
	@RequestMapping(value = "/main/saveEvent", method = RequestMethod.POST)
	public void SaveAbsences(@RequestBody List<StudentDayBean> absences, HttpServletResponse response) {	
		
		//ESTO ES PARA PROBAR EL METODO DE LA FICHA:
		/*List<Student> resultado = studentServiceImpl.getStudentsFiles(null, "primero", "1A", 2016, "MATEMATICAS");
		
		for(Student s: resultado){
			System.out.println(s.getName() + " " + s.getLastName());
		}*/
		
		
		
		//PARA PROBAR EL METODO DE LOGIN
		Professor user = loginService.validateUser("maria.tarigo@gmail.com", "admin");
		
		System.out.println("El usuario " + user.getEmail() + " ha sido logueado.");
		
		
		
		try{
			List<StudentEventRegistration> studentsAssistanceRegistrationList = new ArrayList<StudentEventRegistration> ();
			for (StudentDayBean aux : absences) {
				StudentEventRegistration ser = new StudentEventRegistration();
				ser.setStudentId(aux.getIdStudent());
				ser.setCourseId(1L);
				ser.setGroupId(1L);
				ser.setSubjectId(1L);
				ser.setClassDayStudentId(aux.getClassDayStudentId());
//				EventRegistrationType ert;
//				if(aux.isLate()){
//					ert = EventRegistrationType.MEDIA_FALTA;
//				}else{
//					ert = EventRegistrationType.FALTA;
//				}
				ser.setEventRegistrationType(EventRegistrationType.valueOf(aux.getEventRegistrationType().toString()));
				studentsAssistanceRegistrationList.add(ser);
			}
			studentServiceImpl.saveEvent(studentsAssistanceRegistrationList, null);	
			response.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
			
	}

}
