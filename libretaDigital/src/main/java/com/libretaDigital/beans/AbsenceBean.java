package com.libretaDigital.beans;

import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class AbsenceBean {

	private long idStudent;	
	private boolean late;;	

	public long getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}
	public boolean isLate() {
		return late;
	}
	public void setLate(boolean late) {
		this.late = late;
	}
	
	public AbsenceBean(){
		
	}

}
