package com.libretaDigital.datatypes;

import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.utils.EventRegistrationType;

@JsonAutoDetect
public class StudentEventRegistration {
	
	private BigInteger studentid;
	private EventRegistrationType eventRegistrationType;
	
	public BigInteger getStudentid() {
		return studentid;
	}
	public void setStudentid(BigInteger bigInteger) {
		this.studentid = bigInteger;
	}
	public EventRegistrationType getEventRegistrationType() {
		return eventRegistrationType;
	}
	public void setEventRegistrationType(EventRegistrationType eventRegistrationType) {
		this.eventRegistrationType = eventRegistrationType;
	}

}
