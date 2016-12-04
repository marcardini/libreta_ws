package com.libretaDigital.interfaces;

import com.libretaDigital.entities.Professor;

public interface ILoginService {

	Professor validateUser(String mail, String password);
	
}
