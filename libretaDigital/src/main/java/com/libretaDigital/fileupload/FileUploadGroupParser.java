package com.libretaDigital.fileupload;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.DAO.CourseDAO;
import com.libretaDigital.entities.Course;
import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.interfaces.IFileParser;

public class FileUploadGroupParser implements IFileParser {

	private String splitChar;
	private String subClassSplitChar;
	private String fieldsInvalidMsg;
	private HashMap<String, String> errorMsgs;
	private CourseDAO courseDAO;


	public FileUploadGroupParser() {}

	public void beforeParseFile() {}

	@Override
	public FileLine parseLine(String line, FileUploadBlockManager manager) throws BuildLineException {

		FileUploadLine fileLine = null;
		fileLine = buildGroupLine(StringUtils.splitPreserveAllTokens(line, splitChar));
		fileLine.setUpoloadProcessorId(UploadProcessorId.GROUP);

		return fileLine;
	}

	protected FileUploadLine buildGroupLine(String[] fields) throws BuildLineException {

		FileUploadLine groupUploadLine = new FileUploadLine();

		int i = 0;
		groupUploadLine.setGroupName(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		groupUploadLine.setYear(validateAndGetIntField(fields[i], true, i));
		i++;
		Course course = courseDAO.getCourseByName(validateAndGetStringField(fields[i], true, 20, i));
		groupUploadLine.setCourse(course);
		
		groupUploadLine.setInstitutionId(1L);

		groupUploadLine.setUpoloadProcessorId(UploadProcessorId.GROUP);

		return groupUploadLine;
	}

	private String validateAndGetStringField(String field, boolean checkIsEmpty, int checkLength, int index) throws BuildLineException {

		if (field != null) {

			field = field.trim();

			// Chequea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("emptyField") + index);

			// Chequea largo
			if (checkLength > 0 && field.length() > checkLength)
				throw new BuildLineException(errorMsgs.get("invalidLengthField") + index);

		} else {
			throw new BuildLineException(errorMsgs.get("nullField") + index);
		}

		return field;
	}
	
	private int validateAndGetIntField(String field, boolean checkIsEmpty, int index) throws BuildLineException {

		int result = 0;

		if (field != null) {

			// Chequea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("emptyField") + index);

			if (field != null && !field.equals(""))
				result = Integer.parseInt(field);
			
		} else
			throw new BuildLineException(errorMsgs.get("nullField") + index);

		return result;
	}

	
	public String getSplitChar() {
		return splitChar;
	}
	@Required
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	public String getFieldsInvalidMsg() {
		return fieldsInvalidMsg;
	}
	@Required
	public void setFieldsInvalidMsg(String fieldsInvalidMsg) {
		this.fieldsInvalidMsg = fieldsInvalidMsg;
	}
	public HashMap<String, String> getErrorMsgs() {
		return errorMsgs;
	}
	public void setErrorMsgs(HashMap<String, String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}
	public String getSubClassSplitChar() {
		return subClassSplitChar;
	}
	public void setSubClassSplitChar(String subClassSplitChar) {
		this.subClassSplitChar = subClassSplitChar;
	}
	@Override
	public String getBlockSizeParameterName() {
		return null;
	}
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
}