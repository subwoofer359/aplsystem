package org.amc.servlet.el;


import javax.servlet.http.HttpServletRequest;

import org.amc.model.MaterialRemote;

public class MyFunctions
{

	
	public static boolean isUserInRole(HttpServletRequest request,String role)
	{
		return request.isUserInRole(role);
	}
	
	public static String toString(MaterialRemote material)
	{
		System.out.println("Helper Function:"+material.getName());
		 return material.getCompany()+" "+material.getName()+" "+material.getType();
	}
}
