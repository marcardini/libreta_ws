package com.libretaDigital.datatypes;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentEventRegistration {
	
	private Long studentid;
	private EventRegistrationType eventRegistrationType;
	
	public Long getStudentid() {
		return studentid;
	}
	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}
	public EventRegistrationType getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(EventRegistrationType eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}

}
