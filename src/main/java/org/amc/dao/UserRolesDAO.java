package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.amc.model.User;
import org.amc.model.UserRoles;

public class UserRolesDAO extends DAO<UserRoles>
{
	public UserRolesDAO(EntityManager em)
	{
		super(em,UserRoles.class);
	}
	
	public List<UserRoles> getEntities(User user)
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		synchronized (em)
		{
			Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
			roles = (List<UserRoles>) q.getResultList();
		//em.close();
		}
		return roles;
	}
}
