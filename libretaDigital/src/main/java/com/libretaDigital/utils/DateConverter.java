package com.libretaDigital.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.libretaDigital.exceptions.DateConvertorException;

public class DateConverter {

	public Date createDate(String stringDate) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ii:ss");

		try {
			return format.parse(stringDate);
		} catch (ParseException e) {
			throw new DateConvertorException();
		}
	}

	public Timestamp createTimestamp(String stringDate) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse(stringDate);
		long time = date.getTime();
		return new Timestamp(time);
	}

}
