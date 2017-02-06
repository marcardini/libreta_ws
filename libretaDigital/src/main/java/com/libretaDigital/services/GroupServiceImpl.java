package com.libretaDigital.services;

import java.util.List;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.libretaDigital.DAO.*;
import com.libretaDigital.entities.Group;
import com.libretaDigital.exceptions.*;
import com.libretaDigital.interfaces.*;

public class GroupServiceImpl implements IGroupService{

	private GroupDAO groupDAO;
	private MessageDigestPasswordEncoder encoder;
	
	public List<Group> getAllGroups() {
		return groupDAO.getAllGroups();
	}
	
	public void addGroup(Group dtGroup) throws GroupAlreadyExists, InvalidGroupInformation {
		
		if (groupDAO.getGroupByNameAndYear(dtGroup.getName(),dtGroup.getYear()) != null)
			throw new GroupAlreadyExists();
				
		groupDAO.saveOrUpdate(dtGroup);
	}
	
	@Override
	public Group getGroupByNameAndYear(String name, int year){
		return groupDAO.getGroupByNameAndYear(name, year);
	}
	
	@Override
	public void deleteGroups(List<Long> items) {
		
		for (Long oid : items) {
			Group group = groupDAO.getById(oid);
			if(group != null){
				groupDAO.delete(group);
			}
		}
	}

	public MessageDigestPasswordEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(MessageDigestPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
}
