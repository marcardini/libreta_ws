package com.libretaDigital.test.professor;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.libretaDigital.entities.Professor;
import com.libretaDigital.exceptions.InvalidProfessorInformation;
import com.libretaDigital.exceptions.ProfessorAlreadyExists;
import com.libretaDigital.utils.Gender;

public class ProfessorTest extends BasicTestData{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BasicTestData.setUpBeforeClass();
	}
	
	
	@Test
	public void test() throws ProfessorAlreadyExists, InvalidProfessorInformation {
		
		Date birthDate = createDate("17-05-1989 00:00:00");
		Professor professor = new Professor("admin", "mathias", "arcardini", birthDate, Gender.MALE, "arcardinimathias@gmail.com", new Date());
		
		getProfessorService().addProfessor(professor);
		
		List<Professor> professors = getProfessorService().getAllProfessors();
		
		Assert.assertEquals(1,professors.size());
	}

}
