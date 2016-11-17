package com.libretaDigital.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.libretaDigital.utils.EventRegistrationType;

public class ClassDayStudent extends ClassDay{
	
	private BigInteger studentId;
	private Enum<EventRegistrationType> eventRegistrationType;
	private BigDecimal value;
	
	public ClassDayStudent(BigInteger studentId, Date date, EventRegistrationType event, BigDecimal value){
		super(date);
		this.studentId = studentId;
		this.eventRegistrationType = event;
		this.value = value;
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

	public BigInteger getStudentId() {
		return studentId;
	}

	public void setStudentId(BigInteger studentId) {
		this.studentId = studentId;
	}


}
