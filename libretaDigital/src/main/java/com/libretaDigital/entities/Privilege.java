package com.libretaDigital.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class Privilege implements Serializable{

	private static final long serialVersionUID = 7197375468903305761L;
	
	private Long oid;
	private Permission permissionEntity;
	private Enum<PermissionType> permissionType;
	
	public Privilege(){}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Permission getPermissionEntity() {
		return permissionEntity;
	}

	public void setPermissionEntity(Permission permissionEntity) {
		this.permissionEntity = permissionEntity;
	}

	public Enum<PermissionType> getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Enum<PermissionType> permissionType) {
		this.permissionType = permissionType;
	}

	

}