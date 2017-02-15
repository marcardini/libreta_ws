package com.libretaDigital.beans;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.libretaDigital.entities.Course;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Person;

@JsonAutoDetect
@JsonIgnoreProperties
public class ProfessorBean extends Person {

	private String password;
	private String grade;
	private Date employeeSince;
	private List<Course> coursesList;
	private List<Group> groupsList;
	private String subjectName;
	
	public ProfessorBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Course> getCoursesList() {
		return coursesList;
	}

		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getEmployeeSince() {
		return employeeSince;
	}

	public void setEmployeeSince(Date employeeSince) {
		this.employeeSince = employeeSince;
	}

	public List<Group> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(List<Group> groupsList) {
		this.groupsList = groupsList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	
}
