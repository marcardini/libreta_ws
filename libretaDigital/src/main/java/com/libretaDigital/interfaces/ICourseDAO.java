package com.libretaDigital.interfaces;

import java.util.List;

import com.libretaDigital.entities.Course;

public interface ICourseDAO extends IGenericDAO<Course>{
	
	List<Course> getAllCourses();
	
}
