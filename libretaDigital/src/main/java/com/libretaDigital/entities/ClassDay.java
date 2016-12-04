package com.libretaDigital.entities;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public abstract class ClassDay{
	
	private Long oid;
	private Date date;
	
	public ClassDay(){}
	
	public ClassDay(Long classDayStudentOid, Date date){
		this.oid = classDayStudentOid;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}
}
