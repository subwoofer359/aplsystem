package org.amc.model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UserLocal
{
	public String getFullName();

	public String getUserName();

	public String getEmailAddress();

	public char[] getPassword();

	public boolean isActive();

	public List<String> getRoles();

	public void setFullName(String fullName);

	public void setUserName(String userName);

	public void setEmailAddress(String emailAddress);

	public void setPassword(char[] password);

	public void setActive(boolean active);

	public void setRoles(List<String> roles);
}
