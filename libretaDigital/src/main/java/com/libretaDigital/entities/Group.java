package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Group implements Serializable{

	private static final long serialVersionUID = -4285731517983323733L;
	
	private Long oid;
	private Course course;
	private String name;
	private int year;
	private List<Student> studentsList;
	private List<Subject> subjectsList;
	//private List<Period> periodsList;
	
	public Group(){}
	
	public Group(String name, int year){
		this.name = name;
		this.year = year;
	}
	
	public Group(Course course, String name, int year){
		this.course = course;
		this.name = name;
		this.year = year;
	}
	
	public Group(Course couse, String name, int year, List<Student> studentsList){
		this.course = couse;
		this.name = name;
		this.year = year;
		this.studentsList = studentsList;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Group))
			return false;
		Group that = (Group) other;
		return this.getCourse().equals(that.getCourse()) && this.getName().equals(that.getName()) && this.getYear() == that.getYear();
	}
	
	@Override
	public String toString() {
		return this.getName() + "-" + this.getCourse();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((studentsList == null) ? 0 : studentsList.hashCode());
		return result;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Student> getStudentsList() {
		return studentsList;
	}
	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public List<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}

	
}
