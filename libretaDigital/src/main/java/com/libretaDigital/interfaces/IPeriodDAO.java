package com.libretaDigital.interfaces;

import java.util.Date;
import java.util.List;
import com.libretaDigital.entities.*;

public interface IPeriodDAO extends IGenericDAO<Period>{
	
	List<Period> getAllPeriods();
	
	Period getPeriodByDates(Date start, Date end);
	
}
