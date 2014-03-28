package org.amc.dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface DAOBeanRemote 
{
	public Connection getConnection() throws SQLException;
}
