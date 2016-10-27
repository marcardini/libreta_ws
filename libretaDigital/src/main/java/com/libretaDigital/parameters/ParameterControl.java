package com.libretaDigital.parameters;

import java.io.Serializable;

public class ParameterControl implements Serializable {

	private static final long serialVersionUID = 756812999261658132L;
	
	private String name;
	
	private Long value;
	private String description;
	private Boolean eliminated;
	private Boolean editableFromConsole;
	
	public ParameterControl() {
		eliminated=false;
		editableFromConsole = true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEliminated() {
		return eliminated;
	}
	public void setEliminated(Boolean eliminated) {
		this.eliminated = eliminated;
	}
	
	public Boolean getEditableFromConsole() {
		return editableFromConsole;
	}
	public void setEditableFromConsole(Boolean editableFromConsole) {
		this.editableFromConsole = editableFromConsole;
	}
		
}
