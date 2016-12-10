package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum EventRegistrationType {

	FALTA("Falta"),
	MEDIA_FALTA("Media falta"),
	JUSTIFICADA("Justificada"),
	
	PARCIAL("Parcial"),
	EXAMEN("Exámen"),
	ORAL("Oral"),
	EXAMEN_ORAL("Exámen oral"),
	EXAMEN_ESCRITO("Exámen escrito"),
	PARTICIPACION_CLASE("Participación de clase"),
	OBSERVACION("Observación"),
	SUSPENSIÓN("Suspensión");
	
	private final String value;

	private EventRegistrationType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}