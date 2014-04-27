package org.amc.myservlet.test;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.amc.model.PartBeanRemote;
import org.junit.*;

import static org.junit.Assert.*;

public class TestPart
{
	
	private int id=4;
	private String colour="Red";
	private String company="Tosara";
	private boolean external=true;
	private String name="Pot";
	private String part_id="33-004";
	private String qss_no="QSS:122";
	private String revision="2";
	private String version="60g";
	
	//private JobTemplate job=mock(JobTemplate.class);
	private PartBeanRemote job;
	
	@Before
	public void init()
	{
		InitialContext ctx;
		PartBeanRemote part=null;
		try {
		   	  Properties props = new Properties();
		   	  props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
		   	  props.put(Context.PROVIDER_URL,"ejbd://127.0.0.1:4201");
		      ctx = new InitialContext(props);
		      part=(PartBeanRemote)ctx.lookup("PartBeanRemote");
		     } catch (NamingException ex) {
		         ex.printStackTrace();
		}
			
			
		job.setId(this.id);
		job.setColour(colour);
		job.setCompany(company);
		job.setExternal(external);
		job.setName(name);
		job.setPart_id(part_id);
		job.setQss_no(qss_no);
		job.setRevision(revision);
		job.setVersion(version);
	}
	
	@Test
	public void testGetters()
	{
		System.out.println(job);
		assertEquals(job.getId(),id);
		assertEquals(job.getColour(),colour);
		assertEquals(job.getCompany(),company);
		assertEquals(job.getExternal(),external);
		assertEquals(job.getName(),name);
		assertEquals(job.getPart_id(),part_id);
		assertEquals(job.getQss_no(),qss_no);
		assertEquals(job.getRevision(),revision);
		assertEquals(job.getVersion(),version);
	}
}
