package org.amc.servlet.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.amc.EntityManagerThreadLocal;

/**
 * Servlet Filter implementation class EntityManagerFilter
 */
public class EntityManagerFilter implements Filter {

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManagerThreadLocal.setEntityManagerFactory(factory);

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		EntityManagerThreadLocal.closeEntityManager();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

	@Resource(name="entityManagerFactory")
	public void setEntityManagerFactory(EntityManagerFactory factory)
	{
		this.factory=factory;
	}
	
}
