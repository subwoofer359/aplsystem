package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.amc.dao.UserDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUser
{

	private EntityManager em;
	private EntityManagerFactory factory;
	
	@Before
	public void setUp() throws Exception
	{
		factory=Persistence.createEntityManagerFactory("myDataSource");
		em=factory.createEntityManager();
		
		//Clear the table
			Query q=em.createNativeQuery("DELETE FROM users");
			Query q1=em.createNativeQuery("DELETE FROM user_roles");
			em.getTransaction().begin();
			q.executeUpdate();
			q1.executeUpdate();
			em.getTransaction().commit();
	}

	@After
	public void tearDown() throws Exception
	{
		em.close();
		factory.close();
	}

	/**
	 * 
	 * @return Sample User
	 */
	public User getTestUser()
	{
		User u=new User();
		u.setFullName("Adrian McLaughlin");
		u.setUserName("subwoofer359");
		u.setEmailAddress("subwoofer359@gmail.com");
		u.setPassword("orororo03".toCharArray());
		u.setActive(true);
		return u;
	}
	
	/**
	 * Tests methods addUser(User u) and getUser(String id)
	 */
	@Test
	public void testAddUser()
	{
		User u=getTestUser();
//		UserRoles roles=new UserRoles();
//		roles.setRoleName("QC");
//		roles.setUser(u);
//		
//		UserRoles roles2=new UserRoles();
//		roles2.setRoleName("Manager");
//		roles2.setUser(u);
//		
//		List<UserRoles> listOfRoles=new ArrayList<UserRoles>();
//		listOfRoles.add(roles);
//		listOfRoles.add(roles2);
//		u.setRoles(listOfRoles);
		UserDAO ud=new UserDAO(factory);
		ud.addUser(u);
		
		User tu=ud.getUser(String.valueOf(u.getId()));
		
		assertTrue(tu.equals(u));
	}
	
	@Test
	public void testFindUsers()
	{
		UserDAO ud=new UserDAO(factory);
		User[] users={getTestUser(),getTestUser(),getTestUser(),getTestUser()};
		
		for(User u:users)
		{
			ud.addUser(u);
			
		}
		List<User> list=ud.findUsers();
		assertEquals(users.length, list.size());
		
		
	}
	@Test
	public void testFindUsersByValue()
	{
		String userName="Bunny";
		
		UserDAO ud=new UserDAO(factory);
		
		User u1=getTestUser();
		User u2=getTestUser();
		u2.setUserName(userName);
		User u3=getTestUser();
		User u4=getTestUser();
		ud.addUser(u1);
		ud.addUser(u2);
		ud.addUser(u3);
		ud.addUser(u4);
		
		List<User> list=ud.findUsers("userName", userName);
		assertEquals(list.size(), 1);
		User actualUser=list.get(0);
		assertEquals(actualUser.getUserName(),userName);
	
	}
	
	@Test
	public void testUpdateUser()
	{
		String emailAddress="Test Email Address";
		UserDAO ud=new UserDAO(factory);
		User u=getTestUser();
		
		ud.addUser(u);
		
		u.setEmailAddress(emailAddress);
		
		ud.updateUser(u);
		
		User tu=ud.getUser(String.valueOf(u.getId()));
		
		assertEquals(tu.getEmailAddress(), emailAddress);
	}
	
	@Test
	public void testRoles()
	{
		String userName="Bunny";
		String[] roles={"QC","MANAGER"};
		
		UserDAO ud=new UserDAO(factory);
		
		User u1=getTestUser();
		User u2=getTestUser();
		u2.setUserName(userName);
		
		UserRoles r1=new UserRoles();
		r1.setRoleName(roles[0]);
		r1.setUser(u1);
		
		UserRoles r2=new UserRoles();
		r2.setRoleName(roles[1]);
		r2.setUser(u1);
		
		UserRoles r3=new UserRoles();
		r3.setRoleName(roles[0]);
		r3.setUser(u2);
		
		List<UserRoles> u1_roles=new ArrayList<UserRoles>();
		List<UserRoles> u2_roles=new ArrayList<UserRoles>();
		
		u1_roles.add(r1);
		u1_roles.add(r2);
		u2_roles.add(r3);
		
		u1.setRoles(u1_roles);
		u2.setRoles(u2_roles);
		
		ud.addUser(u1);
		ud.addUser(u2);
		
		User t1=ud.getUser(String.valueOf(u1.getId()));
		assertTrue(t1!=null);
		
		ud.deleteUser(u1);
		t1=ud.getUser(String.valueOf(u1.getId()));
		
		assertTrue(t1==null);
		
		
		
		
	}

}