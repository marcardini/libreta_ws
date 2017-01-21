package com.libretaDigital.utils;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public enum Gender {
	
	/*We base ourselves in the Humans rights Commission of Nueva York to develop the list of genders*/
	
	MALE,
	FEMALE,
	BI_GENDERED,
	CROSS_DRESSER,
	DRAG_KING,
	DRAG_QUEEN,
	ANDROGYNOUS,
	FEMELE_TO_MALE,
	GENDER_BENDER,
	GENDER_QUEER,
	MALE_TO_FEMALE,
	NO_OP,
	HIJRA,
	PANGENDER,
	TRANSEXUAL,
	TRANSPERSON,
	BUCH,
	TWO_SPIRIT,
	TRANS,
	AGENDER,
	THIRD_SEX,
	FLUID_SEX,
	
	PENDING;
	
	private Gender() {
		// TODO Auto-generated constructor stub
	}
	
	
}