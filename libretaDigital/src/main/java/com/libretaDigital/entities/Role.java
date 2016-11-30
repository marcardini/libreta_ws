package com.libretaDigital.entities;

import java.util.ArrayList;
import java.util.List;

public class Role {

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