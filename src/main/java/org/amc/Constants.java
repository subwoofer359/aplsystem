package org.amc;

public class Constants
{
	public static enum roles
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
			roles[] r=roles.values();
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
