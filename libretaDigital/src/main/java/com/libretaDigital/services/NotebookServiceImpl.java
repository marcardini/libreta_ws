package com.libretaDigital.services;


import java.util.List;

import com.libretaDigital.DAO.NotebookDAO;
import com.libretaDigital.entities.Notebook;
import com.libretaDigital.interfaces.INotebookService;

public class NotebookServiceImpl implements INotebookService{
	
	
	private NotebookDAO notebookDAO;

	@Override
	public void saveNotebook(Notebook notebook) {
		notebookDAO.saveOrUpdate(notebook);		
	}
		

	@Override
	public Notebook getNotebookById(long notebookId) {		
		return notebookDAO.getNotebookById(notebookId);
	}
	
	@Override
	public List<Notebook> getNotebooksListFromSubjectIdAndProfessorId(Long subjectId, Long professorId) {		
		return notebookDAO.getNotebooksListFromSubjectIdAndProfessorId(subjectId, professorId);
	}

	public NotebookDAO getNotebookDAO() {
		return notebookDAO;
	}

	public void setNotebookDAO(NotebookDAO notebookDAO) {
		this.notebookDAO = notebookDAO;
	}


	

}
