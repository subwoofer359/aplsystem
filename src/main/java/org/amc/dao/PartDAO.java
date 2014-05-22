package org.amc.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.amc.model.MouldingProcess;
import org.amc.model.Part;

public class PartDAO implements Serializable
{
	private static final long serialVersionUID = -7746051424830292513L;
	private EntityManager em;

	public PartDAO()
	{
		;;
	}

	public void addPart(Part job)
	{
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
	}

	public void updatePart(Part job)
	{
		em.merge(job);
	}

	public void deletePart(Part job)
	{

	}

	public Part getPart(String jobTemplateId)
	{
		Query q = em.createQuery("Select x from Part x where x.id="
				+ jobTemplateId + "");
		Part part = (Part) q.getSingleResult();
		return part;
	}

	public List<Part> findParts(String col, String value)
	{
		Query q = em.createQuery("Select x from Part x where x." + col + "='"
				+ value + "'");
		List<Part> resultList = (List<Part>) q.getResultList();
		return resultList;
	}

	public List<Part> findParts()
	{
		Query q = em.createQuery("Select x from Part x where x");
		List<Part> resultList = (List<Part>) q.getResultList();
		return resultList;
	}

	@PersistenceUnit(name = "myDatabase")
	public void setEm(EntityManager em)
	{
		this.em = em;
	}

}
