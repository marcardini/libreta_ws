package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum EventRegistrationType {

	INASSISTANCE("Falta"),
	HALF_ASSISTANCE("Media falta"),
	JUSTIFIED("Justificada"),
	
	PARCIAL("Parcial"),
	EXAMEN("Ex�men"),
	ORAL("Oral"),
	EXAMEN_ORAL("Ex�men oral"),
	EXAMEN_ESCRITO("Ex�men escrito"),
	PARTICIPACION_CLASE("Participaci�n de clase"),
	OBSERVACION("Observaci�n"),
	SUSPENSI�N("Suspensi�n");
	
	private final String value;

	private EventRegistrationType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}