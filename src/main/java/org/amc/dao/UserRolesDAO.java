package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.amc.DAOException;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.apache.log4j.Logger;

/**
 * DAO for UserRoles
 * @author Adrian Mclaughlin
 * @version 1
 */
public class UserRolesDAO extends DAO<UserRoles>
{
	
	/**
	 * Serializable UID
	 */
	private static final long serialVersionUID = 3284453615590490609L;
	
	/**
	 * logging service
	 */
	private static Logger LOG=Logger.getLogger(UserRolesDAO.class);
	
	/**
	 * Default Constructor
	 */
	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
	
	/**
	 * 
	 * @param user
	 * @return List of UserRoles
	 * @throws DAOException
	 */
	public List<UserRoles> getEntities(User user) throws DAOException
	{
		EntityManager em=getEntityManager();
		List<UserRoles> roles=null;
		try
		{
			Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
			roles = q.getResultList();
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			LOG.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		return roles;
	}
}
