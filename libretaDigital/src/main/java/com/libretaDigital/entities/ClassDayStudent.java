package com.libretaDigital.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.libretaDigital.utils.EventRegistrationType;

public class ClassDayStudent extends ClassDay{
	
	private Long studentId;
	private Long courseId;
	private Enum<EventRegistrationType> eventRegistrationType;
	private BigDecimal value;
	
	public ClassDayStudent(){}
	
	public ClassDayStudent(Long studentId, Long courseId, Date date, EventRegistrationType event, BigDecimal value){
		super(date);
		this.studentId = studentId;
		this.courseId = courseId;
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


}
