package com.libretaDigital.interfaces;

import java.util.Date;
import java.util.List;

import com.libretaDigital.datatypes.StudentEventRegistration;
import com.libretaDigital.entities.*;
import com.libretaDigital.exceptions.*;

public interface IStudentService {

	List<Student> getAllStudents();

	void addStudent(Student dtStudent) throws StudentAlreadyExists, InvalidStudentInformation;
	
	void assistanceControl(List<StudentEventRegistration> studentsAssistanceRegistrationList, Date date);
	
	List<Student> getStudentsFiles(String mail, String groupCode, int year, String subjectName);
	
}
