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
			//Professor professor = new Professor(line.getName(), line.getLastName(), line.getBirthDate(), line.getGender());
			Professor professor = new Professor(line.getName(), line.getLastName());
			
			log.info("about to insert or update Professor in FileUploadLineProcessor");
			professorDAO.save(professor);
		}
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.STUDENT)){
			Student student = new Student(line.getName(), line.getLastName());
			
			log.info("about to insert or update Student in FileUploadLineProcessor");
			studentDAO.save(student);
		}
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.GROUP)){
			Group group = new Group(line.getGroupName(), line.getYear());
			
			log.info("about to insert or update Group in FileUploadLineProcessor");
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