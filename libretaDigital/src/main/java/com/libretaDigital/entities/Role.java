package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = -1998976552257333683L;

	private Long oid;

	private String name;
	
	private List<Privilege> privileges;
	
	public Role() {
		privileges = new ArrayList<Privilege>();
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
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
}