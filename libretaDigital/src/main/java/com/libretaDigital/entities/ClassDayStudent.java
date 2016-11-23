package com.libretaDigital.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.libretaDigital.utils.EventRegistrationType;

public class ClassDayStudent extends ClassDay{
	
	private Long studentId;
	private Long courseId;
	private Long groupId;
	private Long subjectId;
	private Enum<EventRegistrationType> eventRegistrationType;
	private BigDecimal value;
	private String comment;
	
	public ClassDayStudent(){}

	public ClassDayStudent(Long studentId, Long courseId, Long groupId, Long subjectId, Date date, EventRegistrationType event){
		super(date);
		this.studentId = studentId;
		this.courseId = courseId;
		this.eventRegistrationType = event;
	}
	
	public ClassDayStudent(Long studentId, Long courseId, Long groupId, Long subjectId, Date date, EventRegistrationType event, BigDecimal value, String comment){
		super(date);
		this.studentId = studentId;
		this.courseId = courseId;
		this.eventRegistrationType = event;
		this.value = value;
		this.comment = comment;
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
