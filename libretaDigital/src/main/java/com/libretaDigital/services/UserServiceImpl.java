package com.libretaDigital.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.libretaDigital.DAO.ProfessorDAO;
import com.libretaDigital.DAO.UserDAO;
import com.libretaDigital.entities.Professor;

@Service("userService")
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private ProfessorDAO professorDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public UserServiceImpl(){}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Professor user = this.validateUser(username, null);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		UserDetails result = convertFromProfessorToUserDetailsEntity(user);
		return result;
	}
	
	private UserDetails convertFromProfessorToUserDetailsEntity(Professor professor){
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 authorities.add(new SimpleGrantedAuthority("ADMIN"));
		 
		 return new org.springframework.security.core.userdetails.User(professor.getEmail(), professor.getPassword(), true,
				true, true, true, authorities);
		
	}
	
	public Professor getUser(String username) {
		return professorDAO.getProfessorByMail(username);
	}

//	@Transactional(readOnly = false)
//	public void saveUser(Professor user) {
//		professorDAO.save(user);
//	}

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}	
	
	public Professor validateUser(String mail, String password) {
		//FIXME
		if(password == null)
			password = "admin";
		return userDAO.validateUser(mail, password);
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
