package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;
import com.libretaDigital.exceptions.*;

public interface IProfessorService {

	List<Professor> getAllProfessors();

	void addProfessor(Professor dtProfessor) throws ProfessorAlreadyExists, InvalidProfessorInformation;

	void deleteProfessors(List<Long> items);
	
}
