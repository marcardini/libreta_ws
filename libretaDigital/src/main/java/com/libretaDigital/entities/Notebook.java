package com.libretaDigital.entities;

import java.util.List;

import com.libretaDigital.utils.CourseType;

public class Notebook {

	private Long oid;
	private int currentYear;
	private CourseType courseType;
	private String reformulationPlan;
	private List<Professor> professors;
	private Subject subject;
	private Group group;
	
	public Notebook(){}
	
	public Notebook(int currentYear, CourseType courseType, String reformulationPlan, Subject subject, Group group){
		this.currentYear = currentYear;
		this.courseType = courseType;
		this.reformulationPlan = reformulationPlan;
		this.subject = subject;
		this.group = group;
	}
	
	public Notebook(int currentYear, CourseType courseType, String reformulationPlan, List<Professor> professors, Subject subject, Group group){
		this.currentYear = currentYear;
		this.courseType = courseType;
		this.reformulationPlan = reformulationPlan;
		this.professors = professors;
		this.subject = subject;
		this.group = group;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Notebook))
			return false;
		Notebook that = (Notebook) other;
		return this.getCurrentYear() == that.getCurrentYear() && this.getCourseType().toString().equals(that.getCourseType().toString()) 
				&& this.reformulationPlan.equals(that.getReformulationPlan()) && this.getSubject().equals(that.getSubject()) 
				&& this.getGroup().equals(that.getGroup());
	}
	
	@Override
	public String toString() {
		String professorsNames = "";
		for(Professor p : this.getProfessors()){
			professorsNames.concat(p.getLastName() + "," + p.getName() + ". ");
		}
		if(professorsNames.equals(""))
			professorsNames = "No hay profesores registrados aun";
		
		return "Materia: " + this.getSubject() + " | Grupo: " + this.getGroup().getName() + " | Anio: " + this.getCurrentYear() + " | Profesores: " + professorsNames; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + currentYear;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
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
	public List<Professor> getProfessors() {
		return professors;
	}
	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}
}
