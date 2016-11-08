package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;
import com.libretaDigital.exceptions.*;

public interface IStudentService {

	List<Student> getAllStudents();

	void addStudent(Student dtStudent) throws StudentAlreadyExists, InvalidStudentInformation;
	
}
