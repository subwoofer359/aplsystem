package org.amc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

	public void deletePart(Part job)
	{

	}

	public Part getPart(String productId)
	{
		Query q = getEntityManager().createQuery("Select x from Part x where x.id="
				+ productId + "");
		Part part = (Part) q.getSingleResult();
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
