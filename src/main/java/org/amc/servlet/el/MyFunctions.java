package org.amc.servlet.el;


import javax.servlet.http.HttpServletRequest;

public class MyFunctions
{

	
	public static boolean isUserInRole(HttpServletRequest request,String role)
	{
		return request.isUserInRole(role);
	}
}
