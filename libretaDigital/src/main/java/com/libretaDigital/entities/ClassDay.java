package com.libretaDigital.entities;

import java.util.Date;

public abstract class ClassDay {

	private Long oid;
	private Date date;
	
	public ClassDay(Date date){
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
