package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;

public class BulletinDAO extends GenericDAO<Bulletin> implements IBulletinDAO{
	
	private static Logger log = Logger.getLogger(BulletinDAO.class);
	
	public List<Bulletin> getBulletinsByStudentIdAndSubjectIdBetweenDates(Long studentId, Long subjectId, Timestamp start, Timestamp end){
		log.debug(String.format("Getting bulletins by: student id - " + studentId + ", subject id - " + subjectId + ", between " + start + " and " + end));

		return getHibernateTemplate().execute(new HibernateCallback<List<Bulletin>>() {

			List<Bulletin> result = new ArrayList<Bulletin>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Bulletin> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select b.oid, b.student_id, b.start_date, b.end_date, b.subject_id, b.grade, b.comment, b.final_bulletin, b.inassistances "
						+ "from bulletin b where subject_id = :subjectId and student_id = :studentId "
						+ "and start_date >= :startDate and end_date <= :endDate";
				
				SQLQuery query = session.createSQLQuery(oQuery);

				query.setLong("subjectId", subjectId);
				query.setLong("studentId", studentId);
				query.setTimestamp("startDate", start);
				query.setTimestamp("endDate", end);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getBulletinsByStudentIdAndSubjectIdBetweenDatesFromPartialResult(partialResult);

				return result;
			}
		});
	}
	
	private List<Bulletin> getBulletinsByStudentIdAndSubjectIdBetweenDatesFromPartialResult(List<Object[]> partialResult) {
        List<Bulletin> result = new ArrayList<Bulletin>();
        
        for (Object[] oPartialResult : partialResult) {
        	
        	Bulletin bulletin = new Bulletin();
            
        	BigInteger bid = (BigInteger) oPartialResult[0];
        	bulletin.setOid(bid.longValue());
        	
        	if(oPartialResult[1] != null && !oPartialResult[1].equals("")){
        		BigInteger studentId = (BigInteger) oPartialResult[1];
        		bulletin.setStudentId(studentId.longValue());
        	}
        	
        	if(oPartialResult[2] != null && !oPartialResult[2].equals("")){
                Timestamp startDate = (Timestamp)oPartialResult[2];
                bulletin.setStartDate(startDate);
            }
        	
        	if(oPartialResult[3] != null && !oPartialResult[3].equals("")){
        		Timestamp endDate = (Timestamp)oPartialResult[3];
                bulletin.setEndDate(endDate);
            }
        	
        	if(oPartialResult[4] != null && !oPartialResult[4].equals("")){
        		BigInteger subjectId = (BigInteger)oPartialResult[4]; 
        		bulletin.setSubjectId(subjectId.longValue());
        	}
            
        	if(oPartialResult[5] != null && !oPartialResult[5].equals("")){
        		bulletin.setGrade((int) oPartialResult[5]);
        	}
        	
        	if(oPartialResult[6] != null && !oPartialResult[6].equals("")){
        		bulletin.setComment((String)oPartialResult[6]);
        	}
        	
        	if(oPartialResult[7] != null && !oPartialResult[7].equals("")){
                boolean finalBulletin;
                if(oPartialResult[7].toString().equals("1") || oPartialResult[7].equals("true"))
                	finalBulletin = true;
                else
                	finalBulletin = false;
                bulletin.setFinalBulletin(finalBulletin);
            }
        	
        	if(oPartialResult[8] != null && !oPartialResult[8].equals("")){
        		double inassistances = (double) oPartialResult[8];
        		bulletin.setInassistances(inassistances);
        	}
        	
            result.add(bulletin);
        }
        return result;
    }

	
	
}
