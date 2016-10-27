package com.libretaDigital.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TranslateUtils {
	
	public static String translate(String toTranslate, String msgFileName) {
		
		ResourceBundle rb = ResourceBundle.getBundle(msgFileName);
		
		try {
			
			if(toTranslate != null)
				toTranslate = toTranslate.trim();
			
			return rb.getString(toTranslate);
			
		} catch(MissingResourceException mre){
			return toTranslate;
		}
	}

}
