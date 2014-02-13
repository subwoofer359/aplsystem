package org.amc.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(description = "Save the logged in user", urlPatterns = { "/*" })
public class UserFilter implements Filter {

	private FilterConfig filterConfig;
    
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
		if(httpRequest.getUserPrincipal()!=null && session.getAttribute("USER")==null)
		{
			//session.setAttribute("USER", new MyServletUser(request.getUserPrincipal()));
			session.setAttribute("USER", httpRequest.getUserPrincipal());
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

}
