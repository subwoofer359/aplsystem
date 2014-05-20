package org.amc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Remote;


public interface DAORemote 
{
	public Connection getConnection() throws SQLException;
}
