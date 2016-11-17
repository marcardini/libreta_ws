package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum EventRegistrationType {

	ASSISTANCE("Asistencia"),
	INASSISTANCE("Falta"),
	HALF_ASSISTANCE("Media falta");
	
	private final String value;

	private EventRegistrationType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}