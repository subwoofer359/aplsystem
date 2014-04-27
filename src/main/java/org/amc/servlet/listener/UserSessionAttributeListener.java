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

   private static String REMOTE_ADDRESS=""; 
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
    public void attributeAdded(HttpSessionBindingEvent arg0) //FIX
    {
        HttpSession session=arg0.getSession();
        Logger log=(Logger)Logger.getLogger(UserSessionAttributeListener.class);
       
        synchronized(session)
        {
        	try
        	{
        		if(arg0.getName().equals("USER"))
        		{
        			Object temp=session.getAttribute("USER");
        			
        			if(temp!=null)
        			{
        				Principal user=(Principal)temp;	
        				log.info("User:"+user.getName()+" has logged in from "+REMOTE_ADDRESS);
        			//	System.out.println("User:"+user.getName()+" has logged in");
        			}
        		}
        		else
        		if(arg0.getName().equals("REMOTE_ADDRESS"))
        		{
        			Object remote_address=session.getAttribute("REMOTE_ADDRESS");
        			if(remote_address!=null)
    				{
        				synchronized(UserSessionAttributeListener.class)
        				{
        					REMOTE_ADDRESS=(String)remote_address;
        				}
    				}
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
