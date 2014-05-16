package org.amc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.amc.model.PartBeanRemote;

@Stateless(mappedName="PartDAOBeanRemote")
public class PartDAOBean extends BasicDAOBean implements PartDAOBeanRemote, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7746051424830292513L;
	public PartDAOBean()
	{
	}


	private static String tablename="jobtemplate";
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#addJobTemplate(org.amc.servlet.model.JobTemplate)
	 */
	@Override
	public void addPart(PartBeanRemote job) throws SQLException
	{
		//id,name,company,colour,external,part_id,qss_no, revision,version
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("INSERT INTO "+tablename+" VALUES(NULL,?,?,?,?,?,?,?,?)");
		statement.setString(1, job.getName());
		statement.setString(2, job.getCompany());
		statement.setString(3, job.getColour());
		statement.setBoolean(4, job.getExternal());
		statement.setString(5, job.getPart_id());
		statement.setString(6, job.getQss_no());
		statement.setString(7, job.getRevision());
		statement.setString(8, job.getVersion());
		
		statement.executeUpdate();
		closeDBObjects(null, statement, connection);
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#updateJobTemplate(org.amc.servlet.model.JobTemplate)
	 */
	@Override
	public void updatePart(PartBeanRemote job) throws SQLException
	{
		//id,name,company,colour,external,part_id,qss_no, revision,version
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("UPDATE "+tablename+" set name=?,"
				+ "company=?,"
				+ "colour=?,"
				+ "external=?,"
				+ "part_id=?,"
				+ "qss_no=?,"
				+ "revision=?,"
				+ "version=? where id=?");
				statement.setString(1, job.getName());
				statement.setString(2, job.getCompany());
				statement.setString(3, job.getColour());
				statement.setBoolean(4, job.getExternal());
				statement.setString(5, job.getPart_id());
				statement.setString(6, job.getQss_no());
				statement.setString(7, job.getRevision());
				statement.setString(8, job.getVersion());
				statement.setString(9, String.valueOf(job.getId()));
				
	
				statement.executeUpdate();
				closeDBObjects(null, statement, connection);
		
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#deleteJobTemplate(org.amc.servlet.model.JobTemplate)
	 */
	@Override
	public void deletePart(PartBeanRemote job)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#getJobTemplate(int)
	 */
	@Override
	public PartBeanRemote getPart(String jobTemplateId) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where id=?;");
		statement.setString(1, jobTemplateId);
		ResultSet rs=statement.executeQuery();
		PartBeanRemote tempJob=null;
		if(rs.next())
		{
			tempJob=getPart(rs);
		}
		closeDBObjects(rs, statement, connection);
		return tempJob;
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#findJobTemplates(java.lang.String, java.lang.String)
	 */
	@Override
	public List<PartBeanRemote> findParts(String col,String value) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where "+col+" REGEXP ?;");
		
		statement.setString(1, value);
		ResultSet rs=statement.executeQuery();
		List<PartBeanRemote> list=new ArrayList<PartBeanRemote>();
		
		while(rs.next())
		{
			PartBeanRemote tempJob=getPart(rs);
			list.add(tempJob);
		}
		closeDBObjects(rs, statement, connection);
		
		return list;
		
	}

	@Override
	public List<PartBeanRemote> findParts() throws SQLException 
	{
		Connection connection=getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from "+tablename+";");
		List<PartBeanRemote> list=new ArrayList<PartBeanRemote>();
		
		while(rs.next())
		{
			PartBeanRemote tempJob=getPart(rs);
			list.add(tempJob);
		}
		
		closeDBObjects(rs, statement, connection);
		return list;
	}
	
	
	//Don't call next or close the ResultSet
	private PartBeanRemote getPart(ResultSet rs) throws SQLException
	{

		PartBeanRemote tempPart = getPartBean();

		tempPart.setName(rs.getString("name"));
		tempPart.setPart_id(rs.getString("part_id"));
		tempPart.setCompany(rs.getString("company"));
		tempPart.setVersion(rs.getString("version"));
		tempPart.setRevision(rs.getString("revision"));
		tempPart.setColour(rs.getString("colour"));
		tempPart.setExternal(rs.getBoolean("external"));
		tempPart.setQss_no(rs.getString("qss_no"));
		tempPart.setId(rs.getInt("ID"));
		return tempPart;

	}
	private PartBeanRemote getPartBean()
	{
		
		InitialContext ctx;
		PartBeanRemote part=null;
		try {
	    	  Properties props = new Properties();
	    	  props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
	    	  //props.put(Context.PROVIDER_URL,"ejbd://127.0.0.1:4201");
	         ctx = new InitialContext(props);
	         part=(PartBeanRemote)ctx.lookup("PartBeanRemote");
	      } catch (NamingException ex) {
	         ex.printStackTrace();
	      }
		return part;
	}
}
