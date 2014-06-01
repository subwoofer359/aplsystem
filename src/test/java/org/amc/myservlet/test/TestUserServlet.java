package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.amc.dao.UserDAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.amc.servlet.UserServlet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.*;

public class TestUserServlet
{

	//Availible roles
	private static String[] ROLES ={"manager","guest","user","qc"};
	//Test Fixture
	private UserServlet userServlet;
	

	@Before
	public void setUp() throws Exception
	{
		userServlet=new UserServlet();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testGetUserPage()
	{
		String result=userServlet.getUserPage();
		assertEquals("UserInfo", result);
	}

	private User getTestUser(String username,String fullname)
	{
		User u1=new User();
		u1.setFullName(fullname);
		u1.setUserName(username);
		u1.setEmailAddress("adrian@adrianmclaughlin.ie");
		return u1;
	}
	
	/**
	 * Should return a ModelAndView object with a viewname="UsersSearchPage" and contain a List of Users
	 */
	@Test
	public void testGetUsersPage()
	{
		//Mock DAO object
		UserDAO dao=mock(UserDAO.class);
		
		//A User 
		User u1=getTestUser("adrian", "Adrian McLaughlin");
		
		//List of Users
		List<User> list=new ArrayList<User>();
		list.add(u1);
		//Mock object to return this list of objects
		when(dao.findUsers()).thenReturn(list);
		userServlet.setUserDAO(dao);
		
		//Empty model to pass to getUsersPage
		ModelAndView model=new ModelAndView();
		
		//MockHttpServletRequest required for the isUserInRole method
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		
		ModelAndView returnedMav =userServlet.getUsersPage(model, request);
		
		//Assert the method has returned the correct model attribute and view
		ModelAndViewAssert.assertModelAttributeValue(returnedMav, "users", list);
		ModelAndViewAssert.assertViewName(returnedMav, "UsersSearchPage");
		
	}

	/**
	 * Test to make sure that if a user isn't in role 'manager' they are redirected to the Main page
	 */
	@Test
	public void testSaveUserNotInRole()
	{
		//Request parameters
		String[] roles={ROLES[0],ROLES[1],ROLES[2]};
		String mode="add";//Save new user mode
		String active="true";
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
		
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		UserRolesDAO roleDao=mock(UserRolesDAO.class);
		when(roleDao.getUserRoles(u1)).thenReturn(getUserRoles(2, u1));
		//Inject DAOs
		userServlet.setUserDAO(dao);
		userServlet.setUserRolesDAO(roleDao);
			
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("guest");
		BindingResult result=mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);
				
		Model m=mock(Model.class);
				
		String returnedResult=userServlet.saveUser(m, u1, result, mode, active, roles, request);
				
		assertEquals("Main", returnedResult);
	}
	
	
	private List<UserRoles> getUserRoles(int number,User user)
	{
		
		List<UserRoles> roles=new ArrayList<UserRoles>();
		
		//Return the least of the requested size or size of ROLES array
		number=Math.min(number, ROLES.length);
		
		for(int i=0;i<number;i++)
		{
			UserRoles userRole=new UserRoles();
			userRole.setRoleName(ROLES[i]);
			userRole.setUser(user);
			roles.add(userRole);
		}
		return roles;
	}
	
	/**
	 * Preconditions: mode="add" 
	 * User and UserRoles objects exist in database
	 * BindResult returns with no errors
	 */
	@Test
	public void testSaveNewUser()
	{
		//Case one: User has currently no roles but 3 new roles are selected
		//Request parameters
		String[] roles={ROLES[0],ROLES[1],ROLES[2]};
		String mode="add";//Save new user mode
		String active="true";
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
		
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		UserRolesDAO roleDao=mock(UserRolesDAO.class);
		when(roleDao.getUserRoles(u1)).thenReturn(getUserRoles(2, u1));
		//Inject DAOs
		userServlet.setUserDAO(dao);
		userServlet.setUserRolesDAO(roleDao);
		
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		BindingResult result=mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);
		
		Model m=mock(Model.class);
		
		String returnedResult=userServlet.saveUser(m, u1, result, mode, active, roles, request);
		
