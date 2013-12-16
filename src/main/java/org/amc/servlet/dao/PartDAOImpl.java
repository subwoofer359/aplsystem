package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.amc.servlet.model.Part;

public class PartDAOImpl extends BasicDAO implements PartDAO 
{

	private static String tablename="jobtemplate";
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#addJobTemplate(org.amc.servlet.model.JobTemplate)
	 */
	@Override
	public void addPart(Part job) throws SQLException
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
	public void updatePart(Part job) throws SQLException
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
	public void deletePart(Part job)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see org.amc.servlet.dao.JobTemplateDAO#getJobTemplate(int)
	 */
	@Override
	public Part getPart(String jobTemplateId) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where id=?;");
		statement.setString(1, jobTemplateId);
		ResultSet rs=statement.executeQuery();
		Part tempJob=null;
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
	public List<Part> findParts(String col,String value) throws SQLException
	{
		Connection connection=getConnection();
		PreparedStatement statement=connection.prepareStatement("select * from "+tablename+" where "+col+" REGEXP ?;");
		
		statement.setString(1, value);
		ResultSet rs=statement.executeQuery();
		List<Part> list=new ArrayList<Part>();
		
		while(rs.next())
		{
			Part tempJob=getPart(rs);
			list.add(tempJob);
		}
		closeDBObjects(rs, statement, connection);
		
		return list;
		
	}

	@Override
	public List<Part> findParts() throws SQLException 
	{
		Connection connection=getConnection();
		Statement statement=connection.createStatement();
		ResultSet rs=statement.executeQuery("select * from "+tablename+";");
		List<Part> list=new ArrayList<Part>();
		
		while(rs.next())
		{
			Part tempJob=getPart(rs);
			list.add(tempJob);
		}
		
		closeDBObjects(rs, statement, connection);
		return list;
	}
	
	
	//Don't call next or close the ResultSet
	private Part getPart(ResultSet rs) throws SQLException
	{
			Part tempJob=new Part(
					rs.getString("name"),
					rs.getString("part_id"),
					rs.getString("company"),
					rs.getString("version"),
					rs.getString("revision"),
					rs.getString("colour"),
					rs.getBoolean("external"),
					rs.getString("qss_no")
					);
			tempJob.setId(rs.getInt("ID"));
		return tempJob;	
		
	}
}
