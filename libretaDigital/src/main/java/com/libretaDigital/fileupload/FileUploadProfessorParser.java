package com.libretaDigital.fileupload;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.interfaces.IFileParser;
import com.libretaDigital.utils.Gender;
import com.libretaDigital.utils.Grade;

public class FileUploadProfessorParser implements IFileParser {

	protected String splitChar;
	protected String subClassSplitChar;
	protected String fieldsInvalidMsg;
	private HashMap<String, String> errorMsgs;

	protected int gradeFieldLenght;

	public FileUploadProfessorParser() {
	}

	public void beforeParseFile() {
	}

	@Override
	public FileLine parseLine(String line, FileUploadBlockManager manager) throws BuildLineException {

		FileUploadLine fileLine = null;
		fileLine = buildProfessorLine(StringUtils.splitPreserveAllTokens(line, splitChar));
		fileLine.setUpoloadProcessorId(UploadProcessorId.PROFESSOR);

		return fileLine;
	}

	protected FileUploadLine buildProfessorLine(String[] fields) throws BuildLineException {

		FileUploadLine professorUploadLine = new FileUploadLine();

		int i = 0;
		professorUploadLine.setName(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		professorUploadLine.setLastName(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		professorUploadLine.setBirthDate(validateAndGetTimestampField(fields[i], true, 8, i));
		i++;
		professorUploadLine.setEmail(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		professorUploadLine.setGender(validateAndGetGenderField(fields[i], true, i));
		i++;
		professorUploadLine.setEmployeeSince(validateAndGetTimestampField(fields[i], true, 8, i));
		i++;
		professorUploadLine.setGrade(validateAndGetGradeField(fields[i], true, i));
		i++;
		professorUploadLine.setPhoneNumber(validateAndGetStringField(fields[i], true, 20, i));
		i++;
		professorUploadLine.setSubjectName(validateAndGetStringField(fields[i], true, 255, i));

		professorUploadLine.setUpoloadProcessorId(UploadProcessorId.PROFESSOR);

		return professorUploadLine;
	}

	private String validateAndGetStringField(String field, boolean checkIsEmpty, int checkLength, int index)
			throws BuildLineException {

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

	private Timestamp validateAndGetTimestampField(String field, boolean checkIsEmpty, int checkLength, int index)
			throws BuildLineException {

		Timestamp createdDate = null;

		if (field != null) {

			field = field.trim();

			// Chequea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("emptyField") + index);

			// Chequea largo
			if (checkLength > 0 && field.length() > checkLength)
				throw new BuildLineException(errorMsgs.get("invalidLengthField") + index);

		} else
			throw new BuildLineException(errorMsgs.get("nullField") + index);

		try {
			if (!field.equals("")) {

				int day = Integer.parseInt(field.substring(0, 2));
				int month = Integer.parseInt(field.substring(2, 4));
				int year = Integer.parseInt(field.substring(4, 8));
				/*
				 * int hour = Integer.parseInt(field.substring(8, 10)); /int
				 * minute = Integer.parseInt(field.substring(10, 12)); /int
				 * seconds = Integer.parseInt(field.substring(12, 14));
				 */

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, month - 1);
				calendar.set(Calendar.DAY_OF_MONTH, day);

				calendar.set(Calendar.HOUR_OF_DAY, 00);
				calendar.set(Calendar.MINUTE, 00);
				calendar.set(Calendar.SECOND, 00);

				createdDate = new Timestamp(calendar.getTimeInMillis());

			}
		} catch (Exception ex) {
			throw new BuildLineException(errorMsgs.get("dateInvalidFormat") + index);
		}

		return createdDate;
	}

	private Gender validateAndGetGenderField(String field, boolean checkIsEmpty, int index) throws BuildLineException {

		Gender gender = Gender.PENDING;
		if (field != null) {

			field = field.trim();

			// Chequea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("emptyField") + index);

			if (field.equals("M"))
				gender = Gender.MALE;

			if (field.equals("F"))
				gender = Gender.FEMALE;
		}
		return gender;
	}

	private Grade validateAndGetGradeField(String field, boolean checkIsEmpty, int index) throws BuildLineException {

		Grade grade = Grade.UNKNOWN;
		if (field != null) {

			field = field.trim();

			// Chequea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("emptyField") + index);

			if (field.equals("GRADE_1") || field.equals("GRADO_1"))
				grade = Grade.GRADE_1;

			if (field.equals("GRADE_2") || field.equals("GRADO_2"))
				grade = Grade.GRADE_2;
			
			if (field.equals("GRADE_3") || field.equals("GRADO_3"))
				grade = Grade.GRADE_3;
			
			if (field.equals("GRADE_4") || field.equals("GRADO_4"))
				grade = Grade.GRADE_4;
			
			if (field.equals("GRADE_5") || field.equals("GRADO_5"))
				grade = Grade.GRADE_5;
			
			if (field.equals("GRADE_6") || field.equals("GRADO_6"))
				grade = Grade.GRADE_6;
			
			if (field.equals("GRADE_7") || field.equals("GRADO_7"))
				grade = Grade.GRADE_7;
			
		}
		return grade;
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

	public int getGradeFieldLenght() {
		return gradeFieldLenght;
	}

	public void setGradeFieldLenght(int gradeFieldLenght) {
		this.gradeFieldLenght = gradeFieldLenght;
	}

}