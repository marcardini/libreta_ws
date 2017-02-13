package com.libretaDigital.interfaces;


import java.util.List;

import com.libretaDigital.entities.Notebook;

public interface INotebookService {
	
	void saveNotebook(Notebook notebook);
	
	Notebook getNotebookById(long notebookId);

	public List<Notebook> getNotebooksListFromSubjectIdAndProfessorId(Long subjectId, Long professorId);
}
