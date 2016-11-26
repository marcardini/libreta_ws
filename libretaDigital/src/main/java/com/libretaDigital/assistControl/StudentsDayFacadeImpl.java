package com.libretaDigital.assistControl;

import java.util.List;

import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.entities.Student;
import com.libretaDigital.interfaces.IStudentsDayFacade;

public class StudentsDayFacadeImpl implements IStudentsDayFacade {

private StudentDAO studentDAO;	
	
	@Override
	public List<Student> getStudentsByGroupCode(String groupCode) {
		return studentDAO.getStudentsByGroupCode(groupCode);
	}
	
	@Override
	public List<Student> getStudentsFiles(String mail, String courseName, String groupCode, int year, String subjectName){		
		return studentDAO.getStudentsFiles(mail, courseName, groupCode, year, subjectName);		
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

}
