package org.amc.servlet.el;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.amc.model.Material;

public final class MyFunctions
{

	private static Logger logger=Logger.getLogger(MyFunctions.class);
	
	/**
	 * Utility class
	 */
	private MyFunctions()
	{
	}
	
	public static boolean isUserInRole(HttpServletRequest request,String role)
	{
		return request.isUserInRole(role);
	}
	
	public static String toString(Material material)
	{
		 logger.info("Helper Function:"+material.getName());
		 return material.getCompany()+" "+material.getName()+" "+material.getType();
	}
}
