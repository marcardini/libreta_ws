package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class Notebook implements Serializable{

	private static final long serialVersionUID = -8924297568264691767L;
	
	private Long oid;
	private int currentYear;		
	private Long professorOid;
	private Subject subject;
	private Long groupId;
	private String pautaSalaDocente;
	private String propuestaDiagnostica;
	private String descripcionYAnalisis;
	private List<ClassDayProfessor> desarrolloDelCurso;
	private String programaYPautaExamen;
	
	
	public Notebook(){}
	
	public Notebook(int currentYear, Subject subject, Long groupId){
		this.currentYear = currentYear;		
		this.subject = subject;
		this.groupId = groupId;
	}
	
	public Notebook(int currentYear, Long professorOid, Subject subject, Long groupId){
		this.currentYear = currentYear;		
		this.professorOid = professorOid;
		this.subject = subject;
		this.groupId = groupId;		
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Notebook))
			return false;
		Notebook that = (Notebook) other;
		return this.getCurrentYear() == that.getCurrentYear()  
				&&  this.getSubject().equals(that.getSubject());
	}
	
	@Override
	public String toString() {
		return "Materia: " + this.getSubject() + " | Anio: " + this.getCurrentYear(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + currentYear;		
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}
	
	public int getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
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

	public String getPautaSalaDocente() {
		return pautaSalaDocente;
	}

	public void setPautaSalaDocente(String pautaSalaDocente) {
		this.pautaSalaDocente = pautaSalaDocente;
	}

	public String getPropuestaDiagnostica() {
		return propuestaDiagnostica;
	}

	public void setPropuestaDiagnostica(String propuestaDiagnostica) {
		this.propuestaDiagnostica = propuestaDiagnostica;
	}

	public String getDescripcionYAnalisis() {
		return descripcionYAnalisis;
	}

	public void setDescripcionYAnalisis(String descripcionYAnalisis) {
		this.descripcionYAnalisis = descripcionYAnalisis;
	}

	public List<ClassDayProfessor> getDesarrolloDelCurso() {
		return desarrolloDelCurso;
	}

	public void setDesarrolloDelCurso(List<ClassDayProfessor> desarrolloDelCurso) {
		this.desarrolloDelCurso = desarrolloDelCurso;
	}

	public String getProgramaYPautaExamen() {
		return programaYPautaExamen;
	}

	public void setProgramaYPautaExamen(String programaYPautaExamen) {
		this.programaYPautaExamen = programaYPautaExamen;
	}
}
