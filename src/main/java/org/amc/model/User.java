package org.amc.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User implements UserRemote,UserLocal
{
	@Column(name="fullname")
	String fullName="";
	@Column(name="user_name",updatable=true)
	String userName="";
	@Column(name="email",nullable=false)
	String emailAddress="";
	@Column(name="user_pass",nullable=false,updatable=true)
	char[] password;
	@Column(name="active",updatable=true)
	boolean active=true;
	@Transient
	@Column(name="role_name",table="user_roles",updatable=true)
	List<String> roles;
	
	public User()
	{
		
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#getFullName()
	 */
	@Override
	public String getFullName()
	{
		return fullName;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#getUserName()
	 */
	@Override
	public String getUserName()
	{
		return userName;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#getEmailAddress()
	 */
	@Override
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#getPassword()
	 */
	@Override
	public char[] getPassword()
	{
		return password;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#isActive()
	 */
	@Override
	public boolean isActive()
	{
		return active;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#getRoles()
	 */
	@Override
	public List<String> getRoles()
	{
		return roles;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setFullName(java.lang.String)
	 */
	@Override
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setEmailAddress(java.lang.String)
	 */
	@Override
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setPassword(char[])
	 */
	@Override
	public void setPassword(char[] password)
	{
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setActive(boolean)
	 */
	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see org.amc.model.UserRemote#setRoles(java.util.List)
	 */
	@Override
	public void setRoles(List<String> roles)
	{
		this.roles = roles;
	}
	
	
}
