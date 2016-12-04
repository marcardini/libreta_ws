package com.libretaDigital.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class ClassDayStudent extends ClassDay implements Serializable{
	
	private static final long serialVersionUID = -1682883898530367171L;
	
	private Long studentId;
	private Long courseId;
	private Long groupId;
	private Long subjectId;
	private Enum<EventRegistrationType> eventRegistrationType;
	private BigDecimal value;
	private String comment;
	
	public ClassDayStudent(){}

	public ClassDayStudent(Long classDayStudentId, Long studentId, Long courseId, Long groupId, Long subjectId, Date date, EventRegistrationType event){
		super(classDayStudentId, date);
		this.studentId = studentId;
		this.courseId = courseId;
		this.groupId = groupId;
		this.subjectId = subjectId;
		this.eventRegistrationType = event;
	}
	
	public ClassDayStudent(Long classDayStudentId, Long studentId, Long courseId, Long groupId, Long subjectId, Date date, EventRegistrationType event, BigDecimal value, String comment){
		super(classDayStudentId, date);
		this.studentId = studentId;
		this.courseId = courseId;
		this.eventRegistrationType = event;
		this.value = value;
		this.comment = comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClassDayStudent))
			return false;
		ClassDayStudent that = (ClassDayStudent) other;
		return this.getCourseId() == that.getCourseId() && this.getGroupId() == that.getGroupId()
				&& this.getStudentId() == that.getStudentId() && this.getDate().getDate() == that.getDate().getDate();
	}

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Enum<EventRegistrationType> getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(Enum<EventRegistrationType> eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

}
