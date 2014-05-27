package org.amc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.amc.model.Part;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class PartDAO extends DAO implements Serializable
{
	private static final long serialVersionUID = -7746051424830292513L;
	


	public PartDAO(EntityManagerFactory emf)
	{
		super(emf);
	}
	public void addPart(Part product)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		
		em.close();
	}

	public void updatePart(Part product)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
		
		em.close();
	}

	public void deletePart(Part part)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		Part u=em.merge(part);
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 * @param productId
	 * @return Part or null if part not found
	 */
	public Part getPart(String productId)
	{
		Part part=null;
		Query q = getEntityManager().createQuery("Select x from Part x where x.id="
				+ productId + "");
		try
		{
			part = (Part) q.getSingleResult();
		}
		catch(NoResultException nre)
		{
			//do nothing
		}
		return part;
	}

	public List<Part> findParts(String col, String value)
	{
		Query q = getEntityManager().createQuery("Select x from Part x where x." + col + "='"
				+ value + "'");
		List<Part> resultList = (List<Part>) q.getResultList();
		return resultList;
	}

	public List<Part> findParts()
	{
		Query q = getEntityManager().createQuery("Select x from Part x");
		List<Part> resultList = (List<Part>) q.getResultList();
		return resultList;
	}

	

}
