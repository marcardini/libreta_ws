package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum EventRegistrationType {

	FALTA("Falta"),
	MEDIA_FALTA("Media falta"),
	JUSTIFICADA("Justificada"),
	
	OBSERVACION("Observación"),
	SUSPENSIÓN("Suspensión"),
	
	PARTICIPACION_CLASE("Participación de clase"),	
	ORAL("Oral"),
	ESCRITO("Escrito"),
	PARCIAL("Parcial"),	
	EXAMEN_ORAL("Exámen oral"),
	EXAMEN_ESCRITO("Exámen escrito"),
	EXAMEN("Exámen"),
		
	PRIMER_EVALUACIÓN_ESPECIAL("1era Evaluacion Especial"),	
	PROMEDIO_TEORICO("Promedio Teórico"),
	PROMEDIO_PRACTICO("Promedio Práctico"),
	PRIMER_PROMEDIO("Primer Promedio"),
	SEGUNDO_PROMEDIO("Segundo Promedio"),
	SEGUNDA_EVALUACIÓN_ESPECIAL("2da Evaluacion Especial"),
	PROMEDIO_FINAL_PRACTICO("Promedio Final Práctico"),
	PROMEDIO_FINAL_TEORICO("Promedio Final Teórico"),	
	PROMEDIO_FINAL("Promedio Final"),	
	PRIMER_JUICIO_DOCENTE("Primer Juicio Docente"),
	SEGUNDO_JUICIO_DOCENTE("Segundo Juicio Docente"),
	JUICIO_REUNION("Juicio Reunion"),
	JUICIO_FINAL("Juicio Final");
	
	private final String value;

	private EventRegistrationType (String value) {
		this.value = value;
	}

	public String getValue () {
		return value;
	}
}