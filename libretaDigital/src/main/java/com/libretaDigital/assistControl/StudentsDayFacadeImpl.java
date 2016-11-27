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
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

}
