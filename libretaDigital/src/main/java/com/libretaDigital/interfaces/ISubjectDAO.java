package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;

public interface ISubjectDAO extends IGenericDAO<Subject>{
	
	List<Subject> getAllSubjects();
	
	Subject getSubjectByName(String name);
	
}
