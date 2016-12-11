package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;
import com.libretaDigital.utils.CourseType;

public class NotebookDAO extends GenericDAO<Notebook> implements INotebookDAO {
	
	private SubjectDAO subjectDAO;

	public List<Notebook> getNotebooksListFromSubjectIdAndProfessorId(Long subjectId, Long professorId){
		
		return getHibernateTemplate().execute(new HibernateCallback<List<Notebook>>() {

			List<Notebook> result = new ArrayList<Notebook>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Notebook> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select n.oid, n.current_year, n.course_type, n.reformulation_plan, n.group_id from notebook n where subject_oid = :subjectId and professor_id = :professorId";
				
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
				String courseType = (String) oPartialResult[2];
				notebook.setCourseType(CourseType.valueOf(courseType));
			}
			
			if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
				String reformulationPlan = (String) oPartialResult[3];
				notebook.setReformulationPlan(reformulationPlan);
			}
			
			if (oPartialResult[4] != null && !oPartialResult[4].equals("")) {
				BigInteger groupId = (BigInteger) oPartialResult[4];
				notebook.setGroupId(groupId.longValue());
			}
			
			notebook.setSubject(subjectDAO.getById(subjectId));
			
			notebook.setProfessorOid(professorId);
			
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