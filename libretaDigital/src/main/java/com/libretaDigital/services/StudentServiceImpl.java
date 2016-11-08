package com.libretaDigital.services;

import java.util.List;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.Student;
import com.libretaDigital.exceptions.*;
import com.libretaDigital.interfaces.*;

public class StudentServiceImpl implements IStudentService{

	private StudentDAO studentDAO;
	private MessageDigestPasswordEncoder encoder;
	
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
	
	public void addStudent(Student dtStudent) throws StudentAlreadyExists, InvalidStudentInformation {
		
		if (studentDAO.getStudentByMail(dtStudent.getEmail()) != null)
			throw new StudentAlreadyExists();
				
		studentDAO.save(dtStudent);
		
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
}
