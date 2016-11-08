package com.libretaDigital.fileupload;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.interfaces.IFileParser;
import com.libretaDigital.utils.Gender;

public class FileUploadStudentParser implements IFileParser {

	protected String splitChar;
	protected String subClassSplitChar;
	protected String fieldsInvalidMsg;
	private HashMap<String, String> errorMsgs;


	public FileUploadStudentParser() {}

	public void beforeParseFile() {}

	@Override
	public FileLine parseLine(String line, FileUploadBlockManager manager) throws BuildLineException {

		FileUploadLine fileLine = null;
		fileLine = buildStudentLine(StringUtils.splitPreserveAllTokens(line, splitChar));
		fileLine.setUpoloadProcessorId(UploadProcessorId.STUDENT);

		return fileLine;
	}

	protected FileUploadLine buildStudentLine(String[] fields) throws BuildLineException {

		FileUploadLine professorUploadLine = new FileUploadLine();

		int i = 0;
		professorUploadLine.setName(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		professorUploadLine.setLastName(validateAndGetStringField(fields[i], true, 255, i));
		i++;
		professorUploadLine.setBirthDate(validateAndGetTimestampField(fields[i], true, 8, i));
		i++;
		professorUploadLine.setGender(validateAndGetGenderField(fields[i], true, i));

		professorUploadLine.setUpoloadProcessorId(UploadProcessorId.STUDENT);

		return professorUploadLine;
	}

	String validateAndGetStringField(String field, boolean checkIsEmpty, int checkLength, int index)
			throws BuildLineException {

		if (field != null) {

			field = field.trim();

			// Tiene que chequear que no se vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			// Tiene que chequear largo
			if (checkLength > 0 && field.length() > checkLength)
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			// Tiene que chequear que no sea vacio
		} else if (checkIsEmpty) {
			throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
		}

		return field;

	}

	Boolean validateAndGetBooleanField(String field, boolean checkIsEmpty, int checkLength, int index)
			throws BuildLineException {

		Boolean bField = null;

		if (field != null && !field.equals("")) {

			field = field.trim().toUpperCase();

			// Tiene que chequear que no se vacio
			if (checkIsEmpty && field.isEmpty())
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			if (field.equals("S") || field.equals("B"))
				bField = true;
			else if (field.equals("N") || field.equals("A"))
				bField = false;

			// Tiene que chequear que no se vacio
		} else if (checkIsEmpty) {
			throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
		}
		return bField;

	}

	Timestamp validateAndGetTimestampField(String field, boolean checkIsEmpty, int checkLength, int index)
			throws BuildLineException {

		Timestamp createdDate = null;

		if (field != null) {

			field = field.trim();

			// Si tiene que chequear que no sea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			// Tiene que chequear largo
			if (checkLength > 0 && field.length() > checkLength)
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

		} else if (checkIsEmpty) {
			throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
		}

		try {
			if (!field.equals("")) {

				int day = Integer.parseInt(field.substring(0, 2));
				int month = Integer.parseInt(field.substring(2, 4));
				int year = Integer.parseInt(field.substring(4, 8));
				/*int hour = Integer.parseInt(field.substring(11, 13));
				/int minute = Integer.parseInt(field.substring(14, 16));
				/int seconds = Integer.parseInt(field.substring(17, 19));*/

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
			throw new BuildLineException(errorMsgs.get("createdDateInvalidFormat") + index);
		}

		return createdDate;
	}

	BigDecimal validateAndGetBigDecimalField(String field, boolean checkIsEmpty, int index) throws BuildLineException {

		BigDecimal initialBalance = null;

		if (field != null) {

			field = field.trim();

			// Si tiene que chequear que no sea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			if (field != null && !field.equals(""))
				initialBalance = new BigDecimal(field);
		} else if (checkIsEmpty) {
			throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
		}

		return initialBalance;
	}

	private Gender validateAndGetGenderField(String field, boolean checkIsEmpty, int index) throws BuildLineException {
		
		Gender gender = Gender.PENDING;
		if (field != null) {

			field = field.trim();

			// Tiene que chequear que no se vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
			
			if(field.equals("M"))
				gender = Gender.MALE;
			
			if(field.equals("F"))
				gender = Gender.FEMALE;
		}
		return gender;
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

}