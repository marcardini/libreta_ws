package com.libretaDigital.interfaces;

import java.util.List;

import com.libretaDigital.entities.*;

public interface INotebookDAO extends IGenericDAO<Group>{
	
	List<Group> getAllGroups();
	
	Group getGroupByNameAndYear(String name, int year);
	
}
