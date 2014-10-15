package org.amc.dao.parsers;

/**
 * No WebFormSearchToJPQL Parser defined 
 * @author Adrian McLaughlin
 *
 */
public class NoSuchWebFormParser extends Exception
{
	public NoSuchWebFormParser()
	{
		super();
	}
	
	public NoSuchWebFormParser(Throwable throwable)
	{
		super(throwable);
	}
	
	public NoSuchWebFormParser(String message)
	{
		super(message);
	}
}
