package com.libretaDigital.beans;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.entities.Student;

@JsonAutoDetect
public class StudentBulletinBean extends Student{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StudentSubjectBulletinBean> subjectsRows = new ArrayList<StudentSubjectBulletinBean>();
	
	public List<StudentSubjectBulletinBean> getSubjects() {
		return subjectsRows;
	}
	public void setSubjects(List<StudentSubjectBulletinBean> subjects) {
		this.subjectsRows = subjects;
	}
	
	public StudentBulletinBean() {
		// TODO Auto-generated constructor stub
	}

}
