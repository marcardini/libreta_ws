package com.libretaDigital.beans;

import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class AbsenceBean {

	private BigInteger idStudent;	
	private boolean late;

	public BigInteger getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(BigInteger idStudent) {
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
