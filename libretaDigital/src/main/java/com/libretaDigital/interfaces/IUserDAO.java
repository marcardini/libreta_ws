package com.libretaDigital.interfaces;

import com.libretaDigital.entities.*;

public interface IUserDAO extends IGenericDAO<Professor>{
	
	Professor validateUser(String mail, String password);
	
}
