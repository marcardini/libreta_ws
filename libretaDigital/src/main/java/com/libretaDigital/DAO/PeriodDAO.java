package com.libretaDigital.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class PeriodDAO extends GenericDAO<Period> implements IPeriodDAO{

	public List<Period> getAllPeriods() {
		@SuppressWarnings({ "unchecked" })
		List<Period> colResult = (List<Period>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Period> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Period.class);
						return oCriteria.list();
					}
				});
		return colResult;
	}

	@Override
	public Period getPeriodByDates(final Date start, final Date end) {
		Period period = null;
		@SuppressWarnings("unchecked")
		List<Period> colResult = (List<Period>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Period> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Period.class);
						oCriteria.add(Restrictions.eq("dateFrom", start));
						oCriteria.add(Restrictions.eq("dateTo", end));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			period = colResult.get(0);
		
		return period;
	}

}
