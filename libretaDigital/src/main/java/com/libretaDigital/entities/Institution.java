package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class Institution implements Serializable{

	private static final long serialVersionUID = -6207875716963653205L;
	
	private Long oid;
	private String name;
	private List<Course> courseList;
	private List<Group> groupsList;
	private List<Professor> professorsList;
	private List<Student> studentsList;
	
	public Institution(){}
	
	public Institution(String name){
		this.name = name;
	}
	
	public Institution(String name, List<Course> courses){
		this.name = name;
		this.courseList = courses;
	}
	
	public Institution(String name, List<Course> courses, List<Professor> professors){
		this.name = name;
		this.courseList = courses;
		this.professorsList = professors;
	}
	
	public Institution(String name, List<Course> courses, List<Professor> professors, List<Student> students){
		this.name = name;
		this.courseList = courses;
		this.professorsList = professors;
		this.studentsList = students;
	}
	
	public Institution(String name, List<Course> courses, List<Professor> professors, List<Student> students, List<Group> groups){
		this.name = name;
		this.courseList = courses;
		this.professorsList = professors;
		this.studentsList = students;
		this.groupsList = groups;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Institution))
			return false;
		Institution that = (Institution) other;
		return this.getName().equals(that.getName());
	}
	
	@Override
	public String toString() {
		return this.getName().toUpperCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public List<Group> getGroupsList() {
		return groupsList;
	}
	public void setGroupsList(List<Group> groupsList) {
		this.groupsList = groupsList;
	}
	public List<Professor> getProfessorsList() {
		return professorsList;
	}
	public void setProfessorsList(List<Professor> professorsList) {
		this.professorsList = professorsList;
	}

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}

	
}
