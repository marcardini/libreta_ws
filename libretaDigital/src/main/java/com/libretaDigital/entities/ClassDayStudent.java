package com.libretaDigital.entities;

import java.math.BigDecimal;

import com.libretaDigital.utils.ClassDayRegistrationType;

public class ClassDayStudent extends ClassDay{
	
	private Enum<ClassDayRegistrationType> classDayRegistrationType;
	private BigDecimal value;
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Enum<ClassDayRegistrationType> getClassDayRegistrationType() {
		return classDayRegistrationType;
	}
	public void setClassDayRegistrationType(Enum<ClassDayRegistrationType> classDayRegistrationType) {
		this.classDayRegistrationType = classDayRegistrationType;
	}

}
