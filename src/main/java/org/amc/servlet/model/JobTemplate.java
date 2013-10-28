package org.amc.servlet.model;


import java.util.HashMap;
import java.util.Map;
import static org.amc.servlet.model.Fields.*;
/**
 * <p>
 * Project: APL_Problem_Database
 * </p>
 * <p>
 * file: $URL: file:///home/adrian/Documents/SVNRepository/Java/APL_Problem_Database/trunk/APL_Problem_Database/src/apl_problem_database/JobTemplate.java $
 * <p>
 * <p>
 * Created on May 4, 2006
 * </p>
 * 
 * @author adrian
 * @version $Revision: 888 $
 * Represents a job running in the factory
 */

public class JobTemplate
{

	Map<Fields,String> properties; 
	public static final Fields[] fields={
		ID, 		/** Identifier 								*/
		COMPANY,	/** company for whom the part was created 	*/
		NAME,	 	/** Product name 							*/
		PRODUCT_ID,
		VERSION, 
		REVISION,
		COLOUR, 	/** colour of part 							*/
		EXTERNAL, 	/** Is it an external part					*/
		QSS_NO
	};
	
	/**
	 * 
	 * Constructor for JobTemplate.java
	 */
	public JobTemplate()
	{
		super();
		properties=new HashMap<Fields,String>();
	}
	
	/**
	 * 
	 * Constructor for JobTemplate.java
	 * @param name
	 * @param part_no
	 * @param company
	 * @param version
	 * @param revision
	 * @param colour
	 * @param external
	 */
	public JobTemplate(String name,
						String part_no,
						String company,
						String version,
						String revision,
						String colour,
						String external,
						String qss_no
					)
	{
			this();
			properties.put(NAME,name);
			properties.put(PART_ID,part_no);
			properties.put(COMPANY,company);
			properties.put(VERSION,version);
			if(revision!=null)
			{
				properties.put(REVISION,revision);
			}
			properties.put(COLOUR,colour);
			properties.put(EXTERNAL,String.valueOf(external));
			if(qss_no!=null)
			{
				properties.put(QSS_NO,qss_no);
			}
			
	}

	@Override
	public String toString()
	{
		String value= String.valueOf(properties.get(NAME))+" ";
		value=value+String.valueOf(properties.get(VERSION))+" ";
		value=value+String.valueOf(properties.get(REVISION))+" ";
		value=value+String.valueOf(properties.get(COLOUR));
		return value;
	}

	/**
	 * Getters and Setters
	 * 
	 */
	
	public String getName()
	{
		return properties.get(NAME);
	}
	
	public String getPart_No()
	{
		return properties.get(PART_ID);
	}
	public String getCompany()
	{
		return properties.get(COMPANY);
	}
	public String getVersion()
	{
		return properties.get(VERSION);
	}
	public String getRevision()
	{
		return properties.get(REVISION);
	}
	public String getColour()
	{
		return properties.get(COLOUR);
	}
	
	public String getExternal()
	{
		return properties.get(EXTERNAL);
	}
	public String getQSS_No()
	{
		return properties.get(QSS_NO);
	}
	
	public Fields[] getFields()
	{
		return fields;
	}
	
	
	
	public void setName(String name)
	{
		properties.put(NAME,name);
	}
	
	public void setPart_No(String part_no)
	{
		properties.put(PART_ID,part_no);
	}
	public void setCompany(String company)
	{
		properties.put(COMPANY,company);
	}
	public void setVersion(String version)
	{
		properties.put(VERSION,version);
	}
	public void setRevision(String revision)
	{
		properties.put(REVISION,revision);
	}
	public void setColour(String colour)
	{
		properties.put(COLOUR,colour);
	}
	
	public void setExternal(String external)
	{
		properties.put(EXTERNAL,external);
	}
	public void setQSS_No(String qss_no)
	{
		properties.put(QSS_NO,qss_no);
	}
	
	public static void main(String[] args)
	{
		JobTemplate job=new JobTemplate();
		
		job.setName("15g jar");
		job.setColour("grey");
		job.setCompany("tosara");
		job.setExternal("true");
		job.setQSS_No("1234D");
		System.out.println(job);
		JobTemplate job2=new JobTemplate("lid","12334232ds","Tosara","15/25g","1.0","grey","true","123D");
		System.out.println(job2);		
	}
}
