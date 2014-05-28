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
		EntityManager em=getEntityManager();
		Part part=null;
		Query q = em.createQuery("Select x from Part x where x.id="+ productId + "");
		part = (Part) q.getSingleResult();
		em.close();
		return part;
	}

	public List<Part> findParts(String col, String value)
	{
		EntityManager em=getEntityManager();
		List<Part> resultList=null;
		Query q = em.createQuery("Select x from Part x where x." + col + "='"
				+ value + "'");
		resultList = (List<Part>) q.getResultList();
		em.close();
		return resultList;
	}

	public List<Part> findParts()
	{
		
		List<Part> resultList=null;
		EntityManager em=getEntityManager();
		Query q = em.createQuery("Select x from Part x");
		resultList = (List<Part>) q.getResultList();
		em.close();
		return resultList;
	}

	

}
