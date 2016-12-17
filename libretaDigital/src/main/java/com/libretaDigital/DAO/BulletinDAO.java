package com.libretaDigital.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class BulletinDAO extends GenericDAO<Bulletin> implements IBulletinDAO{
	
	private static Logger log = Logger.getLogger(BulletinDAO.class);

	
	
}
