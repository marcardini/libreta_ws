package com.libretaDigital.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.libretaDigital.entities.Student;

@JsonAutoDetect
public class StudentAbsencesBean extends Student {
	
	private long absences;
	
	public void setAbsences(long absences) {
		this.absences = absences;
	}
	
	public long getAbsences() {
		return absences;
	}
	
	public StudentAbsencesBean(){
		super();		
	}

}
