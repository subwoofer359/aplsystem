package org.amc;


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
		 * @return an array of Roles as Strings
		 */
		public static String[] getStringValues()
		{
			final Roles[] roles=Roles.values();
			String[] roleStrings=new String[roles.length];
			
			for(int i=0;i<roleStrings.length;i++)
			{
				roleStrings[i]=roles[i].toString();
			}
			return roleStrings;
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
