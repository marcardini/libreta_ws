package com.libretaDigital.test.assistance;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.libretaDigital.assistControl.AssistControlFacadeImpl;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;

public class AssistControlTest {
	
	@Autowired
	private AssistControlFacadeImpl assistControlFacadeImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Test
	public void getStudentsByGroupCode() {
		
		List<Student> result = assistControlFacadeImpl.getStudentsByCode("1A");
		
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void getGroupsByProfessor() {
		
		List<Group> result = assistControlFacadeImpl.getGroupsByProfessorId(1L);
		
		Assert.assertEquals(1, result.size());
	}
	
	@Test
	public void everybodyCameTest() {
		
	}
	
	@Test
	public void nobodyCameTest() {
		
	}
	
	@Test
	public void averageAssistanceTest() {
		
	}

	
	public AssistControlFacadeImpl getAssistControlFacadeImpl() {
		return assistControlFacadeImpl;
	}
	public void setAssistControlFacadeImpl(AssistControlFacadeImpl assistControlFacadeImpl) {
		this.assistControlFacadeImpl = assistControlFacadeImpl;
	}

}