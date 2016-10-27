package com.libretaDigital.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO{

	public List<Subject> getAllSubjects() {
		@SuppressWarnings({ "unchecked" })
		List<Subject> colResult = (List<Subject>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Subject> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Subject.class);
						return oCriteria.list();
					}
				});
		return colResult;
	}

	@Override
	public Subject getSubjectByName(final String name) {
		Subject subject = null;
		@SuppressWarnings("unchecked")
		List<Subject> colResult = (List<Subject>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Subject> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Subject.class);
						oCriteria.add(Restrictions.eq("name", name));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			subject = colResult.get(0);
		
		return subject;
	}

}
