package com.libretaDigital.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class ProfessorDAO extends GenericDAO<Professor> implements IProfessorDAO{

	public List<Professor> getAllProfessors() {
		@SuppressWarnings({ "unchecked" })
		List<Professor> colResult = (List<Professor>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Professor> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Professor.class);
						oCriteria.addOrder(Order.desc("email"));
						return oCriteria.list();
					}
				});
		return colResult;
	}

	@SuppressWarnings("unchecked")
	public Professor getProfessorByMail(final String email) {
		Professor professor = null;
		List<Professor> colResult = (List<Professor>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Professor> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Professor.class);
						oCriteria.add(Restrictions.eq("email", email));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			professor = colResult.get(0);
		
		return professor;
	}

}
