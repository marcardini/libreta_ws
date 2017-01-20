package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum Grade {
	
	UNKNOWN("UNKNOWN"),
	GRADE_1("GRADE_1"),
	GRADE_2("GRADE_2"),
	GRADE_3("GRADE_3"),
	GRADE_4("GRADE_4"),
	GRADE_5("GRADE_5"),
	GRADE_6("GRADE_6"),
	GRADE_7("GRADE_7");
	
	private final String value;
	
	private Grade(String value) {
		this.value = value;
	}
	
	public String getValue () {
		return value;
	}
	
	
}
