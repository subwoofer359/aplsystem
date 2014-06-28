package org.amc;


/**
 * Holds the global constants for the Application
 * @author Adrian Mclaughlin
 * @version 1
 *
 */
public class Constants
{
	
	private Constants()
	{
		//Utility Class
	}
	
	public static enum Roles
	{
		QC,
		MANAGER,
		GUEST;
		public String toString()
		{
			return super.toString().toLowerCase();
		}
		public static String[] getStringValues()
		{
			Roles[] r=Roles.values();
			String[] roleStrings=new String[r.length];
			
			for(int i=0;i<roleStrings.length;i++)
			{
				roleStrings[i]=r[i].toString();
			}
			return roleStrings;
		}
	}
	
	public final static String PERSISTENCE_UNIT_NAME="myDatabase";
	public final static String PASSWORD_DIGEST="SHA-256";
	public final static String PASSWORD_DEFAULT="PaSsWoRd24432322535342";
}
