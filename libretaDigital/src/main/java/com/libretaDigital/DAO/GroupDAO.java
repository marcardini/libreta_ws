package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.IGroupDAO;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;
import com.libretaDigital.entities.Subject;

public class GroupDAO extends GenericDAO<Group> implements IGroupDAO {

	private static Logger log = Logger.getLogger(GroupDAO.class);

	public List<Group> getAllGroups() {
		return getHibernateTemplate().execute(new HibernateCallback<List<Group>>() {

			List<Group> result = new ArrayList<Group>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Group> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select distinct(g.oid), g.name, p.subjectName, g.year " + "from group_ g, professor p "
						+ "where g.professor_id = p.oid ";

				SQLQuery query = session.createSQLQuery(oQuery);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getGroupProfessorPartialResult(partialResult);

				return result;
			}
		});
	}

	private List<Group> getGroupProfessorPartialResult(List<Object[]> partialResult) {

		List<Group> result = new ArrayList<Group>();

		for (Object[] oPartialResult : partialResult) {

			Group group = new Group();
			if (oPartialResult != null) {

				if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
					BigInteger bid = (BigInteger) oPartialResult[0];
					group.setOid(bid.longValue());
				}

				if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
					String name = (String) oPartialResult[1];
					group.setName(name);
				}

				if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
					String subjectName = (String) oPartialResult[2];
					group.getSubjectsList().add(new Subject(subjectName));
				}
				
				if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
					int year = (int) oPartialResult[3];
					group.setYear(year);
				}

				result.add(group);
			}
		}

		return result;
	}

	@Override
	public Group getGroupByNameAndYear(final String name, final int year) {
		Group group = null;
		@SuppressWarnings("unchecked")
		List<Group> colResult = (List<Group>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

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

	public List<Group> getGroupsByProfessorId(BigInteger professorId) {

		log.debug(String.format("Querying getGroupsByProfessorId. Parameters: " + professorId));

		return getHibernateTemplate().execute(new HibernateCallback<List<Group>>() {

			List<Group> result = new ArrayList<Group>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Group> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select g.oid, g.name from group_ g, professor p where g.professor_id = p.oid ";
				boolean hasParameter = false;
				if (professorId != null && professorId.compareTo(BigInteger.ONE) > 0) {
					oQuery = oQuery.concat("and p.oid = ? ");
					hasParameter = !hasParameter;
				}

				SQLQuery query = session.createSQLQuery(oQuery);
				if (hasParameter)
					query.setBigInteger(0, professorId);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getGroupByProfessorIdFromPartialResult(partialResult);

				return result;
			}
		});
	}

	private List<Group> getGroupByProfessorIdFromPartialResult(List<Object[]> partialResult) {

		List<Group> result = new ArrayList<Group>();

		for (Object[] oPartialResult : partialResult) {

			Group group = new Group();

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger bid = (BigInteger) oPartialResult[0];
				group.setOid(bid.longValue());
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				group.setName(name);
			}

			result.add(group);
		}

		return result;
	}
}
