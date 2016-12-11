package com.libretaDigital.interfaces;

import java.util.List;

import com.libretaDigital.entities.*;

public interface INotebookDAO extends IGenericDAO<Notebook>{
	
	List<Notebook> getNotebooksListFromSubjectIdAndProfessorId(Long subjectId, Long professorId);
	
}
