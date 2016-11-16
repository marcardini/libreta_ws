package com.libretaDigital.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;

public interface AssistControlFacade {

	List<Student> getStudentsByCode(String groupCode);
	
	List<Group> getGroupsByProfessorId(BigInteger professorId);
	
}
