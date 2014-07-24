package org.amc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Holds the global constants for the Application
 * @author Adrian Mclaughlin
 * @version 1
 *
 */
public final class Constants
{
	/**
	 * OpenJPA Persistence Unit name
	 */
	public static final String PERSISTENCE_UNIT_NAME="myDatabase";
	
	/**
	 *	The digest hashing algorithm used by the application 
	 */
	public static final String PASSWORD_DIGEST="SHA-256";
	
	/**
	 * Password to be passed to user so the user's real password is not exposed
	 */
	public static final String PASSWORD_DEFAULT="PaSsWoRd24432322535342";
	
	/**
	 * Session variable to hold the User Object
	 */
	public static final String SESSIONVAR_USER="USER";
	
	/**
	 * Session variable to hold the Remote Address of the User
	 */
	public static final String SESSIONVAR_REMOTE_ADDRESS="REMOTE_ADDRESS";
	
	/**
	 * Spring WebAPP Context 
	 */
	
	public final static String SPRING_WEBAPPCONTEXT="org.springframework.web.context.WebApplicationContext.ROOT";
	
	/**
	 * 
	 * Availible security roles
	 * Must mirror security roles defined in the deployment descriptor 
	 *
	 */
	public static enum Roles
	{
		QC,
		MANAGER,
		GUEST;
		
		/**
		 * Overriding toString method of Enum
		 * @return String representation of the Role enum
		 */
		@Override
		public String toString()
		{
			return super.toString().toLowerCase();
		}
		
		/**
		 * 
		 * @return a List of Roles as Strings
		 */
		public static List<String> getStringValues()
		{
			List<String> roleList=new ArrayList<String>();
			for(Roles role:Roles.values())
			{
				roleList.add(role.toString());
			}
			return Collections.unmodifiableList(roleList);
		}
	}
	
	/**
	 * Utility class so Constructor is not to be called
	 */
	private Constants()
	{
		throw new AssertionError("Utility Class only");
	}
}
