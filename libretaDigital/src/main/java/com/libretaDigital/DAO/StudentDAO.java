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

public class StudentDAO extends GenericDAO<Student> implements IStudentDAO{

	public List<Student> getAllStudents() {
		@SuppressWarnings({ "unchecked" })
		List<Student> colResult = (List<Student>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Student> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Student.class);
						oCriteria.addOrder(Order.desc("email"));
						return oCriteria.list();
					}
				});
		return colResult;
	}

	@SuppressWarnings("unchecked")
	public Student getStudentByMail(final String email) {
		Student student = null;
		List<Student> colResult = (List<Student>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Student> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Student.class);
						oCriteria.add(Restrictions.eq("email", email));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			student = colResult.get(0);
		
		return student;
	}

}
