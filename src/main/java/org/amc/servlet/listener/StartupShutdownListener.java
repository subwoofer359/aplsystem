package org.amc.servlet.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class StartupShutdownListener implements ServletContextListener 
{

	public static Logger log = Logger.getLogger(StartupShutdownListener.class.toString());
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		Logger log=(Logger)org.apache.log4j.Logger.getLogger(StartupShutdownListener.class);
		log.info("Servlet shut down....");
		deregisterDatabaseDrivers();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		//Setting up logger
		//System.out.println(arg0.getServletContext().getRealPath("/")+"WEB-INF/log4j.properties");
		//PropertyConfigurator.configure("log4j.properties");
		log.info("Servlet started up....");
		
	}
	
	/**
	 * When the application is stopped JDBC drivers ared deregistered to help prevent a memory leak
	 */
	public void deregisterDatabaseDrivers()
	{
		Enumeration<Driver> list=DriverManager.getDrivers();
		while(list.hasMoreElements())
		{
			Driver driver=list.nextElement();
			try
			{
				DriverManager.deregisterDriver(driver);
			}
			catch(Exception e)
			{
				log.debug("In attempt to deregister driver:"+driver+" an exception has thrown:"+e.getMessage());
			}
		}
	}
}
