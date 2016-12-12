package com.libretaDigital.datatypes;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentEventRegistration {
	
	private Long classDayStudentId;
	private Long studentId;
	private Long courseId;
	private Long groupId;
	private Long subjectId;
	private EventRegistrationType eventRegistrationType;
	private Long value;
	private String comment;
	
	public StudentEventRegistration(){}
	
	public StudentEventRegistration(Long classDayStudentId, Long studentId, Long courseId, Long groupId, Long subjectId, EventRegistrationType ert, Long value, String comment){
		this.classDayStudentId = classDayStudentId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.groupId = groupId;
		this.subjectId = subjectId;
		this.eventRegistrationType = ert;
		this.value = value;
		this.comment = comment;
	}
	
	public StudentEventRegistration(Long classDayStudentId, Long studentId, Long courseId, Long groupId, Long subjectId, EventRegistrationType ert){
		this.classDayStudentId = classDayStudentId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.groupId = groupId;
		this.subjectId = subjectId;
		this.eventRegistrationType = ert;		
	}
	
	public EventRegistrationType getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(EventRegistrationType eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public Long getClassDayStudentId() {
		return classDayStudentId;
	}

	public void setClassDayStudentId(Long classDayStudentId) {
		this.classDayStudentId = classDayStudentId;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}