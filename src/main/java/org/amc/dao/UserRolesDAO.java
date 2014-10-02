package org.amc.dao;

import org.amc.DAOException;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.apache.log4j.Logger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
	 * Fetch the list of roles for this user
	 * 
	 * @param user the User
	 * @return List of UserRoles
	 * @throws DAOException
	 * @see User
	 */
	public List<UserRoles> getEntities(User user) throws DAOException
	{
		EntityManager em=getEntityManager();
		try
		{
			Query q = em.createQuery("Select x from UserRoles x where x.userName='"
				+ user.getUserName() + "'");
			@SuppressWarnings("unchecked")
			List<UserRoles> roles= q.getResultList();
			return roles;
		}
		catch(PersistenceException pe)
		{
			em.getTransaction().rollback();
			LOG.error("DAO<"+getEntityClass().getSimpleName()+">:Error has occurred when trying to find entities");
			throw new DAOException(pe);
		}
		
	}
}
