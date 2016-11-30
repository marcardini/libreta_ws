package com.libretaDigital.services;

import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.libretaDigital.DAO.*;
import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.ClassDayStudent;
import com.libretaDigital.entities.Student;
import com.libretaDigital.exceptions.*;
import com.libretaDigital.interfaces.*;

public class StudentServiceImpl implements IStudentService{

	private StudentDAO studentDAO;
	private ClassDayStudentDAO classDayStudentDAO;
	private MessageDigestPasswordEncoder encoder;
	
	@Override
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
	
	@Override
	public void addStudent(Student dtStudent) throws StudentAlreadyExists, InvalidStudentInformation {
		
		if (studentDAO.getStudentByMail(dtStudent.getEmail()) != null)
			throw new StudentAlreadyExists();
				
		studentDAO.save(dtStudent);
		
	}
	
	@Override
	public void assistanceControl(List<StudentEventRegistration> studentsAssistanceRegistrationList, Date date){
		
		if(date == null)
			date = new Date();
		
		for(StudentEventRegistration ser : studentsAssistanceRegistrationList){
			ClassDayStudent cds = new ClassDayStudent(ser.getClassDayStudentId(), ser.getStudentId(), ser.getCourseId(), ser.getGroupId(), ser.getSubjectId(), date, ser.getEventRegistrationType());
			
			classDayStudentDAO.saveOrUpdate(cds);
		}
	}
	
	@Override
	public List<Student> getStudentsFiles(String mail, String courseName, String groupCode, int year, String subjectName){
		
		return studentDAO.getStudentsFiles(mail, courseName, groupCode, year, subjectName);
		
	}
	
	

	public MessageDigestPasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(MessageDigestPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public ClassDayStudentDAO getClassDayStudentDAO() {
		return classDayStudentDAO;
	}

	public void setClassDayStudentDAO(ClassDayStudentDAO classDayStudentDAO) {
		this.classDayStudentDAO = classDayStudentDAO;
	}
}
