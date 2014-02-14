package org.amc.servlet.listener;

import org.apache.log4j.Logger; 

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sun.security.auth.UserPrincipal;

@WebListener
public class APLSessionListener implements HttpSessionListener
{
	private static Logger logger=Logger.getLogger(APLSessionListener.class);
	public static int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0)
	{
		synchronized(this)
		{
			count++;
			logger.info("(Create) There are "+count+" sessions");
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
			logger.info("(Destroy) There are "+count+" sessions");
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
