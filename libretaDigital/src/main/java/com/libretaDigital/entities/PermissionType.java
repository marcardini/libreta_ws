package com.libretaDigital.entities;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public enum PermissionType {

	READ,
	WRITE
}
