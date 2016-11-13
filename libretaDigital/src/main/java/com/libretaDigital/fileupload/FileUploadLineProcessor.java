package com.libretaDigital.fileupload;

import org.apache.log4j.Logger;

import com.libretaDigital.DAO.GroupDAO;
import com.libretaDigital.DAO.ProfessorDAO;
import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.entities.Group;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.entities.Student;
import com.libretaDigital.interfaces.UploadProcessor;

public class FileUploadLineProcessor implements UploadProcessor {

	private static Logger log = Logger.getLogger(FileUploadLineProcessor.class);

	private ProfessorDAO professorDAO;
	private StudentDAO studentDAO;
	private GroupDAO groupDAO;
	
	@Override
	public void invoke(BlockUploadContext context) {

		FileUploadLine line = (FileUploadLine)context.getCurrentLine();
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.PROFESSOR)){
			log.info("about to insert or update Professor in FileUploadLineProcessor");
			Professor professor = new Professor(line.getName(), line.getLastName(), line.getPassword(), line.getBirthDate(), line.getEmail(), line.getGender(), line.getEmployeeSince());	
			professorDAO.save(professor);
			return;
		}
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.STUDENT)){
			log.info("about to insert or update Student in FileUploadLineProcessor");
			Student student = new Student(line.getName(), line.getLastName(), line.getBirthDate(), line.getGender(), line.getEmail());
			studentDAO.save(student);
			return;
		}
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.GROUP)){
			log.info("about to insert or update Group in FileUploadLineProcessor");
			Group group = new Group(line.getCourse(), line.getGroupName(), line.getYear());
			groupDAO.save(group);
		}
	}
		
	@Override
	public void transactionFailed(BlockUploadContext context) {}

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}
	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
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