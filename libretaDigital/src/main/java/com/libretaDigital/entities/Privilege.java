package com.libretaDigital.entities;

public class Privilege {

	private Long oid;
	private Permission entity;
	private PermissionType permission;
	
	public Privilege(){}
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public PermissionType getPermission() {
		return permission;
	}
	public void setPermission(PermissionType permission) {
		this.permission = permission;
	}
	public Permission getEntity() {
		return entity;
	}
	public void setEntity(Permission entity) {
		this.entity = entity;
	}
}