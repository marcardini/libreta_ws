package com.libretaDigital.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.libretaDigital.beans.StudentAbsencesBean;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;

public interface AssistControlFacade {

	List<Student> getStudentsByGroupCode(String groupCode);
	
	List<StudentAbsencesBean> getStudentsAbsencesByCode(String groupCode);
	
	List<Group> getGroupsByProfessorId(BigInteger professorId);
	
	List<Student> getStudentsAndTodaysAssistance(String courseName, String groupCode, String subjectName);
	
}
