package com.libretaDigital.DAO;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;


public class ClassDayStudentDAO extends GenericDAO<ClassDayStudent> implements IClassDayStudentDAO {

	private static Logger log = Logger.getLogger(ClassDayStudentDAO.class);

	
}
