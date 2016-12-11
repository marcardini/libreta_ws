package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;
import com.libretaDigital.utils.Gender;
import com.libretaDigital.utils.Grade;

public class UserDAO extends GenericDAO<Professor> implements IUserDAO{
	
	private RoleDAO roleDAO;
	private GroupDAO groupDAO;
	private SubjectDAO subjectDAO;

	@Override
	public Professor validateUser(String email, String password) {
		
		@SuppressWarnings({ "unchecked" })
		Professor result = (Professor) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					
					Professor result = new Professor();

					public Professor doInHibernate(Session oSession) throws HibernateException {
						
						String oQuery = "select p.oid, p.name, p.last_name, p.birth_date, p.gender, p.grade, p.employee_since, p.roleId "
								+ "from professor p "
								+ "where upper(p.email) = upper(:email) and p.password = :password ";

						SQLQuery query = oSession.createSQLQuery(oQuery);
						
						query.setString("email", email);
						query.setString("password", password);

						List<Object[]> partialResult = query.list();

						if (partialResult != null && !partialResult.isEmpty())
							result = getProfessorFromPartialResult(partialResult, email, password);
						
						return result;
					}
				});
		
		return result;
	}

	private Professor getProfessorFromPartialResult(List<Object[]> partialResult, String email, String password){
		
		Professor professor = new Professor();

		for (Object[] oPartialResult : partialResult) {

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				professor.setOid(id.longValue());
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				professor.setName(name);
			}

			if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
				String lastName = (String) oPartialResult[2];
				professor.setLastName(lastName);
			}
			
			if(oPartialResult[3] != null && !oPartialResult[3].equals("")){
				Date birthDate = (Date)oPartialResult[3];
				professor.setBirthDate(birthDate);
			}
			
			if(oPartialResult[4] != null && !oPartialResult[4].equals("")){
				String gender = (String)oPartialResult[4];
				professor.setGender(Gender.valueOf(gender));
			}
			
			professor.setEmail(email);
			professor.setPassword(password);
			
			if(oPartialResult[5] != null && !oPartialResult[5].equals("")){
				String grade = (String)oPartialResult[5];
				professor.setGrade(Grade.valueOf(grade));
			}
			
			if(oPartialResult[6] != null && !oPartialResult[6].equals("")){
				Date employeeSince = (Date)oPartialResult[6];
				professor.setEmployeeSince(employeeSince);
			}
			
			if (oPartialResult[7] != null && !oPartialResult[7].equals("")) {
				BigInteger roleId = (BigInteger) oPartialResult[7];
				Role role = roleDAO.getRoleByRoleId(roleId.longValue());
				professor.setRole(role);
			}
			
			List<Group> professorGroups = groupDAO.getGroupsByProfessorId(BigInteger.valueOf(professor.getOid())); 
			professor.setGroupsList(professorGroups);
			
			
			for(Group g : professorGroups){
				List<Subject> professorSubjects = subjectDAO.getSubjectsOfProfessorByProfessorId(professor.getOid());
				g.setSubjectsList(professorSubjects);
				
			}
			
		}

		return professor;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
}
