package com.libretaDigital.interfaces;

import java.util.List;

import com.libretaDigital.entities.Group;

public interface IGroupDAO extends IGenericDAO<Group>{
	
	List<Group> getAllGroups();
	
	Group getGroupByNameAndYear(String name, int year);
	
}
