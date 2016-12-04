package com.libretaDigital.services;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.interfaces.*;

public class LoginServiceImpl implements ILoginService {

	private UserDAO userDAO;

	@Override
	public Professor validateUser(String mail, String password) {
		return userDAO.validateUser(mail, password);
	}

	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}