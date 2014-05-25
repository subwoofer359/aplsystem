package org.amc.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.amc.model.Part;

public class PartDAO extends DAO implements Serializable
{
	private static final long serialVersionUID = -7746051424830292513L;
	


	public PartDAO()
	{
		;;
	}

	public void addPart(Part job)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		
		em.close();
	}

	public void updatePart(Part job)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.merge(job);
		em.getTransaction().commit();
		
		em.close();
	}

	public void deletePart(Part job)
	{

	}

	public Part getPart(String jobTemplateId)
	{
		Query q = getEntityManager().createQuery("Select x from Part x where x.id="
				+ jobTemplateId + "");
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
