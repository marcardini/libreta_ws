package com.libretaDigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.libretaDigital.DAO.SubjectDAO;
import com.libretaDigital.entities.Subject;

public class SubjectServiceImpl {

	@Autowired
	private SubjectDAO subjectDAO;
	
	public List<Subject> getSubjectsByGroupIdAndProfessorId(Long groupId, Long professorId){
		return subjectDAO.getSubjectsByGroupIdAndProfessorId(groupId, professorId);
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
}
