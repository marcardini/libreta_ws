package com.libretaDigital.interfaces;

import java.util.List;
import com.libretaDigital.entities.*;
import com.libretaDigital.exceptions.*;

public interface IGroupService {

	List<Group> getAllGroups();

	void addGroup(Group dtGroup) throws GroupAlreadyExists, InvalidGroupInformation;
	
}
