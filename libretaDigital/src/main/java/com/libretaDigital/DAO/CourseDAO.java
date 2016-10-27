package com.libretaDigital.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

}