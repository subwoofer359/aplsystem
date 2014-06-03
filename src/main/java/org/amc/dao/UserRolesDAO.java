package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.amc.model.User;
import org.amc.model.UserRoles;

public class UserRolesDAO extends DAO<UserRoles>
{
	public UserRolesDAO(EntityManagerFactory emf)
	{
		super(emf,UserRoles.class);
	}
	
	public List<UserRoles> getEntities(User user)
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
		roles = (List<UserRoles>) q.getResultList();
		em.close();
		return roles;
	}
}
