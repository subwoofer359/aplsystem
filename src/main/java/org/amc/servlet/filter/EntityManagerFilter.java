package org.amc.servlet.filter;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.amc.EntityManagerThreadLocal;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * Servlet Filter implementation class EntityManagerFilter
 */
public class EntityManagerFilter implements Filter 
{

	private static Logger logger=Logger.getLogger(EntityManagerFilter.class);
	
	private FilterConfig fConfig;
	private EntityManagerFactory factory;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() 
	{
		;;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		logger.debug("EntityManagerFilter:doFilter: The EntityManagerFactory="+factory);
		EntityManagerThreadLocal.setEntityManagerFactory(factory);

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		EntityManagerThreadLocal.closeEntityManager();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException 
	{
		this.fConfig=fConfig;
		ApplicationContext context2=(ApplicationContext)fConfig.getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		setEntityManagerFactory((EntityManagerFactory)context2.getBean("applicationEntityManagerFactory"));
	}

	public void setEntityManagerFactory(EntityManagerFactory factory)
	{
		this.factory=factory;
		logger.debug("EntityManagerFilter:setEntityManagerFactory: The EntityManagerFactory="+factory);
	}
	
}
