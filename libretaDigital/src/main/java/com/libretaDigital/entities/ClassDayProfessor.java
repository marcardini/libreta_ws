package com.libretaDigital.entities;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties
public class ClassDayProfessor extends ClassDay implements Serializable {

	private static final long serialVersionUID = -3163875670448524521L;

	private String comment;
	private long notebookId;
	 

	public ClassDayProfessor(Long classDayProfessor, Date date) {
		super(classDayProfessor, date);
	}

	public ClassDayProfessor() {

	}

	public ClassDayProfessor(Long classDayProfessor, Date date, String comment, long notebookId) {
		super(classDayProfessor, date);
		this.comment = comment;
		this.notebookId = notebookId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(long notebookId) {
		this.notebookId = notebookId;
	}

}
