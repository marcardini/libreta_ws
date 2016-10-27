package com.libretaDigital.entities;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private Long oid;
	private String name;
	private List<Notebook> notebooksList;
	private List<Period> periods;
	
	public Subject(){}
	
	public Subject(String name, Period period, Notebook notebook){
		this.notebooksList = new ArrayList<>();
		this.notebooksList.add(notebook);
		
		this.periods = new ArrayList<>();
		this.periods.add(period);
		
		this.name = name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Subject))
			return false;
		Subject that = (Subject) other;
		return this.getName().equalsIgnoreCase(that.getName());
	}
	
	@Override
	public String toString() {
		return this.getName().toUpperCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public List<Notebook> getNotebooksList() {
		return notebooksList;
	}
	public void setNotebooksList(List<Notebook> notebooksList) {
		this.notebooksList = notebooksList;
	}
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
