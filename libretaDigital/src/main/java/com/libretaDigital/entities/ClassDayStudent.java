package com.libretaDigital.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.libretaDigital.utils.EventRegistrationType;

public class ClassDayStudent extends ClassDay{
	
	private Long studentId;
	private Date date;
	private Enum<EventRegistrationType> eventRegistrationType;
	private BigDecimal value;
	
	public ClassDayStudent(Long studentId, Date date, EventRegistrationType event, BigDecimal value){
		this.studentId = studentId;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}


}
