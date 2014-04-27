package org.amc.servlet.model;

import java.security.Principal;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyServletUser implements HttpSessionBindingListener
{

	private Principal user;
	
	public MyServletUser(Principal user)
	{
		this.user=user;
	}
	
	public String getName()
	{
		if(user!=null)
		{
			return user.getName();
		}
		else
		{
			return "";
		}
		
	}
	@Override
	public void valueBound(HttpSessionBindingEvent arg0)
	{
		
		System.out.println("User "+getName()+" has logged in");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0)
	{
		System.out.println("User "+getName()+" has logged out");
		
	}
	
}
