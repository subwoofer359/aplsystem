package org.amc.dao.parsers;

/**
 * No WebFormSearchToJPQL Parser defined 
 * @author Adrian McLaughlin
 *
 */
public class NoSuchWebFormParserException extends Exception
{
	public NoSuchWebFormParserException()
	{
		super();
	}
	
	public NoSuchWebFormParserException(Throwable throwable)
	{
		super(throwable);
	}
	
	public NoSuchWebFormParserException(String message)
	{
		super(message);
	}
}
