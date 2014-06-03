package org.amc.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;

public class SPCDAO extends DAO implements Serializable
{

	private static final long serialVersionUID = -5347530850042314728L;
	
	public SPCDAO(EntityManagerFactory emf)
	{
		super(emf);
	}
	
	public void addSPCMeasurement(SPCMeasurement measurement)
	{
		EntityManager em=getEntityManager();
		em.getTransaction().begin();
		em.persist(measurement);
		em.getTransaction().commit();
		em.close();
	}

}
