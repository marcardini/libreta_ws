package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable{

	private static final long serialVersionUID = -4182974498972556485L;
	
	private Long oid;
	private Date dateFrom;
	private Date dateTo;
	
	public Period(){}
	
	public Period(Date from, Date to){
		this.dateFrom = from;
		this.dateTo = to;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Period))
			return false;
		Period that = (Period) other;
		return this.getDateFrom().equals(that.getDateFrom()) && this.getDateTo().equals(that.getDateTo());
	}
	
	@Override
	public String toString() {
		return "Desde " + this.getDateFrom() + ", hasta " + this.getDateTo(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		return result;
	}
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
}
