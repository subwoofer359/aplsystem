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
import org.amc.servlet.action.SaveProcessSheetAction;
import org.amc.servlet.action.SearchJobTemplateAction;
import org.amc.servlet.action.SearchProcessSheetAction;
import org.amc.servlet.model.JobTemplate;
import org.amc.servlet.model.JobTemplateForm;
import org.amc.servlet.model.MouldingProcess;
import org.amc.servlet.model.MouldingProcessForm;
import org.amc.servlet.validator.JobTemplate_Validator;
import org.amc.servlet.validator.ProcessForm_Validator;

/**
 * Servlet implementation class APLProcessServlet
 */
@WebServlet(description = "To serve up Process Sheet Data", 
			urlPatterns = 
			{
				"/APLProcessServlet",
				"/ProcessSheet_display",
				"/ProcessSheet_search",
				"/ProcessSheet_save", 
			})


public class APLProcessServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APLProcessServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String referal=request.getRequestURI();
		System.out.println(referal);
		
		
		//Handle JobTemplate Page
		if(referal.endsWith("ProcessSheet_save"))
		{
			saveProcessSheet(request, response);
		}
		else if(referal.endsWith("ProcessSheet_display"))
		{
			//displayJobTemplate(request, response);
		}
		else if(referal.endsWith("ProcessSheet_search"))
		{
			searchProcessSheets(request, response);
		}
		else if(referal.endsWith("APLProcessServlet"))
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
	
	

	private void saveProcessSheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Context Path:"+request.getContextPath());
		//check if page is in create or edit mode
		String mode=request.getParameter("mode");
		System.out.printf("SaveProcessSheet:mode:[%s]%n", mode);//debug
		System.out.println("SaveProcessSheet:"+request.getParameter("dateOfIssue"));
		//create form
		
		//If an ID parameter is passed add it to the form
		MouldingProcessForm jForm=(MouldingProcessForm)request.getAttribute("processSheet");
		System.out.println("\nSubmitted Process:"+jForm);
//		String id=request.getParameter("id");
//		if(id!=null)
//		{
//			jForm.setId(id);
//		}

		
		//Validate Form
		ProcessForm_Validator validator=new ProcessForm_Validator();
		List<String> errors=validator.validate(jForm);
		
		//If form validates with no errors
		if(errors.isEmpty())
		{
		
			//create model
			MouldingProcess processSheet;
			
			String dispatcherURL="";
			
			SaveProcessSheetAction action=new SaveProcessSheetAction();
			try
			{
				processSheet=MouldingProcessForm.getMouldingProcess(jForm);
				// New JobTemplate to Database
				if(mode==null||mode.equals("Enter"))
				{
					System.out.println("SaveProcessSheet:Entering entry into database");
					action.save(processSheet);
					dispatcherURL="ProcessSheet_search";
					response.sendRedirect(request.getContextPath()+"/ProcessSheet_search"); // Goto the Search Window
					return; // Exit function 
				}
				else if(mode.equals("Edit"))
				{
					System.out.println("SaveProcessSheet:Editing entry into database");
					//Current JobTemplate is updated in the Database
					//processSheet.setId(Integer.parseInt(jForm.getId()));
					processSheet.setId(Integer.parseInt(jForm.getId()));
					action.edit(processSheet);
					dispatcherURL="ProcessSheet_search";
					response.sendRedirect(request.getContextPath()+"/ProcessSheet_search"); // Goto the Search Window
					return; // Exit function 
				}

				//request.removeAttribute("form");
				request.removeAttribute("errors");
				RequestDispatcher rd=request.getRequestDispatcher(dispatcherURL);
				
				rd.forward(request, response);
			}
			catch(NumberFormatException se)
			{
				RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
				request.setAttribute("exception",se);
				rd.forward(request, response);
			}
			catch(SQLException se)
			{
				
				RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
				request.setAttribute("exception",se);
				rd.forward(request, response);
			}
			catch(Exception e)
			{
				RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
				request.setAttribute("exception",e);
				rd.forward(request, response);
			}
			
			
		}
		else
		{
			//if the form doesn't validate without errors then
			request.setAttribute("errors", errors);
			request.setAttribute("form", jForm);			
			
			RequestDispatcher rd=request.getRequestDispatcher("/JSP/ProcessPage.jsp");
			rd.forward(request, response);
		}
	}
	
	private void searchProcessSheets(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		//check to if in search or edit mode TODO add delete mode
				String mode=request.getParameter("mode");
				//Passed to use as a search term
				String searchWord=request.getParameter("search");
				//Passed if an entry is to be edited
				String idValue=request.getParameter("edit");
				
				//Debug
				System.out.printf("searchProcessSheets:mode:[%s] searchWord:[%s] ID:[%s]%n", mode,searchWord,idValue);
				
				
				
				//create an action
				SearchProcessSheetAction spt=new SearchProcessSheetAction();
				String dispatchURL=null;
				try
				{
					//if the page is to do a search
					if(mode==null || mode.equals("search"))
					{
						System.out.println("searchProcessSheets:Searching");
						List<MouldingProcess> list=null;
						//To check to search for all entries or entries where name=searchWord
						if(searchWord==null||searchWord.equals(""))// search for all entries
						{
							list=spt.search();
						}
						else //search for entry where name=searchWord 
						{
							
							list=spt.search("partId",searchWord);
						}
						request.setAttribute("processSheets", list); //Add the result list to the request object to be used by the JSP page
						
						//debug
						System.out.printf("%d results returned %n",list.size());
					
						dispatchURL="/JSP/ProcessSheetSearchPage.jsp";
						
					}
//					else if(mode!=null && mode.equals("edit") && idValue!=null)// Edit mode
//					{
//						//open the JobTemplate JSPage in edit mode
//						JobTemplate job=sjt.getJobTemplate(idValue);
//						dispatchURL="/JSP/JobTemplate.jsp";
//						request.setAttribute("form", job);
//						request.setAttribute("mode","edit");
//						
//					}
//					else if(mode!=null && mode.equals("add"))
//					{
//						//open the JobTemplate JSPage in add mode
//						dispatchURL="/JSP/JobTemplate.jsp";
//					}
					else if(mode!=null)
					{
						if(mode.equals("add")||idValue==null) //idValue will equal null if the checked box isn't selected
						{
							System.out.println("searchProcessSheets:Opening ProcessPage.jsp");
							//open the JobTemplate JSPage in add mode
							MouldingProcess process =new MouldingProcess();
							request.setAttribute("form", process);
							dispatchURL="/JSP/ProcessPage.jsp";
						}
						else if(mode.equals("edit")&&idValue!=null)
						{
							//open the JobTemplate JSPage in edit mode
							System.out.println("searchProcessSheets:Opening ProcessPage.jsp in edit mode");
							MouldingProcess process=spt.getMouldingProcess(idValue);
							dispatchURL="/JSP/ProcessPage.jsp";
							request.setAttribute("form", process);
							request.setAttribute("mode","edit");
						}
					}
					RequestDispatcher rd=request.getRequestDispatcher(dispatchURL);
					rd.forward(request, response);
				}
				catch(SQLException se)
				{
					RequestDispatcher rd=request.getRequestDispatcher("/JSP/ErrorPage.jsp");
					request.setAttribute("exception",se);
					rd.forward(request, response);
					se.printStackTrace();
				}
		
	}
}
