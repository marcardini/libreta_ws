package com.libretaDigital.entities;

import java.awt.Image;
import java.util.Date;
import java.util.List;

import com.libretaDigital.utils.Gender;

public class Student extends Person {

	private Course course;
	private Group group;
	private List<ClassDayStudent> calendar;
	private boolean currentStudent;
		
	public Student(String name, String lastName, Date birthDate, Gender gender, String email){
		super(name, lastName, birthDate, gender, email);
    }
	
	public Student(String name, String lastName, Date birthDate, Image photo, Gender gender, String email){
		super(name, lastName, birthDate, photo, gender, email);
    }
	
	public Student(Course course, Group group, boolean current, String name, String lastName, Date birthDate, Gender gender, String email){
		super(name, lastName, birthDate, gender, email);
		this.course = course;
		this.group = group;
		this.currentStudent = current;
	}
	
	public Student(Course course, Group group, boolean current, String name, String lastName, Date birthDate, Gender gender, String email, Image photo){
		super(name, lastName, birthDate, photo, gender, email);
		this.course = course;
		this.group = group;
		this.currentStudent = current;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

}
