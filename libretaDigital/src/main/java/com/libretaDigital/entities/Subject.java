package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class Subject implements Serializable{
	
	private static final long serialVersionUID = -4934585354884721276L;
	
	private Long oid;
	private String name;
	
	//private List<Period> periods;
	
	public Subject(){}
	
	public Subject(String name){
		
		this.name = name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Subject))
			return false;
		Subject that = (Subject) other;
		return this.getName().equalsIgnoreCase(that.getName());
	}
	
	@Override
	public String toString() {
		return this.getName().toUpperCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
		

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
