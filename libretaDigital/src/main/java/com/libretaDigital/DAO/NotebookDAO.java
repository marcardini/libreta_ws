package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;


public class NotebookDAO extends GenericDAO<Notebook> implements INotebookDAO {
	
	private SubjectDAO subjectDAO;
	
	private static Logger log = Logger.getLogger(StudentDAO.class);
	
	@SuppressWarnings("unchecked")
	public Notebook getNotebookById(final long id) {
		Notebook notebook = null;
		List<Notebook> colResult = (List<Notebook>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {

					public List<Notebook> doInHibernate(Session oSession) throws HibernateException {
						Criteria oCriteria = oSession.createCriteria(Notebook.class);
						oCriteria.add(Restrictions.eq("oid", id));
						return oCriteria.list();
					}
				});
		if (colResult.size() > 0)
			notebook = colResult.get(0);
		
		return notebook;
	}

	public List<Notebook> getNotebooksListFromSubjectIdAndProfessorId(Long subjectId, Long professorId){		
		return getHibernateTemplate().execute(new HibernateCallback<List<Notebook>>() {
			List<Notebook> result = new ArrayList<Notebook>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Notebook> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select oid, current_year, group_id, pautaSalaDocente, "
						+ "propuestaDiagnostica, descripcionYAnalisis, programaYPautaExamen "
						+ " from notebook n where subject_oid = :subjectId and professor_id = :professorId";
				
				SQLQuery query = session.createSQLQuery(oQuery);

				query.setLong("subjectId", subjectId);
				query.setLong("professorId", professorId);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getNotebookDataFromPartialResult(partialResult, subjectId, professorId);

				return result;
			}
		});
	}
	
	private List<ClassDayProfessor> getDesarrolloDelCursoByNotebookId(Long notebookOid){
		
		log.debug(String.format("Getting desarrollo del curso. Parameters: Notebook Id " + notebookOid));

		return getHibernateTemplate().execute(new HibernateCallback<List<ClassDayProfessor>>() {

			List<ClassDayProfessor> result = new ArrayList<ClassDayProfessor>();

			@SuppressWarnings("unchecked")
			@Override
			public List<ClassDayProfessor> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select * "
						+ "from class_day_professor day " 
						+ "where day.notebook_id = :notebookOid ";
				
				SQLQuery query = session.createSQLQuery(oQuery);

				query.setLong("notebookOid", notebookOid);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getProfessorDayByPartialResult(partialResult);

				return result;
			}
		});
	}
	
	protected List<ClassDayProfessor> getProfessorDayByPartialResult(List<Object[]> partialResult) {
		List<ClassDayProfessor> desarrollo = new ArrayList<ClassDayProfessor>();
		for (Object[] oPartialResult : partialResult) {
			ClassDayProfessor day = new ClassDayProfessor();
			
			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				day.setOid(id.longValue());
			}
			
			if(oPartialResult[1] != null && !oPartialResult[1].equals("")){
				Date date = (Date)oPartialResult[1];
				day.setDate(date);
			}	
			
			if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
				BigInteger notebookId = (BigInteger) oPartialResult[2];
				day.setNotebookId(notebookId.longValue());
			}
			
			if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
				String comment = (String) oPartialResult[3];
				day.setComment(comment);
			}
			desarrollo.add(day);
		}		
		
		return desarrollo;
	}

	private List<Notebook> getNotebookDataFromPartialResult(List<Object[]> partialResult, Long subjectId, Long professorId){		
		List<Notebook> notebooks = new ArrayList<Notebook>();		
		for (Object[] oPartialResult : partialResult) {
			Notebook notebook = new Notebook();
			
			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				notebook.setOid(id.longValue());
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				int year = (int) oPartialResult[1];
				notebook.setCurrentYear(year);
			}		
			
			if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
				BigInteger groupId = (BigInteger) oPartialResult[2];
				notebook.setGroupId(groupId.longValue());
			}
			
			notebook.setSubject(subjectDAO.getById(subjectId));
			notebook.setProfessorOid(professorId);
			
			if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
				String pauta = (String) oPartialResult[3];
				notebook.setPautaSalaDocente(pauta);
			}
			
			if (oPartialResult[4] != null && !oPartialResult[4].equals("")) {
				String prop = (String) oPartialResult[4];
				notebook.setPropuestaDiagnostica(prop);
			}
			
			if (oPartialResult[5] != null && !oPartialResult[5].equals("")) {
				String descr = (String) oPartialResult[5];
				notebook.setDescripcionYAnalisis(descr);
			}
			
			if (oPartialResult[6] != null && !oPartialResult[6].equals("")) {
				String progr = (String) oPartialResult[6];
				notebook.setProgramaYPautaExamen(progr);
			}		
			
			notebook.setDesarrolloDelCurso(getDesarrolloDelCursoByNotebookId(notebook.getOid()));
			
			notebooks.add(notebook);
		}
		return notebooks;
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

}