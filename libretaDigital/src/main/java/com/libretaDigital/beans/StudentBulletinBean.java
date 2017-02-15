package com.libretaDigital.beans;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.entities.Student;

@JsonAutoDetect
public class StudentBulletinBean extends Student{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StudentSubjectBulletinBean> subjects;
	
	public List<StudentSubjectBulletinBean> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<StudentSubjectBulletinBean> subjects) {
		this.subjects = subjects;
	}
	
	public StudentBulletinBean() {
		// TODO Auto-generated constructor stub
	}

}
