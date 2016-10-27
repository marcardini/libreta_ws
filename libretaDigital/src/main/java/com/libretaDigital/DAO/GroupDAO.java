package com.libretaDigital.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.IGroupDAO;
import com.libretaDigital.entities.Group;

public class GroupDAO extends GenericDAO<Group> implements IGroupDAO{

	public List<Group> getAllGroups() {
		@SuppressWarnings({ "unchecked" })
		List<Group> colResult = (List<Group>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Group> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Group.class);
						return oCriteria.list();
					}
				});
		return colResult;
	}

	@Override
	public Group getGroupByNameAndYear(final String name, final int year) {
		Group group = null;
		@SuppressWarnings("unchecked")
		List<Group> colResult = (List<Group>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Group> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Group.class);
						oCriteria.add(Restrictions.eq("name", name));
						oCriteria.add(Restrictions.eq("year", year));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			group = colResult.get(0);
		
		return group;
	}

}
