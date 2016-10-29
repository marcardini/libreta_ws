package com.libretaDigital.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.libretaDigital.exceptions.DateConvertorException;

public class DateConverter {
	
	public Date createDate(String stringDate){
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ii:ss");
		
		try {
			return format.parse(stringDate);
		} catch (ParseException e) {
			throw new DateConvertorException();
		}
	}

}
