package com.libretaDigital.test.professor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.libretaDigital.exceptions.DateConvertorException;
import com.libretaDigital.services.ProfessorServiceImpl;

public class BasicTestData {

	protected static ApplicationContext fileSystem;
	protected static ProfessorServiceImpl professorService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			
			fileSystem = new ClassPathXmlApplicationContext("classpath:main-config-test.xml");
			
			professorService = (ProfessorServiceImpl) fileSystem.getBean("professorService");
			
		} catch (Exception e){
			e.printStackTrace();
			throw e; 
		}
	}
	
	public ProfessorServiceImpl getProfessorService() {
		return professorService;
	}
	
	
	public Date createDate(String stringDate){
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			return dateFormat.parse(stringDate);
		} catch (ParseException e) {
			throw new DateConvertorException();
		}
	}


}
