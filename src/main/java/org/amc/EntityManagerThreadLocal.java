package org.amc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerThreadLocal
{
	private static EntityManagerFactory factory;
	private final static ThreadLocal<EntityManager> entityManager=new ThreadLocal<EntityManager>()
			{

				@Override
				protected EntityManager initialValue()
				{
					// TODO Auto-generated method stub
					return factory.createEntityManager();
				}
			
			};
	
	
	public static void setEntityManagerFactory(EntityManagerFactory factory)
	{
		EntityManagerThreadLocal.factory=factory;
	}
	
	public static EntityManager getEntityManager()
	{
		return entityManager.get();
	}
	
	public static void closeEntityManager()
	{
		entityManager.get().close();
		entityManager.remove();
	}

}
