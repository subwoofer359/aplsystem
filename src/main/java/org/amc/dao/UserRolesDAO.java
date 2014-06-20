package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.model.User;
import org.amc.model.UserRoles;

public class UserRolesDAO extends DAO<UserRoles>
{
	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
	
	public List<UserRoles> getEntities(User user) throws DAOException
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
		roles = (List<UserRoles>) q.getResultList();
		return roles;
	}
}
