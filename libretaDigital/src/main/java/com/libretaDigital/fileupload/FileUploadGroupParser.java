package com.libretaDigital.fileupload;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.libretaDigital.exceptions.BuildLineException;
import com.libretaDigital.interfaces.IFileParser;

public class FileUploadGroupParser implements IFileParser {

	protected String splitChar;
	protected String subClassSplitChar;
	protected String fieldsInvalidMsg;
	private HashMap<String, String> errorMsgs;


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

		groupUploadLine.setUpoloadProcessorId(UploadProcessorId.GROUP);

		return groupUploadLine;
	}

	private String validateAndGetStringField(String field, boolean checkIsEmpty, int checkLength, int index)
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
	
	private int validateAndGetIntField(String field, boolean checkIsEmpty, int index) throws BuildLineException {

		int result = 0;

		if (field != null) {

			// Si tiene que chequear que no sea vacio
			if (checkIsEmpty && field.equals(""))
				throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);

			if (field != null && !field.equals(""))
				result = Integer.parseInt(field);
		} else if (checkIsEmpty) {
			throw new BuildLineException(errorMsgs.get("invalidAccountRecordLengthField") + index);
		}

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

}