package com.libretaDigital.DAO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.libretaDigital.beans.StudentAbsencesBean;
import com.libretaDigital.entities.*;
import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.*;
import com.libretaDigital.utils.EventRegistrationType;
import com.libretaDigital.utils.Gender;

import org.apache.log4j.Logger;

public class StudentDAO extends GenericDAO<Student> implements IStudentDAO {

	private static Logger log = Logger.getLogger(StudentDAO.class);

	public List<Student> getAllStudents() {
		@SuppressWarnings({ "unchecked" })
		List<Student> colResult = (List<Student>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

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
		List<Student> colResult = (List<Student>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

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

	public List<Student> getStudentsByGroupCode(String groupCode) {

		log.debug(String.format("Querying getStudentsByGroupCode. Parameters: " + groupCode));

		return getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			List<Student> result = new ArrayList<Student>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select s.oid, s.name, s.last_name  " + "from student s, group_ g "
						+ "where s.group_id = g.oid ";
				boolean hasParameter = false;
				if (groupCode != null && !groupCode.equals("")) {
					oQuery = oQuery.concat("and upper(g.name) = ? ");
					hasParameter = !hasParameter;
				}

				SQLQuery query = session.createSQLQuery(oQuery);
				if (hasParameter)
					query.setString(0, groupCode);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getStudentsByGroupCodeFromPartialResult(partialResult);

				return result;
			}
		});
	}

