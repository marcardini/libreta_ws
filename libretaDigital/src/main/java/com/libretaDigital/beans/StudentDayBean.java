package com.libretaDigital.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentDayBean {

	private boolean late;
	private boolean justified;
	private Enum<EventRegistrationType> eventRegistrationType;
	private long courseId;
	private long groupId;
	private long subject_id;
	private long idStudent;
	private long classDayStudentId;

	public StudentDayBean() {

	}

	public long getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}
	public boolean isLate() {
		return late;
	}
	public void setLate(boolean late) {
		this.late = late;
	}
	public Enum<EventRegistrationType> getEventRegistrationType() {
		return eventRegistrationType;
	}

	public void setEventRegistrationType(Enum<EventRegistrationType> eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupid(long groupId) {
		this.groupId = groupId;
	}
	public long getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public long getClassDayStudentId() {
		return classDayStudentId;
	}

	public void setClassDayStudentId(long classDayStudentId) {
		this.classDayStudentId = classDayStudentId;
	}

	public boolean isJustified() {
		return justified;
	}

	public void setJustified(boolean justified) {
		this.justified = justified;
	}
}