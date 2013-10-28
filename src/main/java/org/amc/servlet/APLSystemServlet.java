package org.amc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class APLSystemServlet
 */
@WebServlet(
		description = "Dispatching Servlet for the Problem Database", 
		urlPatterns = { 
				"/APLSystemServlet", 
				"/JobTemplate_display", 
				"/JobTemplate_save", 
				"/Problem_save", 
				"/Problem_display", 
				"/ProblemDescription_save", 
				"/ProblemDescription_display", 
				"/SearchProblemDatabase"
		})

public class APLSystemServlet extends HttpServlet 
{
	private static final long serialVersionUID = 334034039L;

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		process(request, response);
	}
	
	/**
	 * Handles incoming requests
	 */
	
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String referal=request.getRequestURI();
		System.out.println(referal);
	}
}
