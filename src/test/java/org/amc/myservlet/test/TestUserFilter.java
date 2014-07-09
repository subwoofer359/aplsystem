package org.amc.myservlet.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.User;
import org.amc.servlet.filter.UserFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;


public final class TestUserFilter
{
	private final static String SPRING_WEBAPPCONTEXT="org.springframework.web.context.WebApplicationContext.ROOT";
	private final static String FULLNAME="Adrian McLaughlin";
	private final static String USERNAME="adrian";
	private final static String EMAILADDRESS="adrian@adrianmclaughlin.ie";
	private final static String PASSWORD="doweoe";
	private final static String USER_USERNAME="userName";
	//UserFilter constants todo replace this code
	private final static String USERDAO="myUserDAO";
	private final static String REMOTEADDRESS="192.168.1.1";
	private final static String SESSIONVAR_USER="USER";
	private final static String SESSIONVAR_REMOTE_ADDRESS="REMOTE_ADDRESS";
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * After doFilter call, Session variables USER and REMOTE_ADDRESS should be set 
	 * with no exceptions thrown
	 * @throws ServletException
	 * @throws IOException
	 * @throws DAOException
	 */
	@Test
	public void testUserSavedToSession() throws ServletException,IOException,DAOException
	{
		MockHttpServletRequest request=new MockHttpServletRequest();
		MockHttpServletResponse response=new MockHttpServletResponse();
		MockServletContext context=new MockServletContext();
		MockFilterConfig fConfig=new MockFilterConfig(context);
		MockFilterChain chain = new MockFilterChain();
		MockHttpSession session=new MockHttpSession();

		//Create User to be retrieved from mock database query
		User user=new User();
		user.setFullName(FULLNAME);
		user.setUserName(USERNAME);
		user.setEmailAddress(EMAILADDRESS);
		user.setPassword(PASSWORD);
		
		DAO<User> userDAO=mock(DAO.class);
		List<User> userList=new ArrayList<User>();
		userList.add(user);
		when(userDAO.findEntities(USER_USERNAME,USERNAME)).thenReturn(userList);
		
		//Spring Context
		ApplicationContext applicationContext =mock(ApplicationContext.class);
		when(applicationContext.getBean(USERDAO,DAO.class)).thenReturn(userDAO);
		
		//Store Spring Context in ServletContext
		context.setAttribute(SPRING_WEBAPPCONTEXT,applicationContext);
		
		Principal userPrincipal =mock(Principal.class);
		when(userPrincipal.getName()).thenReturn(USERNAME);
		request.setUserPrincipal(userPrincipal);
		request.setRemoteAddr(REMOTEADDRESS);
		request.setSession(session);
		
		
		UserFilter filter=new UserFilter();
		filter.init(fConfig);
		filter.doFilter(request, response, chain);
		
		assertEquals(session.getAttribute(SESSIONVAR_USER),user);
		assertEquals(session.getAttribute(SESSIONVAR_REMOTE_ADDRESS), REMOTEADDRESS);
		
	}
	
	/**
	 * After doFilter call, HttpServletRequest.logout() should be called 
	 * with no exceptions thrown
	 * @throws ServletException
	 * @throws IOException
	 * @throws DAOException
	 */
	@Test
	public void testUserDeactivated() throws ServletException,IOException,DAOException
	{
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpServletResponse response=mock(HttpServletResponse.class);
		MockServletContext context=new MockServletContext();
		MockFilterConfig fConfig=new MockFilterConfig(context);
		MockFilterChain chain = new MockFilterChain();
		MockHttpSession session=new MockHttpSession();

		//Create User to be retrieved from mock database query
		User user=new User();
		user.setFullName(FULLNAME);
		user.setUserName(USERNAME);
		user.setEmailAddress(EMAILADDRESS);
		user.setPassword(PASSWORD);
		user.setActive(false);
		
		DAO<User> userDAO=mock(DAO.class);
		List<User> userList=new ArrayList<User>();
		userList.add(user);
		when(userDAO.findEntities(USER_USERNAME,USERNAME)).thenReturn(userList);
		
		
		//Spring Context
		ApplicationContext applicationContext =mock(ApplicationContext.class);
		when(applicationContext.getBean(USERDAO,DAO.class)).thenReturn(userDAO);
		
		//Store Spring Context in ServletContext
		context.setAttribute(SPRING_WEBAPPCONTEXT,applicationContext);
		
		Principal userPrincipal =mock(Principal.class);
		when(userPrincipal.getName()).thenReturn(USERNAME);
		when(request.getUserPrincipal()).thenReturn(userPrincipal);
		when(request.getRemoteAddr()).thenReturn(REMOTEADDRESS);
		when(request.getSession()).thenReturn(session);
		when(request.getSession(anyBoolean())).thenReturn(session);
		
		UserFilter filter=new UserFilter();
		filter.init(fConfig);
		try
		{
			filter.doFilter(request, response, chain);
		}
		catch(ServletException se)
		{
			//Verify the user logged out
			verify(request).logout();
			//Session is invalidated
			assertTrue(session.isInvalid());
			return;
		}
		//Exception not thrown which is not expected
		assertFalse(true);
		
		
		
	}
}
