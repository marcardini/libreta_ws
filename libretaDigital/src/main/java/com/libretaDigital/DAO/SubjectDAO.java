package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO{
	
	private NotebookDAO notebookDAO;

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
	
	@Override
	public List<Subject> getSubjectsOfProfessorByProfessorId(Long professorId){

		return getHibernateTemplate().execute(new HibernateCallback<List<Subject>>() {

			List<Subject> result = new ArrayList<Subject>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Subject> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select s.oid, s.name, s.group_id from subject s, group_ g, professor p where s.group_id = g.oid and p.oid = g.professor_id "
								+ "and g.professor_id = :professorId";

				SQLQuery query = session.createSQLQuery(oQuery);
				
				query.setLong("professorId", professorId);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getSubjectsOfProfessorFromPartialResult(partialResult, professorId);

				return result;
			}
		});
	}
	
	private List<Subject> getSubjectsOfProfessorFromPartialResult(List<Object[]> partialResult, Long professorOid){
		
		List<Subject> result = new ArrayList<Subject>();

		for (Object[] oPartialResult : partialResult) {

			Subject subject = new Subject();

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				subject.setOid(id.longValue());
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				subject.setName(name);
			}
			List<Notebook> notebooks = notebookDAO.getNotebooksListFromSubjectIdAndProfessorId(subject.getOid(), professorOid);
			subject.setNotebooksList(notebooks);

			result.add(subject);
		}

		return result;
		
	}

	public NotebookDAO getNotebookDAO() {
		return notebookDAO;
	}

	public void setNotebookDAO(NotebookDAO notebookDAO) {
		this.notebookDAO = notebookDAO;
	}
	
	
	

}
