package org.amc.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class User implements Serializable
{
	private static final long serialVersionUID = 261123044422857580L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
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
		;;
	}

	
	
	public String getFullName()
	{
		return fullName;
	}

	
	
	public String getUserName()
	{
		return userName;
	}

	
	
	public String getEmailAddress()
	{
		return emailAddress;
	}

	
	
	public char[] getPassword()
	{
		return password;
	}

	
	
	public boolean isActive()
	{
		return active;
	}

	
	
	public List<String> getRoles()
	{
		return roles;
	}

	
	
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	
	
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	
	
	public void setPassword(char[] password)
	{
		this.password = password;
	}

	
	
	public void setActive(boolean active)
	{
		this.active = active;
	}

	
	
	public void setRoles(List<String> roles)
	{
		this.roles = roles;
	}
	
	
}
