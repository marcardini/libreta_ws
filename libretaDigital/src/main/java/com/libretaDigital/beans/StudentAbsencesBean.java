package com.libretaDigital.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.entities.Student;

@JsonAutoDetect
public class StudentAbsencesBean extends Student {
	
	private long absences;
	private long half;
	
	public void setAbsences(long absences) {
		this.absences = absences;
	}
	
	public long getAbsences() {
		return absences;
	}
	
	public long getHalf() {
		return half;
	}

	public void setHalf(long half) {
		this.half = half;
	}
	
	public StudentAbsencesBean(){
		super();		
	}
}
