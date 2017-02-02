package com.libretaDigital.testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.libretaDigital.DAO.ClassDayStudentDAO;
import com.libretaDigital.DAO.GroupDAO;
import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.assistControl.AssistControlFacadeImpl;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.exceptions.GroupAlreadyExists;
import com.libretaDigital.exceptions.InvalidGroupInformation;
import com.libretaDigital.exceptions.InvalidProfessorInformation;
import com.libretaDigital.exceptions.InvalidStudentInformation;
import com.libretaDigital.exceptions.ProfessorAlreadyExists;
import com.libretaDigital.exceptions.StudentAlreadyExists;
import com.libretaDigital.services.GroupServiceImpl;
import com.libretaDigital.services.ProfessorServiceImpl;
import com.libretaDigital.services.StudentServiceImpl;
import com.libretaDigital.utils.EventRegistrationType;
import com.libretaDigital.utils.Gender;
import com.libretaDigital.utils.Grade;

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
	
	@Autowired
	private ProfessorServiceImpl professorService;
	
	@Autowired
	private GroupServiceImpl groupService;
	
	private Group testGroup;
	private List<StudentEventRegistration> studentsAssistanceRegistrationList;
	
	private String currentGroupName = "testGroup3";
	private String student_1_name = "prueba 4 nombre";
	private String student_1_lastname = "prueba 4 apellido";
	private String student_2_name = "prueba 4 nombre";
	private String student_2_lastname = "prueba 4 apellido";
	private String groupNameOne = "grupo 1";
	private Professor newProfessor;
	private Student student;
	private Group group;
	private String assistanceControlGroupName = "1E";
	private String assistanceControlStudentName = "assistanceControlStudent_E";
	private String assistanceControlStudentMail = "assistanceControlStudent_E@gmail.com";
	

	//ABMs INDIVIDUALES comienzo
	@Test
	public void saveProfessor() throws ProfessorAlreadyExists, InvalidProfessorInformation{
		
		newProfessor = new Professor("name", "lastName", new Date(), Gender.PENDING, "testmail@gmail.com", "admin", Grade.GRADE_1, new Date());
		
		String email = newProfessor.getEmail();
		professorService.addProfessor(newProfessor);
		
		Professor professor = professorService.getByEmail(email);
		
		Assert.assertNotNull(professor);
	}
	
	@Test
	public void deleteProfessor(){
		
		String email = newProfessor.getEmail();
		List<Long> professorToDelete = new ArrayList<Long>();
		professorToDelete.add(newProfessor.getOid());
		professorService.deleteProfessors(professorToDelete);
		
		Professor professor = professorService.getByEmail(email);
		
		Assert.assertNull(professor);
	}
		
	@Test
	public void saveStudent() throws StudentAlreadyExists, InvalidStudentInformation{
		
		student = new Student(student_1_name, student_1_lastname);
		
		studentService.addStudent(student);
		
		Student studentObtained = studentService.getStudent(student);
		
		Assert.assertNotNull(studentObtained);
	}
	
	@Test
	public void deleteStudent(){
		
		String email = student.getEmail();
		List<Long> studentToDelete = new ArrayList<Long>();
		studentToDelete.add(student.getOid());
		studentService.deleteStudentDay(studentToDelete);
		
		Student studentObtained = studentService.getStudentByEmail(email);
		
		Assert.assertNull(studentObtained);
	}
	
	@Test
	public void saveGroup() throws GroupAlreadyExists, InvalidGroupInformation{
		
		group = new Group(groupNameOne, 2017);
		
		groupService.addGroup(group);
		
		Group groupObtained = groupService.getGroupByNameAndYear(group.getName(), group.getYear());
		
		Assert.assertNotNull(groupObtained);
	}
	
	@Test
	public void deleteGroup(){
		
		String name = group.getName();
		int year = group.getYear();
		List<Long> groupToDelete = new ArrayList<Long>();
		groupToDelete.add(student.getOid());

		groupService.deleteGroups(groupToDelete);
		
		Group groupObtained = groupService.getGroupByNameAndYear(name, year);
		
		Assert.assertNull(groupObtained);
	}
	
	//ABMs INDIVIDUALES fin
	
	
	
	//CONTROL DE ASISTENCIAS comienzo
	
	@Test
	public void getStudentsByGroupCode() {
		
		testGroup = new Group(currentGroupName,2017);
		
		List<Student> studentsOfTestGroup = new ArrayList<Student>();
		Student one = new Student(student_1_name, student_1_lastname);
		one.setGroupId(4L);
		Student two = new Student(student_2_name, student_2_lastname);
		two.setGroupId(4L);
		studentsOfTestGroup.add(one);
		studentsOfTestGroup.add(two);
		studentDAO.save(one);
		studentDAO.save(two);
		testGroup.setStudentsList(studentsOfTestGroup);
		
		groupDAO.save(testGroup);
		
		List<Student> result = assistControlFacade.getStudentsByGroupCode(currentGroupName);
		Assert.assertEquals(2, result.size());
	}
	
	@Test
	public void assistanceControlPersistanceTest() throws GroupAlreadyExists, InvalidGroupInformation, StudentAlreadyExists, InvalidStudentInformation {
		
		Group assistanceControlGroup = new Group(assistanceControlGroupName, 2017);
		groupService.addGroup(assistanceControlGroup);
		
		Student assistanceControlStudent = new Student(assistanceControlStudentName, "-", new Date(), Gender.PENDING, assistanceControlStudentMail, true, 
				assistanceControlGroup.getOid());
		studentService.addStudent(assistanceControlStudent);
		
		StudentEventRegistration abscense = new StudentEventRegistration(assistanceControlStudent.getOid(), 1L, assistanceControlGroup.getOid(), 1L, EventRegistrationType.FALTA);
		
		studentsAssistanceRegistrationList = new ArrayList<StudentEventRegistration>();
		studentsAssistanceRegistrationList.add(abscense);
		
		studentService.saveStudentDay(studentsAssistanceRegistrationList, new Date());
		
		Assert.assertEquals(1, studentDAO.getStudentsAndTodaysAssistance(assistanceControlGroupName, "MATEMATICAS").size());
	}

	//CONTROL DE ASISTENCIAS fin
	
	
	
	
	
	
	
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