	public List<Student> getStudentsFiles(String mail, String courseName, String groupCode, int year, String subjectName) {

		if (mail != null && !mail.equals(""))
			log.debug(String.format("Getting a particular student file: " + mail));
		else
			log.debug(String.format("Getting students files with this parameters: Group " + groupCode + ". Year " + year + ". Subject " + subjectName));

		return getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			List<Student> result = new ArrayList<Student>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select stu.oid, stu.name, stu.last_name, stu.birth_date, stu.gender, stu.email, stu.currentStudent "
						+ "from student stu, group_ g, subject sub, course course, class_day_student day "
						+ "where stu.group_id = g.oid and sub.course_id = course.oid and day.student_id = stu.oid and day.course_id = course.oid "
						+ "and upper(g.name) = upper(:groupCode) and g.year = :year and upper(sub.name) = upper(:subjectName) ";

				if (mail != null && !mail.equals(""))
					oQuery = oQuery.concat("and upper(stu.email) = upper(:mail)");

				SQLQuery query = session.createSQLQuery(oQuery);

				query.setString("groupCode", groupCode);
				query.setString("subjectName", subjectName);
				query.setInteger("year", year);
				
				if (mail != null && !mail.equals(""))
					query.setString("mail", mail);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getStudentsFilesFromPartialResult(partialResult, courseName);

				return result;
			}
		});
	}

	public List<StudentAbsencesBean> getStudentsAbsencesByGroupCodeWith(String groupCode) {

		log.debug(String.format("Querying getStudentsByGroupCode. Parameters: " + groupCode));

		return getHibernateTemplate().execute(new HibernateCallback<List<StudentAbsencesBean>>() {

			List<StudentAbsencesBean> result = new ArrayList<StudentAbsencesBean>();

			@SuppressWarnings("unchecked")
			@Override
			public List<StudentAbsencesBean> doInHibernate(Session session) throws HibernateException {

				String oQuery = "select s.oid, s.name, s.last_name, "
						+ "(select count(*) from class_day_student cds  where cds.student_id = s.oid and cds.event_registration_type = 'INASSISTANCE') as absences, " 
						+ "(select count(*) from class_day_student cds  where cds.student_id = s.oid and cds.event_registration_type = 'HALF_ASSISTANCE') as half "
						+ "from student s, group_ g "
						+ "where s.group_id = g.oid ";

				boolean hasParameter = false;
				if (groupCode != null && !groupCode.equals("")) {
					oQuery = oQuery.concat("and upper(g.name) = ?");
					hasParameter = !hasParameter;
				}
				oQuery = oQuery + " order by absences DESC, half DESC, last_name ASC";
				SQLQuery query = session.createSQLQuery(oQuery);
				if (hasParameter)
					query.setString(0, groupCode);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getStudentsAbsencesByGroupCodeFromPartialResult(partialResult);

				return result;
			}
		});
	}

	private List<Student> getStudentsFilesFromPartialResult(List<Object[]> partialResult, String courseName) {

		List<Student> result = new ArrayList<Student>();

		for (Object[] oPartialResult : partialResult) {

			Student student = new Student();			
			student.setOid(new BigInteger(oPartialResult[0].toString()));			
			student.setName((String)oPartialResult[1]);			
			student.setLastName((String)oPartialResult[2]);
			
			if(oPartialResult[3] != null && !oPartialResult[3].equals("")){
				Date birthDate = (Date)oPartialResult[3];
				student.setBirthDate(birthDate);
			}

			String gender = (String)oPartialResult[4];
			student.setGender(Gender.valueOf(gender));			
			student.setEmail((String)oPartialResult[5]);
			
			if(oPartialResult[6] != null && !oPartialResult[6].equals("")){
				boolean currentStudent;
				if(oPartialResult[6].toString().equals("1") || oPartialResult[6].equals("true"))
					currentStudent = true;
				else
					currentStudent = false;
				student.setCurrentStudent(currentStudent);
			}
			
			student.setCalendar(getStudentCalendarByStudentIdAndCourseId(student.getOid(), courseName));			
			result.add(student);
		}
		return result;
	}
	
	private List<ClassDayStudent> getStudentCalendarByStudentIdAndCourseId(BigInteger studentOid, String courseName){
		
		log.debug(String.format("Getting student calendar. Parameters: Student Id " + studentOid + "Course name " + courseName));

		return getHibernateTemplate().execute(new HibernateCallback<List<ClassDayStudent>>() {

			List<ClassDayStudent> result = new ArrayList<ClassDayStudent>();

			@SuppressWarnings("unchecked")
			@Override
			public List<ClassDayStudent> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select day.class_date, day.event_registration_type, day.value, day.comment "
						+ "from class_day_student day " 
						+ "where day.student_id = :studentOid and day.course_id = (select oid from course where name = :courseName) ";
				
				SQLQuery query = session.createSQLQuery(oQuery);

				query.setBigInteger("studentOid", studentOid);
				query.setString("courseName", courseName);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getDayEventsByPartialResult(partialResult);

				return result;
			}
		});
	}
	
	private List<ClassDayStudent> getDayEventsByPartialResult(List<Object[]> partialResult){
		
		List<ClassDayStudent> result = new ArrayList<ClassDayStudent>();

		for (Object[] oPartialResult : partialResult) {

			ClassDayStudent classDay = new ClassDayStudent();
			
			if(oPartialResult[0] != null && !oPartialResult[0].equals("")){
				Date date = (Date)oPartialResult[0];
				classDay.setDate(date);
			}

			String type = (String)oPartialResult[1];
			classDay.setEventRegistrationType(EventRegistrationType.valueOf(type));
			
			if(oPartialResult[2] != null && !oPartialResult[2].toString().equals(""))
				classDay.setValue(new BigDecimal(oPartialResult[2].toString()));
			
			if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
				String comment = (String) oPartialResult[3];
				classDay.setComment(comment);
			}
			
			if(oPartialResult[4] != null && !oPartialResult[4].toString().equals(""))
				classDay.setOid(new Long(oPartialResult[4].toString()));
			
			if(oPartialResult[5] != null && !oPartialResult[5].toString().equals(""))
				classDay.setStudentId(new Long(oPartialResult[5].toString()));
			
			result.add(classDay);
		}
		return result;
	}

	private List<Student> getStudentsByGroupCodeFromPartialResult(List<Object[]> partialResult) {

		List<Student> result = new ArrayList<Student>();

		for (Object[] oPartialResult : partialResult) {

			Student student = new Student();

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				student.setOid(id);
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				student.setName(name);
			}

			if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
				String lastName = (String) oPartialResult[2];
				student.setLastName(lastName);
			}

			result.add(student);
		}

		return result;
	}

	private List<StudentAbsencesBean> getStudentsAbsencesByGroupCodeFromPartialResult(List<Object[]> partialResult) {

		List<StudentAbsencesBean> result = new ArrayList<StudentAbsencesBean>();

		for (Object[] oPartialResult : partialResult) {

			StudentAbsencesBean student = new StudentAbsencesBean();

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				student.setOid(id);
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				student.setName(name);
			}

			if (oPartialResult[2] != null && !oPartialResult[2].equals("")) {
				String lastName = (String) oPartialResult[2];
				student.setLastName(lastName);
			}

			if (oPartialResult[3] != null && !oPartialResult[3].equals("")) {
				BigInteger absences = (BigInteger) oPartialResult[3];
				student.setAbsences(absences.longValue());
			}
			
			if (oPartialResult[3] != null && !oPartialResult[4].equals("")) {
				BigInteger half = (BigInteger) oPartialResult[4];
				student.setHalf(half.longValue());
			}

			result.add(student);
		}

		return result;
	}
	
	
	public List<Student> getStudentsAndTodaysAssistance(String courseName, String groupCode, String subjectName){
		
		log.debug(String.format("Getting students and today's assistance. Course: " + courseName + ". Group: " + groupCode + ". Subject: " + subjectName));
		
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();
		String dateFrom = dateFormat.format(date).concat(" 00:00:00");
		String dateTo = dateFormat.format(date).concat(" 23:59:59");
		
		return getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			List<Student> result = new ArrayList<Student>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select stu.oid, stu.name, stu.last_name, stu.birth_date, stu.gender, stu.email, stu.currentStudent, day.event_registration_type "
						+ "from student stu, group_ g, subject sub, course course, class_day_student day "
						+ "where stu.group_id = g.oid and sub.course_id = course.oid and day.student_id = stu.oid and day.course_id = course.oid "
						+ "and upper(g.name) = upper(:groupCode) and upper(sub.name) = upper(:subjectName) and course.oid = (select oid from course where name = :courseName) "
						+ "and day.class_date >= :dateFrom and day.class_date <= :dateTo ";

				SQLQuery query = session.createSQLQuery(oQuery);

				query.setString("courseName", courseName);
				query.setString("groupCode", groupCode);
				query.setString("subjectName", subjectName);
				query.setString("dateFrom", dateFrom);
				query.setString("dateTo", dateTo);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getStudentsAndTodaysAssistanceFromPartialResult(partialResult, courseName, groupCode, subjectName);

				return result;
			}
		});
	}
	
	private List<Student> getStudentsAndTodaysAssistanceFromPartialResult(List<Object[]> partialResult, String courseName, String groupCode, String subjectName){
		
		List<Student> result = new ArrayList<Student>();

		for (Object[] oPartialResult : partialResult) {

			Student student = new Student();			
			student.setOid(new BigInteger(oPartialResult[0].toString()));			
			student.setName((String)oPartialResult[1]);			
			student.setLastName((String)oPartialResult[2]);
			
			if(oPartialResult[3] != null && !oPartialResult[3].equals("")){
				Date birthDate = (Date)oPartialResult[3];
				student.setBirthDate(birthDate);
			}

			String gender = (String)oPartialResult[4];
			student.setGender(Gender.valueOf(gender));			
			student.setEmail((String)oPartialResult[5]);
			
			if(oPartialResult[6] != null && !oPartialResult[6].equals("")){
				boolean currentStudent;
				if(oPartialResult[6].toString().equals("1") || oPartialResult[6].equals("true"))
					currentStudent = true;
				else
					currentStudent = false;
				student.setCurrentStudent(currentStudent);
			}
			
			student.setCalendar(getStudentCalendarByStudentId(student.getOid(), courseName, groupCode, subjectName));			
			result.add(student);
		}
		return result;
	}
	
	private List<ClassDayStudent> getStudentCalendarByStudentId(BigInteger studentOid, String courseName, String groupCode, String subjectName){
		
		log.debug(String.format("Getting student calendar. Parameters: Student Id " + studentOid));

		return getHibernateTemplate().execute(new HibernateCallback<List<ClassDayStudent>>() {

			List<ClassDayStudent> result = new ArrayList<ClassDayStudent>();

			@SuppressWarnings("unchecked")
			@Override
			public List<ClassDayStudent> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select day.class_date, day.event_registration_type, day.value, day.comment, day.oid, day.student_id "
						+ "from class_day_student day " 
						+ "where day.student_id = :studentOid "
						+ "and day.course_id = (select oid from course where name = :courseName) "
						+ "and day.group_id = (select oid from group_ where name = :groupCode) "
						+ "and day.subject_id = (select oid from subject where name = :subjectName) "
						+ "and day.event_registration_type in('INASSISTANCE', 'HALF_ASSISTANCE') ";
				
				SQLQuery query = session.createSQLQuery(oQuery);

				query.setBigInteger("studentOid", studentOid);
				query.setString("courseName", courseName);
				query.setString("groupCode", groupCode);
				query.setString("subjectName", subjectName);
				
				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getDayEventsByPartialResult(partialResult);

				return result;
			}
		});
	}

}