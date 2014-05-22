package org.amc.dao;

import java.io.Serializable;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.amc.dao.MouldingProcessDAO;
import org.amc.model.MouldingProcess;


public class MouldingProcessDAO implements Serializable
{
	private static final long serialVersionUID = 7577290113094820714L;

	private EntityManager em;
	public MouldingProcessDAO()
	{
		;;
	}

	public void addProcessSheet(MouldingProcess process)
	{
		em.getTransaction().begin();
		em.persist(process);
		em.getTransaction().commit();
	}

	public void updateProcessSheet(MouldingProcess process)
	{
		em.merge(process);
	}

	public void deleteProcessSheet(MouldingProcess process)
	{

	}

	public MouldingProcess getProcessSheet(String processId)
	{

		Query q=em.createQuery("Select x from MouldingProcess x where x.id="+processId+"");
		MouldingProcess mp = (MouldingProcess)q.getSingleResult();
		return mp;

	}

	public List<MouldingProcess> findProcessSheets(String col, String value)
	{

		Query q=em.createQuery("Select x from MouldingProcess x where x."+col+"='"+value+"'");
		List<MouldingProcess> resultList=(List<MouldingProcess>)q.getResultList();
		return resultList;
	}

	public List<MouldingProcess> findProcessSheets() 
	{
		Query q=em.createQuery("Select x from MouldingProcess x");
		List<MouldingProcess> resultList=(List<MouldingProcess>)q.getResultList();
		return resultList;
	}

	@PersistenceUnit(name="myDatabase")
	public void setEm(EntityManager em)
	{
		this.em = em;
	}	


}
