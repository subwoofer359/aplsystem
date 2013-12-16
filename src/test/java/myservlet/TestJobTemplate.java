package myservlet;
import org.amc.servlet.model.Part;
import org.junit.*;
import static org.junit.Assert.*;

public class TestJobTemplate
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
	private Part job=new Part();
	
	@Before
	public void init()
	{
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
