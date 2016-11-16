package com.libretaDigital.assistControl;

import java.math.BigInteger;
import java.util.List;

import com.libretaDigital.DAO.GroupDAO;
import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Student;
import com.libretaDigital.interfaces.AssistControlFacade;

public class AssistControlFacadeImpl implements AssistControlFacade {

	private StudentDAO studentDAO;
	private GroupDAO groupDAO;
	
	@Override
	public List<Student> getStudentsByCode(String groupCode) {
		return studentDAO.getStudentsByGroupCode(groupCode);
	}
	
	@Override
	public List<Group> getGroupsByProfessorId(BigInteger l){
		return groupDAO.getGroupsByProfessorId(l);
	}

	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	public GroupDAO getGroupDAO() {
		return groupDAO;
	}
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
}