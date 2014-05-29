package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.amc.model.User;
import org.amc.model.UserRoles;

public class UserRolesDAO extends DAO
{
	public UserRolesDAO(EntityManagerFactory emf)
	{
		super(emf);
	}
	
	public List<UserRoles> getUserRoles(User user)
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
		roles = (List<UserRoles>) q.getResultList();
		em.close();
		return roles;
	}
	
	public void deleteUserRole(UserRoles role)
	{
		EntityManager em=getEntityManager();
		try
		{
			em.getTransaction().begin();
			UserRoles u=em.merge(role);
			em.remove(u);
			em.getTransaction().commit();
		}
		catch(RollbackException rbe)
		{
			rbe.printStackTrace();
		}
		finally
		{
			em.close();
		}
	}

}
