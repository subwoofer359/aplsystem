package org.amc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Remote;


public interface DAOBeanRemote 
{
	public Connection getConnection() throws SQLException;
}
