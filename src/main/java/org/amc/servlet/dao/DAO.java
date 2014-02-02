package org.amc.servlet.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO 
{
	public Connection getConnection() throws SQLException;
	public void setConnection(Connection connection);
}
