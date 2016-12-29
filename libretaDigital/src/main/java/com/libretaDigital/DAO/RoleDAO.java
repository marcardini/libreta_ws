package com.libretaDigital.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.libretaDigital.hibernate.GenericDAO;
import com.libretaDigital.interfaces.IRoleDAO;
import com.libretaDigital.entities.Permission;
import com.libretaDigital.entities.PermissionType;
import com.libretaDigital.entities.Privilege;
import com.libretaDigital.entities.Role;

public class RoleDAO extends GenericDAO<Role> implements IRoleDAO {

	private static Logger log = Logger.getLogger(RoleDAO.class);

	@Override
	public Role getRoleByRoleId(Long roleId) {

		@SuppressWarnings("unchecked")
		Role role = (Role) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			Role result = new Role();

			public Role doInHibernate(Session oSession) throws HibernateException {

				String oQuery = "select r.oid, r.name from roles r where r.oid = :roleId";

				SQLQuery query = oSession.createSQLQuery(oQuery);

				query.setLong("roleId", roleId);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getRoleFromPartialResult(partialResult);

				return result;
			}
		});

		return role;
	}

	private Role getRoleFromPartialResult(List<Object[]> partialResult) {

		Role role = new Role();

		for (Object[] oPartialResult : partialResult) {

			if (oPartialResult[0] != null && !oPartialResult[0].equals("")) {
				BigInteger id = (BigInteger) oPartialResult[0];
				role.setOid(id.longValue());
			}

			if (oPartialResult[1] != null && !oPartialResult[1].equals("")) {
				String name = (String) oPartialResult[1];
				role.setName(name);
			}

			role.setPrivileges(getPrivilegesFromRoleId(role.getOid()));
		}

		return role;
	}

	private List<Privilege> getPrivilegesFromRoleId(Long roleId) {

		return getHibernateTemplate().execute(new HibernateCallback<List<Privilege>>() {

			List<Privilege> result = new ArrayList<Privilege>();

			@SuppressWarnings("unchecked")
			@Override
			public List<Privilege> doInHibernate(Session session) throws HibernateException {
				String oQuery = "select pri.oid, per.name, pri.permissionType "
						+ "from privileges pri, permissions per " + "where pri.permissionEntityId = per.oid "
						+ "and pri.role_id = :roleId";

				SQLQuery query = session.createSQLQuery(oQuery);

				query.setLong("roleId", roleId);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getPrivilegesByRoleIdFromPartialResult(partialResult);

				return result;
			}
		});
	}

	private List<Privilege> getPrivilegesByRoleIdFromPartialResult(List<Object[]> partialResult) {

		List<Privilege> result = new ArrayList<Privilege>();

		for (Object[] oPartialResult : partialResult) {
			Privilege privilege = new Privilege();
			Permission permission = new Permission();

			BigInteger bid = (BigInteger) oPartialResult[0];
			privilege.setOid(bid.longValue());
			
			/*BigInteger permissionEntityId = (BigInteger) oPartialResult[1];
			permission.setOid(permissionEntityId.longValue());*/
			
			String permissionName = (String)oPartialResult[1];
			permission.setName(permissionName);
			
			privilege.setPermissionEntity(permission);
			
			String permissionType = (String)oPartialResult[2];
			privilege.setPermissionType(PermissionType.valueOf(permissionType));

			result.add(privilege);
		}
		return result;
	}
	
	@Override
	public Role getRoleByName(String roleName) {

		@SuppressWarnings("unchecked")
		Role role = (Role) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			Role result = new Role();

			public Role doInHibernate(Session oSession) throws HibernateException {

				String oQuery = "select r.oid, r.name from roles r where r.name = :roleName";

				SQLQuery query = oSession.createSQLQuery(oQuery);

				query.setString("roleName", roleName);

				List<Object[]> partialResult = query.list();

				if (partialResult != null && !partialResult.isEmpty())
					result = getRoleFromPartialResult(partialResult);

				return result;
			}
		});

		return role;
	}
}