package org.amc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRoles implements Serializable,WorkEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="role_name")
	private String roleName;
	
	
	public UserRoles()
	{
		//empty constructor
	}

	public int getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setUser(User user)
	{
		this.user = user;
		setUserName(user.getUserName());
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getUserName()
	{
		return userName;
	}

	private void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String toString()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("Role name:"+this.getRoleName());
		return sb.toString();
	}
}