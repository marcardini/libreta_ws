package com.libretaDigital.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentDayBean {

	private boolean late;
	private boolean justified;
	private String eventRegistrationType;
	private long courseId;
	private long groupId;
	private long subject_id;
	private long studentId;
	private long oid;
	private long value;
	private String comment;

	public StudentDayBean() {

	}

	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public boolean isLate() {
		return late;
	}
	public void setLate(boolean late) {
		this.late = late;
	}
	public String getEventRegistrationType() {
		return eventRegistrationType;
	}

	public void setEventRegistrationType(String eventRegistrationType) {
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

	public long getOid() {
		return oid;
	}

	public void setOidd(long oid) {
		this.oid = oid;
	}

	public boolean isJustified() {
		return justified;
	}

	public void setJustified(boolean justified) {
		this.justified = justified;
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