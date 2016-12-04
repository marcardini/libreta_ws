package com.libretaDigital.entities;

import java.io.Serializable;

public class Permission implements Serializable{

	private static final long serialVersionUID = -2305151294570301410L;
	
	private Long oid;
	private String name;

	public Permission(){}
	
	public Permission(Long oid, String name){
		this.oid = oid;
		this.name = name;
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