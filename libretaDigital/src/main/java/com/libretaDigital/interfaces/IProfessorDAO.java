package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;

public interface IProfessorDAO extends IGenericDAO<Professor>{
	
	List<Professor> getAllProfessors();
	
	Professor getProfessorByMail(final String email);
	
}
