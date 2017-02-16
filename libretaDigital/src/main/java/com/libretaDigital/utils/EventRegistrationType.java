package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum EventRegistrationType {

	FALTA("Falta"),
	MEDIA_FALTA("Media falta"),
	JUSTIFICADA("Justificada"),
	
	OBSERVACION("Observaci�n"),
	SUSPENSI�N("Suspensi�n"),
	
	PARTICIPACION_CLASE("Participaci�n de clase"),	
	ORAL("Oral"),
	ESCRITO("Escrito"),
	PARCIAL("Parcial"),	
	EXAMEN_ORAL("Ex�men oral"),
	EXAMEN_ESCRITO("Ex�men escrito"),
	EXAMEN("Ex�men"),
		
	PRIMER_EVALUACI�N_ESPECIAL("1era Evaluacion Especial"),	
	PROMEDIO_TEORICO("Promedio Te�rico"),
	PROMEDIO_PRACTICO("Promedio Pr�ctico"),
	PRIMER_PROMEDIO("Primer Promedio"),
	SEGUNDO_PROMEDIO("Segundo Promedio"),
	SEGUNDA_EVALUACI�N_ESPECIAL("2da Evaluacion Especial"),
	PROMEDIO_FINAL_PRACTICO("Promedio Final Pr�ctico"),
	PROMEDIO_FINAL_TEORICO("Promedio Final Te�rico"),	
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