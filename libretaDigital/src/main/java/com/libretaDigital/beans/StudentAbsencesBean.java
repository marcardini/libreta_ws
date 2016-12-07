package com.libretaDigital.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.entities.Student;

@SuppressWarnings("serial")
@JsonAutoDetect
public class StudentAbsencesBean extends Student {
	
	private long absences;
	private long half;
	private long justified;
	
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

	public long getJustified() {
		return justified;
	}

	public void setJustified(long justified) {
		this.justified = justified;
	}
}
