package com.libretaDigital.datatypes;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentEventRegistration {
	
	private Long studentid;
	private Long courseId;
	private EventRegistrationType eventRegistrationType;
	
	public StudentEventRegistration(){}
	
	public StudentEventRegistration(Long studentId, Long courseId, EventRegistrationType ert){
		this.studentid = studentId;
		this.courseId = courseId;
		this.eventRegistrationType = ert;
	}
	
	public EventRegistrationType getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(EventRegistrationType eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}

	public Long getStudentid() {
		return studentid;
	}

	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
