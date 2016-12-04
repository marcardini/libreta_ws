package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.libretaDigital.utils.*;

public class Professor extends Person implements Serializable{
	
	private static final long serialVersionUID = -6377924630397322562L;
	private String password;
	private Enum<Grade> grade;
	private Date employeeSince;
	private List<Course> coursesList;
	
	private Role role;
	
	public Professor(){}
	
	public Professor(String name, String lastName){
		super(name, lastName);
	}
		
	public Professor(String name, String lastName, Date birthDate, Gender gender){
		super(name, lastName, birthDate, gender);
	}
	
	public Professor(String password, String name, String lastName, Date birthDate, Gender gender, String email, Date employeeSince){
		super(name, lastName, birthDate, gender, email);
        this.password = password;
        this.employeeSince = employeeSince;
    }
	
	public Professor(String name, String lastName, String password, Date birthDate, String email, Gender gender, Date employeeSince){
		super(name, lastName, birthDate, gender, email);
        this.password = password;
        this.employeeSince = employeeSince;
    }
	
	public boolean validatePassword(String password) {
		return password.length() > 4 && password.length() < 20;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getEmployeeSince() {
		return employeeSince;
	}

	public void setEmployeeSince(Date employeeSince) {
		this.employeeSince = employeeSince;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Enum<Grade> getGrade() {
		return grade;
	}

	public void setGrade(Enum<Grade> grade) {
		this.grade = grade;
	}
}
