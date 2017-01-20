package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class ClassDayProfessor extends ClassDay implements Serializable{

	private static final long serialVersionUID = -3163875670448524521L;

	public ClassDayProfessor(Long classDayProfessor, Date date) {
		super(classDayProfessor, date);
	}

}
