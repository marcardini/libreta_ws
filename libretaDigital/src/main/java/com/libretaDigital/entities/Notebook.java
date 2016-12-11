package com.libretaDigital.entities;

import java.io.Serializable;

import com.libretaDigital.utils.CourseType;

public class Notebook implements Serializable{

	private static final long serialVersionUID = -8924297568264691767L;
	
	private Long oid;
	private int currentYear;
	private CourseType courseType;
	private String reformulationPlan;
	private Long professorOid;
	private Subject subject;
	private Long groupId;
	
	public Notebook(){}
	
	public Notebook(int currentYear, CourseType courseType, String reformulationPlan, Subject subject, Long groupId){
		this.currentYear = currentYear;
		this.courseType = courseType;
		this.reformulationPlan = reformulationPlan;
		this.subject = subject;
		this.groupId = groupId;
	}
	
	public Notebook(int currentYear, CourseType courseType, String reformulationPlan, Long professorOid, Subject subject, Long groupId){
		this.currentYear = currentYear;
		this.courseType = courseType;
		this.reformulationPlan = reformulationPlan;
		this.professorOid = professorOid;
		this.subject = subject;
		this.groupId = groupId;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Notebook))
			return false;
		Notebook that = (Notebook) other;
		return this.getCurrentYear() == that.getCurrentYear() && this.getCourseType().toString().equals(that.getCourseType().toString()) 
				&& this.reformulationPlan.equals(that.getReformulationPlan()) && this.getSubject().equals(that.getSubject());
	}
	
	@Override
	public String toString() {
		return "Materia: " + this.getSubject() + " | Anio: " + this.getCurrentYear(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + currentYear;
		result = prime * result + ((reformulationPlan == null) ? 0 : reformulationPlan.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}
	
	public int getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public String getReformulationPlan() {
		return reformulationPlan;
	}
	public void setReformulationPlan(String reformulationPlan) {
		this.reformulationPlan = reformulationPlan;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getProfessorOid() {
		return professorOid;
	}

	public void setProfessorOid(Long professorOid) {
		this.professorOid = professorOid;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
