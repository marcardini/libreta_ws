package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.Student;

public interface IStudentsDayFacade {

	public abstract List<Student> getStudentsByGroupCode(String groupCode);

	List<Student> getStudentsFiles(String mail, String courseName, String groupCode, int year, String subjectName);


}
