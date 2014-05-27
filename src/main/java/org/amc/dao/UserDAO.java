package org.amc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.amc.model.User;

public class UserDAO extends DAO
{
	public UserDAO(EntityManagerFactory emf)
	{
		super(emf);
	}

	public void addUser(User user)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void updateUser(User user)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		
		em.close();
	}

	public void deleteUser(User user)
	{

		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		User u=em.merge(user);
		em.remove(u);
		em.getTransaction().commit();
		
		em.close();
	}

	/**
	 * 
	 * @param userId
	 * @return User or null if user doesn't exist
	 */
	public User getUser(String userId)
	{
		User user=null;
		Query q = getEntityManager().createQuery("Select x from User x where x.id="
				+ userId + "");
		try
		{
			user = (User) q.getSingleResult();
		}
		catch(NoResultException nre)
		{
			//do nothing
		}
		return user;
	}

	public List<User> findUsers(String col, String value)
	{
		Query q = getEntityManager().createQuery("Select x from User x where x." + col + "='"
				+ value + "'");
		List<User> resultList = (List<User>) q.getResultList();
		return resultList;
	}

	public List<User> findUsers()
	{
		Query q = getEntityManager().createQuery("Select x from User x");
		List<User> resultList = (List<User>) q.getResultList();
		return resultList;
	}
}
