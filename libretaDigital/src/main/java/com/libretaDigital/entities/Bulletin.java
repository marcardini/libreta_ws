package com.libretaDigital.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Bulletin implements Serializable{

	/*BOLETINES:
	 * Se genera uno por materia y periodo. Para emitir el boletin final de curso, se consideran todos los del año para
	 * ese alumno y se obtiene el promedio. Tambien debemos guardarlo. Este ultimo se guarda sin id de materia.
	 * Por eso el atributo esta fuera del equals.
	 * */
	private static final long serialVersionUID = -4285731517983323733L;
	
	private Long oid;
	private Long studentId;
	private Timestamp startDate;
	private Timestamp endDate;
	private Long subjectId;
	private int grade;
	private String comment;
	private Boolean finalBulletin;
	
	public Bulletin(){}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Bulletin))
			return false;
		Bulletin that = (Bulletin) other;
		return this.getStudentId() == that.getStudentId() && this.getSubjectId() == that.getSubjectId() &&
				this.getStartDate().equals(that.getStartDate()) && this.getEndDate().equals(that.getEndDate());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		return result;
	}

	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getFinalBulletin() {
		return finalBulletin;
	}
	public void setFinalBulletin(Boolean finalBulletin) {
		this.finalBulletin = finalBulletin;
	}
}
