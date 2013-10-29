package org.amc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amc.servlet.action.SaveJobTemplateAction;
import org.amc.servlet.action.SearchJobTemplateAction;
import org.amc.servlet.model.JobTemplate;
import org.amc.servlet.model.JobTemplateForm;
import org.amc.servlet.validator.JobTemplate_Validator;

/**
 * Servlet implementation class APLSystemServlet
 */
@WebServlet(
		description = "Dispatching Servlet for the Problem Database", 
		urlPatterns = { 
				"/APLSystemServlet", 
				"/JobTemplate_display",
				"/JobTemplate_search",
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
		
		
		//Handle JobTemplate Page
		if(referal.endsWith("JobTemplate_save"))
		{
			saveJobTemplate(request, response);
		}
		else if(referal.endsWith("JobTemplate_display"))
		{
			displayJobTemplate(request, response);
		}
		else if(referal.endsWith("JobTemplate_search"))
		{
			searchJobTemplate(request, response);
		}
		else if(referal.endsWith("APLSystemServlet"))
		{
//			PrintWriter writer=response.getWriter();
//			response.setContentType("text/html");
//			
//			writer.println("<!doctype html>"
//					+ "<HTML>"
//					+ "<BODY>"
//					+ "<a href='JobTemplate_display'>JobTemplate</a>"
//					+ "</BODY>"
//					+ "</HTML>");
//			writer.flush();
			RequestDispatcher rd=request.getRequestDispatcher("/JSP/Main.jsp");
			rd.forward(request, response);
		}
		
	}
	
	/**
	 * Saves the JobTemplate to the Database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveJobTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//check if page is in create or edit mode
		String mode=request.getParameter("mode");
		System.out.printf("mode:[%s]", mode);//debuh
		//create form
		JobTemplateForm jForm=new JobTemplateForm();
		
		//If an ID parameter is passed add it to the form 
		String id=request.getParameter("id");
		if(id!=null)
		{
			jForm.setId(id);
		}
		jForm.setName(request.getParameter("name"));
		jForm.setColour(request.getParameter("colour"));
		//checkedbox
		String external=request.getParameter("external");
		if(external==null)
		{
			jForm.setExternal(false);
		}
		else
		{
			jForm.setExternal(true);
		}
		
		jForm.setPart_id(request.getParameter("part_id"));
		jForm.setQss_no(request.getParameter("qss_no"));
		jForm.setRevision(request.getParameter("revision"));
		jForm.setVersion(request.getParameter("version"));
		jForm.setCompany(request.getParameter("company"));
		
		//Validate Form
		JobTemplate_Validator validator=new JobTemplate_Validator();
		List<String> errors=validator.validate(jForm);
		
		//If form validates with no errorts
		if(errors.isEmpty())
		{
		
			//create model
			JobTemplate job=new JobTemplate();
			job.setCompany(jForm.getCompany());
			job.setName(jForm.getName());
			job.setColour(jForm.getColour());
			job.setExternal(jForm.getExternal());
			job.setQss_no(jForm.getQss_no());
			job.setRevision(jForm.getRevision());
			job.setVersion(jForm.getVersion());
			job.setPart_id(jForm.getPart_id());
			
			String dispatcherURL="";
			
			SaveJobTemplateAction action=new SaveJobTemplateAction();
			try
			{
				// New JobTemplate to Database
				if(mode==null||mode.equals("Enter"))
				{
					action.save(job);
					request.setAttribute("form",jForm);
					request.setAttribute("result", job.toString()+" saved");
					dispatcherURL="/JSP/JobTemplate.jsp";
				}
				else if(mode.equals("Edit"))
				{
					//Current JobTemplate is updated in the Database
					job.setId(Integer.parseInt(jForm.getId()));
					action.edit(job);
					//dispatcherURL="JobTemplate_search";
					response.sendRedirect("JobTemplate_search"); // Goto the Search Window
					return; // Exit function 
				}

				//request.removeAttribute("form");
				request.removeAttribute("errors");
				RequestDispatcher rd=request.getRequestDispatcher(dispatcherURL);
				
				rd.forward(request, response);
			}
			catch(SQLException se)
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
				request.setAttribute("exception",se);
				rd.forward(request, response);
			}
			
			
		}
		else
		{
			//if the form doesn't validate without errors then
			request.setAttribute("errors", errors);
			request.setAttribute("form", jForm);			
			
			RequestDispatcher rd=request.getRequestDispatcher("/JSP/JobTemplate.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * display a blank JobTemplate page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayJobTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd=request.getRequestDispatcher("/JSP/JobTemplate.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Create SearchPage
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchJobTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//check to if in search or edit mode TODO add delete mode
		String mode=request.getParameter("mode");
		//Passed to use as a search term
		String searchWord=request.getParameter("search");
		//Passed if an entry is to be edited
		String idValue=request.getParameter("edit");
		
		//Debug
		System.out.printf("mode:[%s] searchWord:[%s] ID:[%s]%n", mode,searchWord,idValue);
		
		//create an action
		SearchJobTemplateAction sjt=new SearchJobTemplateAction();
		String dispatchURL=null;
		try
		{
			//if the page is to do a search
			if(mode==null || mode.equals("search"))
			{
				List<JobTemplate> list=null;
				//To check to search for all entries or entries where name=searchWord
				if(searchWord==null||searchWord.equals(""))// search for all entries
				{
					list=sjt.search();
				}
				else //search for entry where name=searchWord 
				{
					
					list=sjt.search("name",searchWord);
				}
				request.setAttribute("jobtemplates", list); //Add the result list to the request object to be used by the JSP page
				
				//debug
				System.out.printf("%d results returned %n",list.size());
			
				dispatchURL="/JSP/PartsSearchPage.jsp";
				
			}
			else if(mode!=null && mode.equals("edit") && idValue!=null)// Edit mode
			{
				//open the JobTemplate JSPage in edit mode
				JobTemplate job=sjt.getJobTemplate(idValue);
				dispatchURL="/JSP/JobTemplate.jsp";
				request.setAttribute("form", job);
				request.setAttribute("mode","edit");
				
			}
			else if(mode!=null && mode.equals("add"))
			{
				//open the JobTemplate JSPage in add mode
				dispatchURL="/JSP/JobTemplate.jsp";
			}
			RequestDispatcher rd=request.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);
		}
		catch(SQLException se)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
			request.setAttribute("exception",se);
			rd.forward(request, response);
		}
	}
	
	
}
