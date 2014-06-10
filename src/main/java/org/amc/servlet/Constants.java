package org.amc.servlet;

public class Constants
{
	public enum roles
	{
		QC,
		MANAGER,
		GUEST;
		public String toString()
		{
			return super.toString().toLowerCase();
		}
	}
}
