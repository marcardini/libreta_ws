package com.libretaDigital.interfaces;

import java.sql.Timestamp;

public interface IBulletinService {

	void generateBulletin(Long student_id, Timestamp start_date, Timestamp end_date, Long subject_id, int grade, 
			String comment, Boolean final_bulletin, double inassistances);
	
}
