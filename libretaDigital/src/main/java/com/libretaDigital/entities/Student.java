package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.Gender;

@JsonAutoDetect
public class Student extends Person implements Serializable{

	private static final long serialVersionUID = -6696037101000500210L;
	
	private Course course;
	private Long groupId;
	private List<ClassDayStudent> calendar;
	private boolean currentStudent;
	
	public Student(){}
	
	public Student(String name, String lastName){
		super(name, lastName);
	}
	
	public Student(String name, String lastName, Long groupId){
		super(name, lastName);
		this.groupId = groupId;
	}
	
	public Student(String name, String lastName, Date birthDate, byte[] photo, Gender gender, String email){
		super(name, lastName, birthDate, photo, gender, email);
    }
	
	public Student(String name, String lastName, Date birthDate, Gender gender, String email, boolean currentStudent){
		super(name, lastName, birthDate, gender, email);
		this.currentStudent = currentStudent;
    }
	
	public Student(String name, String lastName, Date birthDate, Gender gender, String email, boolean current, Long groupId){
		super(name, lastName, birthDate, gender, email);
		this.groupId = groupId;
		this.currentStudent = current;
	}
	
	public Student(Course course, Long groupId, boolean current, String name, String lastName, Date birthDate, Gender gender, String email, byte[] photo){
		super(name, lastName, birthDate, photo, gender, email);
		this.course = course;
		this.groupId = groupId;
		this.currentStudent = current;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<ClassDayStudent> getCalendar() {
		return calendar;
	}
	public void setCalendar(List<ClassDayStudent> calendar) {
		this.calendar = calendar;
	}
	public boolean getCurrentStudent() {
		return currentStudent;
	}
	public void setCurrentStudent(boolean currentStudent) {
		this.currentStudent = currentStudent;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}