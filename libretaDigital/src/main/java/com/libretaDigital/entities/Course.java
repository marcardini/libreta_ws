package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.List;


public class Course implements Serializable{

	private static final long serialVersionUID = -7836078126820934904L;
	
	private Long oid;
	private String name;
	//private List<Group> groupsList;
	private List<Subject> subjectsList;
	
	public Course(){}
	
	public Course(String name){
		this.name = name;
	}
	
	public Course(String name, List<Group> groups, List<Subject> subjects){
		this.name = name;
		//this.groupsList = groups;
		this.subjectsList = subjects;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Course))
			return false;
		Course that = (Course) other;
		return this.getOid().equals(that.getOid());
	}
	
	@Override
	public String toString() {
		return "Curso " + this.getOid() + ": " + this.subjectsList.size() + " materias.";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((groupsList == null) ? 0 : groupsList.hashCode());
		result = prime * result + ((subjectsList == null) ? 0 : subjectsList.hashCode());
		return result;
	}
	
//	public List<Group> getGroupsList() {
//		return groupsList;
//	}
//	public void setGroupsList(List<Group> groupsList) {
//		this.groupsList = groupsList;
//	}
	public List<Subject> getSubjectsList() {
		return subjectsList;
	}
	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
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
	
}
