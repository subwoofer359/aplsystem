package org.amc.servlet.el;


import javax.servlet.http.HttpServletRequest;

import org.amc.model.MaterialBeanRemote;

public class MyFunctions
{

	
	public static boolean isUserInRole(HttpServletRequest request,String role)
	{
		return request.isUserInRole(role);
	}
	
	public static String toString(MaterialBeanRemote material)
	{
		 return material.getCompany()+" "+material.getName()+" "+material.getType();
	}
}
