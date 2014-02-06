package org.amc.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class APLSessionListener implements HttpSessionListener
{
	public static int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0)
	{
		synchronized(this)
		{
			count++;
			System.out.println("(Create) There are "+count+" sessions");
			updateSerlvetContext(arg0);
		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
		synchronized(this)
		{
			if(count>0)
			{
				count--;
			}
			System.out.println("(Destroy) There are "+count+" sessions");
			updateSerlvetContext(arg0);
		}
		
	}
	
	private void updateSerlvetContext(HttpSessionEvent arg0)
	{
		synchronized(arg0.getSession().getServletContext())
		{
			ServletContext context=arg0.getSession().getServletContext();
			context.setAttribute("session_count", count);
		}
	}

}
