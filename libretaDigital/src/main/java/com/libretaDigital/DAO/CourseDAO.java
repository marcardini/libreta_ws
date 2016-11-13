package com.libretaDigital.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.ICourseDAO;
import com.libretaDigital.entities.Course;

public class CourseDAO extends GenericDAO<Course> implements ICourseDAO{

	public List<Course> getAllCourses() {
		@SuppressWarnings({ "unchecked" })
		List<Course> colResult = (List<Course>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Course> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Course.class);
						return oCriteria.list();
					}
				});
		return colResult;
	}
	
	public Course getCourseByName(String name){
		Course course = null;
		@SuppressWarnings("unchecked")
		List<Course> colResult = (List<Course>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Course> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Course.class);
						oCriteria.add(Restrictions.eq("name", name));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			course = colResult.get(0);
		
		return course;
	}
	
	public Long getCourseIdByName(String name){
		Long id = 0L;
		@SuppressWarnings("unchecked")
		List<Course> colResult = (List<Course>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Course> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Course.class);
						oCriteria.add(Restrictions.eq("name", name));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			id = colResult.get(0).getOid();
		
		return id;
	}

}