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
	}
	
	public final static String PERSISTENCE_UNIT_NAME="myDatabase";
	public final static String PASSWORD_DIGEST="SHA-256";
	public final static String PASSWORD_DEFAULT="PaSsWoRd24432322535342";
}
