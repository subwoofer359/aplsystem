package org.amc.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NoCacheFilter
 */
@WebFilter("/*")
public class NoCacheFilter implements Filter {

    private FilterConfig filterConfig;
    public NoCacheFilter() 
    {
      
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() 
	{
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		httpResponse.setHeader("Cache-Control", "no-cache, no-store");
		httpResponse.setHeader("Pragma", "no-cache");
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