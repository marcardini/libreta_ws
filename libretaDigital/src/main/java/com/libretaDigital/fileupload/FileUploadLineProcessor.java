package com.libretaDigital.fileupload;

import org.apache.log4j.Logger;

import com.libretaDigital.DAO.ProfessorDAO;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.interfaces.UploadProcessor;

public class FileUploadLineProcessor implements UploadProcessor {

	private static Logger log = Logger.getLogger(FileUploadLineProcessor.class);

	private ProfessorDAO professorDAO;
	
	@Override
	public void invoke(BlockUploadContext context) {

		FileUploadLine line = (FileUploadLine)context.getCurrentLine();
		
		if(line.getUpoloadProcessorId().equals(UploadProcessorId.PROFESSOR)){
			Professor professor = new Professor(line.getName(), line.getLastName(), line.getBirthDate(), line.getGender());
			log.info("about to insert or update Professor in FileUploadLineProcessor");
			professorDAO.saveOrUpdate(professor);
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

}