package com.libretaDigital.entities;

import java.awt.Image;
import java.util.Date;
import java.util.List;

import com.libretaDigital.utils.*;

public class Professor extends Person {
	
	private String password;
	private Grade grade;
	private Date employeeSince;
	private List<Subject> subjectsList;
	
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
	
	public Professor(String password, String name, String lastName, Date birthDate, Image photo, Gender gender, String email, Date employeeSince){
		super(name, lastName, birthDate, photo, gender, email);
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Date getEmployeeSince() {
		return employeeSince;
	}

	public void setEmployeeSince(Date employeeSince) {
		this.employeeSince = employeeSince;
	}

	public List<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
}
