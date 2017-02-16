package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;
import com.libretaDigital.utils.Gender;
import com.libretaDigital.utils.Grade;

public class ProfessorDAO extends GenericDAO<Professor> implements IProfessorDAO{

	public List<Professor> getAllProfessors() {
		return getHibernateTemplate().execute(new HibernateCallback<List<Professor>>() {
			
			List<Professor> result = new ArrayList<Professor>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Professor> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select p.oid, p.name, p.last_name, p.birth_date, p.gender, p.email, p.grade, p.employee_since, p.phoneNumber, p.subjectName " 
						+ "from professor p ";

				SQLQuery query = session.createSQLQuery(oQuery);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getActiveProfessorsFromPartialResult(partialResult);

				return result;
			}
		});
	}

	public List<Professor> getInactiveProfessors(){
		
		List<Professor> allTheProfessors = getAllProfessors();
		List<Professor> activeProfessors = getActiveProfessors();

		for(Professor p: activeProfessors)
			allTheProfessors.remove(p);
		
		return allTheProfessors;
		
	}
	
	public List<Professor> getActiveProfessors(){
	
			return getHibernateTemplate().execute(new HibernateCallback<List<Professor>>() {
	
				List<Professor> result = new ArrayList<Professor>();
	
				@SuppressWarnings("unchecked")
				@Override
				public List<Professor> doInHibernate(Session session) throws HibernateException {
					String oQuery = "select p.oid, p.name, p.last_name, p.birth_date, p.gender, p.email, p.grade, p.employee_since, p.phoneNumber,  p.subjectName " 
							+ "from professor p, group_ g "
							+ "where p.oid = g.professor_id ";
	
					SQLQuery query = session.createSQLQuery(oQuery);
	
					List<Object[]> partialResult = query.list();
	
					if (partialResult != null && !partialResult.isEmpty())
						result = getActiveProfessorsFromPartialResult(partialResult);
	
					return result;
				}
			});
		}
	
	
	private List<Professor> getActiveProfessorsFromPartialResult(List<Object[]> partialResult) {
        
		List<Professor> result = new ArrayList<Professor>();
        
        for (Object[] oPartialResult : partialResult) {
            Professor professor = new Professor();
            
            BigInteger bid = (BigInteger) oPartialResult[0];
            professor.setOid(bid.longValue());           
            professor.setName((String)oPartialResult[1]);         
            professor.setLastName((String)oPartialResult[2]);
            
            if(oPartialResult[3] != null && !oPartialResult[3].equals("")){
                Date birthDate = (Date)oPartialResult[3];
                professor.setBirthDate(birthDate);
            }
            
            String gender = (String)oPartialResult[4];
            professor.setGender(Gender.valueOf(gender));          
            professor.setEmail((String)oPartialResult[5]);
            
            if(oPartialResult[6] != null && !oPartialResult[6].equals("")){
                professor.setGrade(Grade.valueOf(oPartialResult[6].toString()));
            }
            
            if(oPartialResult[7] != null && !oPartialResult[7].equals("")){
                Date employeeSince = (Date)oPartialResult[7];
                professor.setEmployeeSince(employeeSince);
            }
            
            professor.setPhoneNumber((String)oPartialResult[8]);
            professor.setSubjectName((String)oPartialResult[9]);
            
            result.add(professor);
        }
        return result;
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
