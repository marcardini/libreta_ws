package com.libretaDigital.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

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
	public Student getStudent(Student student){
		return studentDAO.getStudentByMail(student.getEmail());
	}
	
	@Override
	public Student getStudentByEmail(String email){
		return studentDAO.getStudentByMail(email);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
	
	@Override
	public void addStudent(Student dtStudent) throws StudentAlreadyExists, InvalidStudentInformation {
		
//		if (studentDAO.getStudentByMail(dtStudent.getEmail()) != null)
//			throw new StudentAlreadyExists();
				
		studentDAO.saveOrUpdate(dtStudent);
		
	}
	
	@Override
	public void saveStudentDay(List<StudentEventRegistration> studentsAssistanceRegistrationList, Date date){
		
		if (date == null)
			date = new Date();
		

			for (StudentEventRegistration ser : studentsAssistanceRegistrationList) {
				
				ClassDayStudent cds = new ClassDayStudent(ser.getClassDayStudentId(), 
														  ser.getStudentId(),
														  ser.getCourseId(),
														  ser.getGroupId(), 
														  ser.getSubjectId(), 
														  date,
														  ser.getEventRegistrationType(),
														  ser.getValue(),
														  ser.getComment()
														);

				classDayStudentDAO.saveOrUpdate(cds);

			}
		
	}
	
	@Override
	public void deleteStudents(List<Long> items) {
		
		for (Long oid : items) {
			Student stud = studentDAO.getById(oid);
			if(stud != null){
				studentDAO.delete(stud);
			}
		}
	}
	
	@Override
	public void deleteStudentDay(List<Long> eventsIDs) {
		
		for (int i = 0; i < eventsIDs.size(); i++) {		
			ClassDayStudent cds = classDayStudentDAO.getById(eventsIDs.get(i));
			if (cds != null) {
				classDayStudentDAO.delete(cds);
			}			
		}				
	}
	
	@Override
	public List<Student> getStudentsFiles(String mail, String groupCode, int year, String subjectName){
		
		return studentDAO.getStudentsFiles(mail, groupCode, year, subjectName);
		
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
