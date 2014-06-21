package org.amc.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger; 
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.User;



import org.springframework.context.ApplicationContext;

/**
 * Servlet Filter implementation class UserFilter
 * @author Adrian Mclaughlin
 * @version 1
 */
@WebFilter(description = "Save the logged in user", urlPatterns = { "/APLSystemServlet" })
public class UserFilter implements Filter {

	private FilterConfig filterConfig;
	private static Logger logger=Logger.getLogger(UserFilter.class);
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		//Save User
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpSession session=httpRequest.getSession();
		synchronized (session)
		{
			if(httpRequest.getUserPrincipal()!=null && session.getAttribute("USER")==null)
			{
			//session.setAttribute("USER", new MyServletUser(request.getUserPrincipal()));
				if(session.getAttribute("REMOTE_ADDRESS")==null)
				{
					session.setAttribute("REMOTE_ADDRESS", httpRequest.getRemoteAddr());
				}
				String userName=httpRequest.getUserPrincipal().getName();
				//session.setAttribute("USER", httpRequest.getUserPrincipal());
				//Save Remote address when USER is saved
				
				addLoggedUserToSession(userName, session);
			}
		}
		

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		this.filterConfig=fConfig;
		
	}
	
	private void addLoggedUserToSession(String userName,HttpSession session) throws ServletException
	{
		//Spring Context
		ServletContext sContext=filterConfig.getServletContext();
		ApplicationContext context2;
		synchronized(sContext)
		{
			context2=(ApplicationContext)sContext.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		}
		DAO<User> userDAO=(DAO<User>)context2.getBean("myUserDAO",DAO.class);
		List<User> listOfUser=null;
		try{
			listOfUser=userDAO.findEntities("userName", userName);
			if(listOfUser!=null && listOfUser.size()==1)
			{
				synchronized(session)
				{
					session.setAttribute("USER", listOfUser.get(0));
					logger.debug("User added to Session:"+listOfUser.get(0));
				}
			}
			else
			{
				logger.debug("Error when adding User "+userName+" added to Session:");
				throw new ServletException("No User has been found in the User Database with that User name");
			}
		}
		catch(DAOException de)
		{
			logger.error(de.getMessage());
		}
	}

}
