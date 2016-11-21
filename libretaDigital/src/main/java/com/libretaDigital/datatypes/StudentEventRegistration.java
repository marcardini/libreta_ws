package com.libretaDigital.datatypes;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentEventRegistration {
	
	private long studentid;
	private EventRegistrationType eventRegistrationType;
	
	public StudentEventRegistration(){}
	
	public StudentEventRegistration(long studentId, EventRegistrationType ert){
		this.studentid = studentId;
		this.eventRegistrationType = ert;
	}
	
	public long getStudentid() {
		return studentid;
	}
	public void setStudentid(long bigInteger) {
		this.studentid = bigInteger;
	}
	public EventRegistrationType getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(EventRegistrationType eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}

}
