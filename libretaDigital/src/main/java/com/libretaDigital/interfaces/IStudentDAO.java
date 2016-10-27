package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;

public interface IStudentDAO extends IGenericDAO<Student>{
	
	List<Student> getAllStudents();
	
	Student getStudentByMail(final String email);
	
}