		//Verify add user was called on UserDao object
		verify(dao).addUser(any(User.class));
		//Verify the correct view String was returned.
		assertEquals("forward:/user/Users",returnedResult);
		
		
		//Verify 3 roles were create for the user
		List<UserRoles> finalRoles=u1.getRoles();
		assertEquals(finalRoles.size(), roles.length);
		
		
		//Case 2: User has current 2 Roles but has no roles selected in page
		roles=new String[]{};
		dao=mock(UserDAO.class);
		userServlet.setUserDAO(dao);
		returnedResult=userServlet.saveUser(m, u1, result, mode, active, roles, request);
		//Verify add user was called on UserDao object
		verify(dao).addUser(any(User.class));
		//Verify the correct view String was returned.
		assertEquals("forward:/user/Users",returnedResult);
				
		//The user should now have no roles
		finalRoles=u1.getRoles();
		assertEquals(finalRoles.size(), roles.length);
		
		
	}
	
	/**
	 * Preconditions: mode="edit" 
	 * User and UserRoles objects exist in database
	 * BindResult returns with no errors
	 */
	@Test
	public void testSaveEditedUser()
	{
		//Request parameters
		String[] roles={ROLES[0],ROLES[1],ROLES[2]};
		String mode="edit";//Save new user mode
		String active="true";
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
			
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		UserRolesDAO roleDao=mock(UserRolesDAO.class);
		when(roleDao.getUserRoles(u1)).thenReturn(getUserRoles(2, u1));
		//Inject DAOs
		userServlet.setUserDAO(dao);
		userServlet.setUserRolesDAO(roleDao);
		
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		BindingResult result=mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(false);
		
		Model m=mock(Model.class);
				
		String returnedResult=userServlet.saveUser(m, u1, result, mode, active, roles, request);
		
		verify(dao).updateUser(any(User.class));
		//Verify the correct view String was returned.
		assertEquals("forward:/user/Users",returnedResult);
				
		//The user should now have no roles
		List<UserRoles> finalRoles=u1.getRoles();
		assertEquals(finalRoles.size(), roles.length);
	}
	
	/**
	 * Preconditions: mode="edit" 
	 * User and UserRoles objects exist in database
	 * BindResult returns with errors
	 */
	@Test
	public void testSaveEditedUserWithValidationErrors()
	{
		//Request parameters
		String[] roles={ROLES[0],ROLES[1],ROLES[2]};
		String mode="edit";//Save new user mode
		String active="true";
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
			
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		UserRolesDAO roleDao=mock(UserRolesDAO.class);
		when(roleDao.getUserRoles(u1)).thenReturn(getUserRoles(2, u1));
		//Inject DAOs
		userServlet.setUserDAO(dao);
		userServlet.setUserRolesDAO(roleDao);
		
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		BindingResult result=mock(BindingResult.class);
		//result has errors
		when(result.hasErrors()).thenReturn(true);
		
		Model model=mock(Model.class);
				
		String returnedResult=userServlet.saveUser(model, u1, result, mode, active, roles, request);
		verify(model).addAttribute(eq("errors"),any(List.class));
		verify(model).addAttribute(eq("user"), any(User.class));
		//Verify the correct view String was returned.
		assertEquals("UserAddOrEdit",returnedResult);

	}
	
	/**
	 * Test to see if delete user succeeds and the user is directed to the correct page
	 */
	@Test
	public void testDeleteUser()
	{
		String mode="delete";//delete user mode
		int id=1;
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
			
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		when(dao.getUser(anyString())).thenReturn(u1);
		//Inject DAOs
		userServlet.setUserDAO(dao);
		
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		
		ModelAndView model=mock(ModelAndView.class);
		
		ModelAndView mav=userServlet.editUsers(mode, id, model, request);
		
		verify(dao).deleteUser(any(User.class));
		//Verify the correct view String was returned.
		ModelAndViewAssert.assertViewName(mav,"UsersSearchPage");
		
		
	}

	/**
	 * Test that the user directed to the User edit page 
	 */
	@Test
	public void testEditUsers()
	{
		String mode="edit";//delete user mode
		int id=1;
		//Create User
		User u1=getTestUser("adrian", "Adrian McLaughlin");
			
		//Create DAO objects
		UserDAO dao=mock(UserDAO.class);
		when(dao.getUser(anyString())).thenReturn(u1);
		//Inject DAOs
		userServlet.setUserDAO(dao);
		
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addUserRole("manager");
		
		ModelAndView modelView=new ModelAndView();
		
		ModelAndView mav=userServlet.editUsers(mode, id, modelView, request);
		
		//verify UserDAO method getUser was called
		verify(dao).getUser(anyString());
		//Verify the correct view String was returned and Model attribute "user" was set.
		ModelAndViewAssert.assertViewName(mav,"UserAddOrEdit");
		ModelAndViewAssert.assertModelAttributeAvailable(mav,"user");
	}

}
