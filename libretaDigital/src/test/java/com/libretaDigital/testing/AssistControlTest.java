package com.libretaDigital.testing;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.libretaDigital.DAO.ClassDayStudentDAO;
import com.libretaDigital.DAO.GroupDAO;
import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.assistControl.AssistControlFacadeImpl;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:test-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AssistControlTest {
	
	@Autowired
	private AssistControlFacadeImpl assistControlFacade;
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ClassDayStudentDAO classDayStudentDAO;
	
	private Group testGroup;
	private Student testStudent;
	private List<StudentEventRegistration> studentsAssistanceRegistrationList;
	
	@Before
	public void initObjects(){
		testGroup = new Group("testGroup", 1);
		groupDAO.save(testGroup);
		
		//testStudent = new Student("testStudentName", "testStudentLastName", testGroup);
		studentDAO.save(testStudent);
		
		Long studentId = studentDAO.getStudentByMail(testStudent.getEmail()).getOid().longValue();
		
		//FIXME
		Long courseId = 1L;
		
		StudentEventRegistration testStudentEventReg = new StudentEventRegistration(null, studentId, courseId, null, null, EventRegistrationType.FALTA);
		studentsAssistanceRegistrationList.add(testStudentEventReg);
	}
	
	@After
	public void deleteTestObjects(){
		groupDAO.delete(testGroup);
		studentDAO.delete(testStudent);
	}
	
	@Test
	public void getStudentsByGroupCode() {
		List<Student> result = assistControlFacade.getStudentsByGroupCode("testGroup");
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void assistanceControlPersistanceTest() {
		studentService.saveStudentDay(studentsAssistanceRegistrationList, new Date());
	}

	
	public AssistControlFacadeImpl getAssistControlFacade() {
		return assistControlFacade;
	}
	public void setAssistControlFacade(AssistControlFacadeImpl assistControlFacade) {
		this.assistControlFacade = assistControlFacade;
	}
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	public StudentServiceImpl getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}
	public ClassDayStudentDAO getClassDayStudentDAO() {
		return classDayStudentDAO;
	}
	public void setClassDayStudentDAO(ClassDayStudentDAO classDayStudentDAO) {
		this.classDayStudentDAO = classDayStudentDAO;
	}
}