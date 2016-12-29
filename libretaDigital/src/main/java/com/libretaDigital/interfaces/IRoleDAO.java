package com.libretaDigital.interfaces;

import com.libretaDigital.entities.Role;

public interface IRoleDAO extends IGenericDAO<Role>{
	
	Role getRoleByRoleId(Long roleId);
	
	Role getRoleByName(String roleName);
	
}
