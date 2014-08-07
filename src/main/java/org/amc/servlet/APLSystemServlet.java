package org.amc.servlet;

import java.io.IOException;

import org.amc.DAOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.*;
import org.amc.servlet.validator.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * Servlet implementation class APLSystemServlet 
 * @author Adrian Mclaughlin
 * @version 1
 */
@WebServlet(
		description = "Dispatching Servlet for the Problem Database", 
		urlPatterns = { 
				"/app/APLSystemServlet", 
				"/app/Part_display",
				"/app/Part_search",
				"/app/Part_save", 
//				"/Problem_save", 
//				"/Problem_display", 
//				"/ProblemDescription_save", 
//				"/ProblemDescription_display", 
//				"/SearchProblemDatabase",
				"/app/logout"
		},loadOnStartup=1)

public class APLSystemServlet extends HttpServlet 
{
	private static final long serialVersionUID = 334034039L;

	private PartActionFactory partActionFactory;
	
	private static Logger logger=Logger.getLogger(APLSystemServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
		logger.debug(referal);
		
		//Handle Part Page
		if(referal.endsWith("Part_save"))
		{
			
			savePart(request, response);
		}
		else if(referal.endsWith("Part_display"))
		{
			displayPart(request, response);
		}
		else if(referal.endsWith("Part_search"))
		{
			searchForPart(request, response);
		}// To log out the current user
		else if(referal.endsWith("logout"))
		{
			logout(request,response);
		}
		else if(referal.endsWith("APLSystemServlet"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/JSP/Main.jsp");
			rd.forward(request, response);
		}
		
	}
	
	/**
	 * Saves the Part to the Database
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void savePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//check if page is in create or edit mode
		String mode=request.getParameter("mode");
		logger.debug(String.format("mode:[%s]", mode));//debug
		//create form
		PartForm jForm=new PartForm();
		
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
		Part_Validator validator=new Part_Validator();
		List<String> errors=validator.validate(jForm);
		
		//Check if user is a role to allow changes to the database
		if(!(request.isUserInRole("qc")||(request.isUserInRole("manager"))))
		{
			errors.add("User has no permissions to alter Part definitions!");
		}
				
		//If form validates with no errors
		if(errors.isEmpty())
		{
		
			//create model
			Part part=new Part();
			part.setCompany(jForm.getCompany());
			part.setName(jForm.getName());
			part.setColour(jForm.getColour());
			part.setExternal(jForm.isExternal());
			part.setQss_no(jForm.getQss_no());
			part.setRevision(jForm.getRevision());
			part.setVersion(jForm.getVersion());
			part.setPart_id(jForm.getPart_id());
			
			String dispatcherURL="";
			SavePartAction action=partActionFactory.getSaveJobTemplateAction();
			try
			{
				// New Part to Database
				if(mode==null||"Enter".equals(mode))
				{
					action.save(part);
					request.setAttribute("form",jForm);
					request.setAttribute("result", part.toString()+" saved");
					dispatcherURL="/WEB-INF/JSP/Part.jsp";
				}
				else if("Edit".equals(mode))
				{
					//Current Part is updated in the Database
					part.setId(Integer.parseInt(jForm.getId()));
					action.edit(part);
					dispatcherURL="Part_search";
					response.sendRedirect(request.getContextPath()+"/app/Part_search"); // Goto the Search Window
					return; // Exit function 
				}
				else
				{
					throw new ServletException("Form received can't be processed");
				}

				//request.removeAttribute("form");
				request.removeAttribute("errors");
				RequestDispatcher rd=request.getRequestDispatcher(dispatcherURL);
				
				rd.forward(request, response);
			}
			catch(NumberFormatException se)
			{
				throw new ServletException(se);
			}
			catch(DAOException se)
			{
				
				throw (ServletException)new ServletException("Database not available:"+se.getMessage()).initCause(se);
			}
			
			
		}
		else
		{
			//if the form doesn't validate without errors then
			request.setAttribute("errors", errors);
			request.setAttribute("form", jForm);			
			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/JSP/Part.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * display a blank Part page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/JSP/Part.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Create SearchPage
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchForPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//check to if in search or edit mode TODO add delete mode
		String mode=request.getParameter("mode");
		//Passed if an entry is to be edited
		String idValue=request.getParameter("edit");
		
		//Debug
		logger.debug(String.format("mode:[%s] ID:[%s]%n", mode,idValue));
		
		
		
		//create an action
		SearchPartAction sjt=this.partActionFactory.getSearchJobTemplateAction();
		String dispatchURL=null;
		try
		{
			//if the page is to do a search
			if(mode==null || "search".equals(mode))
			{
				List<Part> list=null;
				
				PartSearchForm partSearchForm=new PartSearchForm();
				partSearchForm.setCompany(request.getParameter("company"));
				partSearchForm.setPart(request.getParameter("partName"));
				partSearchForm.setQSSNumber(request.getParameter("qssNumber"));
				partSearchForm.setStartDate(request.getParameter("startDate"));
				partSearchForm.setEndDate(request.getParameter("endDate"));
				
				PartSearchFormValidator validator=new PartSearchFormValidator();
				
				validator.validate(partSearchForm);
				
				if(validator.hasErrors())
				{
					request.setAttribute(ControllerConstants.MESSAGE, validator.getErrors());
				}
				else
				{
					PartSearch partSearch=null;
					try
					{
						partSearch=PartSearchFormValidator.PartSearchBinder.getPartSearch(partSearchForm);
						list=sjt.search(partSearch);
					}
					catch(ParseException pe)
					{
						request.setAttribute(ControllerConstants.MESSAGE, "Search Parameters couldn't be parsed");
						list=new ArrayList<Part>();
					}
					
				}
				
				request.setAttribute("parts", list); //Add the result list to the request object to be used by the JSP page
				
				//debug
				logger.debug(String.format("%d results returned %n",list.size()));
			
				dispatchURL="/WEB-INF/JSP/PartsSearchPage.jsp";
				
			}
			else if(mode!=null)
			{
				if("add".equals(mode)||idValue==null) //idValue will equal null if the checked box isn't selected
				{
					//open the Part JSPage in add mode
					dispatchURL="/WEB-INF/JSP/Part.jsp";
				}
				else if("edit".equals(mode)&&idValue!=null)
				{
					//open the Part JSPage in edit mode
					Part part=sjt.getPart(idValue);
					dispatchURL="/WEB-INF/JSP/Part.jsp";
					request.setAttribute("form", part);
					request.setAttribute("mode","edit");
				}
			}
			RequestDispatcher rd=request.getRequestDispatcher(dispatchURL);
			rd.forward(request, response);
		}
		catch(DAOException se)
		{
			
			se.printStackTrace();
			throw (ServletException)new ServletException("Database not available:"+se.getMessage()).initCause(se);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		HttpSession session=request.getSession(false);
		if(session !=null)
		{
			session.invalidate();
		}
		request.logout();
		
		response.sendRedirect(getServletContext().getContextPath()+"/");
	}
	
	
	public void setPartActionFactory(PartActionFactory partActionFactory)
	{
		this.partActionFactory=partActionFactory;
	}
	

	@Override
	public void init() throws ServletException
	{
		super.init();
		ApplicationContext context2=(ApplicationContext)getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		setPartActionFactory((PartActionFactory)context2.getBean("partActionFactory"));

	}
}
