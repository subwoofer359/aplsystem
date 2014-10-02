package org.amc.servlet.listener;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.model.User;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

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
    	Logger log=Logger.getLogger(UserSessionAttributeListener.class);
    	if(arg0.getName().equals("USER"))
    	{
        	Object temp=arg0.getValue();
        	try
        	{
        		if(temp!=null)
        		{
        			User user=(User)temp;
        			log.info("User:"+user.getUserName()+" has logged out");
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
        Logger log=Logger.getLogger(UserSessionAttributeListener.class);
       
        synchronized(session)
        {
        	try
        	{
        		if(arg0.getName().equals("USER"))
        		{
        			Object temp=session.getAttribute("USER");
        			
        			if(temp!=null)
        			{
        				User user=(User)temp;	
        				log.info("User:"+user.getUserName()+" has logged in from "+REMOTE_ADDRESS);
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
