package com.libretaDigital.services;

import java.util.List;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.exceptions.*;
import com.libretaDigital.interfaces.*;

public class ProfessorServiceImpl implements IProfessorService{

	private ProfessorDAO professorDAO;
	private MessageDigestPasswordEncoder encoder;
	
	public List<Professor> getAllProfessors() {
		return professorDAO.getAllProfessors();
	}
	
	public void addProfessor(Professor dtProfessor) throws ProfessorAlreadyExists, InvalidProfessorInformation {

		if (dtProfessor.getPassword() == null || "".equals(dtProfessor.getPassword().trim()))
			throw new InvalidProfessorInformation(InvalidProfessorInformation.ErrorType.EMPTY_PASSWORD);
		
		
				
		String password = dtProfessor.getPassword();
		String encriptedPassword = null;
		boolean passwordValidated = dtProfessor.validatePassword(password);

		if(passwordValidated){
			//if the validation passes, the password gets encripted
			encriptedPassword = encoder.encodePassword(password, null);
			dtProfessor.setPassword(encriptedPassword);
	
			professorDAO.saveOrUpdate(dtProfessor);
		}
	}
	
	@Override
	public void deleteProfessors(List<Long> items) {
		
		for (Long oid : items) {
			Professor prof = professorDAO.getById(oid);
			if(prof != null){
				professorDAO.delete(prof);
			}
		}
	}
	
	@Override
	public Professor getByEmail(String email) {
		return professorDAO.getProfessorByMail(email);
	}

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}
	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}
	public MessageDigestPasswordEncoder getEncoder() {
		return encoder;
	}
	public void setEncoder(MessageDigestPasswordEncoder encoder) {
		this.encoder = encoder;
	}
}
