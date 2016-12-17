package com.libretaDigital.services;

import java.sql.Timestamp;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.Bulletin;
import com.libretaDigital.interfaces.*;

public class BulletinServiceImpl implements IBulletinService {

	private BulletinDAO bulletinDAO;

	@Override
	public void generateBulletin(Long student_id, Timestamp start_date, Timestamp end_date, Long subject_id,
			int grade, String comment, Boolean final_bulletin, double inassistances) {
		
		Bulletin bulletin = new Bulletin(student_id, start_date, end_date, subject_id, grade, comment, final_bulletin, inassistances);
		
		bulletinDAO.save(bulletin);
		
	}

	public BulletinDAO getBulletinDAO() {
		return bulletinDAO;
	}

	public void setBulletinDAO(BulletinDAO bulletinDAO) {
		this.bulletinDAO = bulletinDAO;
	}

}