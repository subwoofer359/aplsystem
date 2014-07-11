package org.amc.servlet.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.User;
import org.springframework.context.ApplicationContext;

/**
 * Servlet Filter implementation class UserFilter
 * Adds a User object which represents the currently logged in user to the Session
 * If the User is deactivated, the user is automatically logged out 
 * @author Adrian Mclaughlin
 * @version 1
 */
public class UserFilter implements Filter
{
	private FilterConfig filterConfig;
	
	private final static Logger logger = Logger.getLogger(UserFilter.class);
	
	private final static String SPRING_WEBAPPCONTEXT="org.springframework.web.context.WebApplicationContext.ROOT";
	
	//The Spring bean's name referring a UserDAO object
	private final static String USERDAO="myUserDAO";
	
	//Field of the User entity (@see org.amc.model.User)
	private final static String USER_USERNAME="userName";
	
	//Session variable to hold the User Object
	public final static String SESSIONVAR_USER="USER";
	
	//Session variable to hold the Remote Address of the User
	private final static String SESSIONVAR_REMOTE_ADDRESS="REMOTE_ADDRESS";
	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		//Empty No Resource to clean up.
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		logger.debug("Filter:UserFilter called");
		// Save User
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		synchronized (httpRequest.getSession())
		{
			//Get the username from the Request object
			if (httpRequest.getUserPrincipal() != null && httpRequest.getSession().getAttribute(SESSIONVAR_USER) == null)
			{
				if (httpRequest.getSession().getAttribute(SESSIONVAR_REMOTE_ADDRESS) == null)
				{
					httpRequest.getSession().setAttribute(SESSIONVAR_REMOTE_ADDRESS, httpRequest.getRemoteAddr());
				}
				String userName = httpRequest.getUserPrincipal().getName();
				//Get the User object from the database
				User user=getUser(httpRequest.getSession(),userName);
				if(user.isActive())
				{
					addLoggedUserToSession(httpRequest.getSession(),user);
				}
				else
				{
					httpRequest.logout();
					if(httpRequest.getSession(false)!=null)
					{
						httpRequest.getSession(false).invalidate();
					}
					throw new ServletException("User "+user.getUserName()+" has been deactivated. Contact the System adminstrator");
				}
				
				
		

			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		this.filterConfig = fConfig;

	}

	
	/**
	 * 
	 * @param session
	 * @param userName
	 * @return User Object representing the current user 
	 * @throws ServletException if User is not found in the database
	 */
	
	@SuppressWarnings("unchecked")
	private final User getUser(HttpSession session,String userName) throws ServletException
	{
		// Spring Context containing the UserDAO object
		ApplicationContext context2;
		
		synchronized (filterConfig.getServletContext())
		{
			context2 = (ApplicationContext) filterConfig.getServletContext().getAttribute(SPRING_WEBAPPCONTEXT);
		}
		
		DAO<User> userDAO = context2.getBean(USERDAO, DAO.class);
		
		try
		{
			List<User> listOfUser = userDAO.findEntities(USER_USERNAME, userName);
			if (listOfUser != null && listOfUser.size() == 1)
			{
				return listOfUser.get(0);
			}
			else
			{
				logger.debug("Error when adding User " + userName + " added to Session:");
				throw new ServletException("No User has been found in the User Database with that User name");
			}
		} 
		catch (DAOException de)
		{
			logger.error(de.getMessage());
			throw (ServletException)new ServletException().initCause(de);
		}


	}
	
	/**
	 * Adds the User Object to the HttpSession
	 * @param session
	 * @param user
	 */
	private final void addLoggedUserToSession(HttpSession session,User user)
	{
		synchronized (session)
		{
			session.setAttribute(SESSIONVAR_USER, user);
			logger.debug("User added to Session:" + user);
		}
	}

}
