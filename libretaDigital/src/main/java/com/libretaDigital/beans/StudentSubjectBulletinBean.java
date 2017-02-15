package com.libretaDigital.beans;



import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import com.libretaDigital.entities.ClassDayStudent;


@JsonAutoDetect
public class StudentSubjectBulletinBean{

	
	private List<ClassDayStudent> calendar;
	private String subjectName;
			
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public List<ClassDayStudent> getCalendar() {
		return calendar;
	}
	public void setCalendar(List<ClassDayStudent> calendar) {
		this.calendar = calendar;
	}
	
	public StudentSubjectBulletinBean() {
		// TODO Auto-generated constructor stub
	}
	

	
}
