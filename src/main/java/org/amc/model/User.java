package org.amc.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@Entity
@Table(name="users")
public class User implements Serializable,WorkEntity
{
	private static final long serialVersionUID = 261123044422857580L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="fullname")
	private String fullName="";
	
	@NotNull(message="User name is required")
	@Column(name="user_name",updatable=false)
	private String userName="";
	
	@Pattern(regexp="\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b",message="Email not in correct format")
	@Column(name="email",nullable=false)
	private String emailAddress="";
	
	@Column(name="user_pass",nullable=true,updatable=true,length=70)
	private String password="";
	
	@Column(name="activate",updatable=true)
	private boolean active=true;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<UserRoles> roles;
	
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

	
	
	public String getPassword()
	{
		return password;
	}

	
	
	public boolean isActive()
	{
		return active;
	}

	
	
	public List<UserRoles> getRoles()
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

	
	
	public void setPassword(String password)
	{
		
		this.password=password;
	}
		
	
	public void setActive(boolean active)
	{
		this.active = active;
	}

	
	
	public void setRoles(List<UserRoles> roles)
	{
		this.roles = roles;
	}



	public int getId()
	{
		return id;
	}



	public void setId(int id)
	{
		this.id = id;
	}



	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof User)
		{
			User otherUser=(User)obj;
			return this.getUserName().equals(otherUser.getUserName()) && (this.id == otherUser.id);
			
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		int temp=1;
		temp=temp*2+this.userName.hashCode();
		return temp;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("UserName("+this.getId()+"):"+this.getUserName());
		sb.append("\n");
		sb.append("FullName:"+this.getFullName());
		sb.append("\n");
		sb.append("Email address:"+this.getEmailAddress());
		sb.append("\n");
		sb.append("active:"+this.isActive());
		sb.append("\n");
		List<UserRoles> roles=this.getRoles();
		if(roles!=null && !roles.isEmpty())
		{
			sb.append("Roles:\n");
			for(UserRoles role:roles)
			{
				sb.append(role+"\n");
			}
		}
		return sb.toString();
	}
}
