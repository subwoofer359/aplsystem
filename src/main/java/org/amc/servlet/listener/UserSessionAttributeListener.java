package org.amc.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import java.security.Principal;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class UserSessionAttributeListener
 *
 */
@WebListener
public class UserSessionAttributeListener implements HttpSessionAttributeListener {

   
	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0) 
    {
    	Logger log=(Logger)Logger.getLogger(UserSessionAttributeListener.class);
    	if(arg0.getName().equals("USER"))
    	{
        	Object temp=arg0.getValue();
        	try
        	{
        		if(temp!=null)
        		{
        			Principal user=(Principal)temp;
        			log.info("User:"+user.getName()+" has logged out");
        		}
        		else
        		{
        			log.info("User: unknown has logged in");
        		}
        	}
        	catch(ClassCastException cce)
        	{
        		log.error("Error retrieving User from Session");
        	}
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0) 
    {
        HttpSession session=arg0.getSession();
        Logger log=(Logger)Logger.getLogger(UserSessionAttributeListener.class);
       
        synchronized(session)
        {
        	try
        	{
        		Object temp=session.getAttribute("USER");
        		if(temp!=null)
        		{
        			Principal user=(Principal)temp;
        			log.info("User:"+user.getName()+" has logged in");
        			//System.out.println("User:"+user.getName()+" has logged in");
        		}
        		else
        		{
        			System.out.println("User: unknown has logged in");
        			log.info("User: unknown has logged in");
        			
        		}
        	}
        	catch(ClassCastException cce)
        	{
        		//System.err.println("Error retrieving User from Session");
        		log.error("Error retrieving User from Session");
        	}
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
