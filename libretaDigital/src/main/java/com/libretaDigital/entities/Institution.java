package com.libretaDigital.entities;

import java.util.List;

public class Institution {

	private Long oid;
	private String name;
	private List<Course> courseList;
	private List<Group> groupsList;
	private List<Professor> professorsList;
	private List<Student> studendentsList;
	
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
		this.studendentsList = students;
	}
	
	public Institution(String name, List<Course> courses, List<Professor> professors, List<Student> students, List<Group> groups){
		this.name = name;
		this.courseList = courses;
		this.professorsList = professors;
		this.studendentsList = students;
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
	public List<Student> getStudendentsList() {
		return studendentsList;
	}
	public void setStudendentsList(List<Student> studendentsList) {
		this.studendentsList = studendentsList;
	}
	
}
