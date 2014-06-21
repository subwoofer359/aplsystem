package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.apache.log4j.Logger;

public class UserRolesDAO extends DAO<UserRoles>
{
	private static Logger logger=Logger.getLogger(UserRolesDAO.class);
	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
	
	public List<UserRoles> getEntities(User user) throws DAOException
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		try
		{
			Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
			roles = (List<UserRoles>) q.getResultList();
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			logger.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		return roles;
	}
}
