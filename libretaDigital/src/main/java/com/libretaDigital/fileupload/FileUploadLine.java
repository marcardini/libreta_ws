package com.libretaDigital.fileupload;

import java.util.Date;

import com.libretaDigital.entities.Course;
import com.libretaDigital.entities.Group;
import com.libretaDigital.utils.Gender;
import com.libretaDigital.utils.Grade;


public class FileUploadLine extends FileLine{

	FileUploadLine (){}
	
	//Person
	private String name;
	private String lastName;
	private Date birthDate;
	private Gender gender;
	private String email;
	
	//Professor
	private String password;
	private Grade grade;
	private Date employeeSince;
	
	//Student
	private Course course;
	private Group group;
	private boolean currentStudent;
	
	//Group
	//private Course course;
	private String groupName;
	private int year;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public boolean isCurrentStudent() {
		return currentStudent;
	}
	public void setCurrentStudent(boolean currentStudent) {
		this.currentStudent = currentStudent;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}