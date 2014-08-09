package org.amc.servlet;

import java.io.IOException;

import org.amc.Constants.Roles;
import org.amc.DAOException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.SaveMaterialAction;
import org.amc.servlet.action.SearchMaterialAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.model.Material;
import org.amc.servlet.model.MaterialForm;
import org.amc.servlet.model.MaterialSearchForm;
import org.amc.servlet.validator.MaterialForm_Validator;
import org.amc.servlet.validator.MaterialSearchValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import static org.amc.servlet.ControllerConstants.MODE;
import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.MODE_ADD;
import static org.amc.servlet.ControllerConstants.MODE_ENTER;
import static org.amc.servlet.ControllerConstants.MATERIAL;
import static org.amc.servlet.ControllerConstants.MATERIALS;
import static org.amc.servlet.ControllerConstants.PROCESS;
import static org.amc.servlet.ControllerConstants.SEARCH;
/**
 * Servlet implementation class APLMaterialServlet
 * @author Adrian Mclaughlin
 * @version 1
 */
@WebServlet(
		description = "To Process Material Requests", 
		urlPatterns = { 
				"/app/Material_display",
				"/app/Material_save",
				"/app/Material_search",
				"/app/MaterialServlet"
				}
		,loadOnStartup=3)
public class APLMaterialServlet extends HttpServlet 
{
	private static final long serialVersionUID = 5984908504L;
	
	private static Logger logger=Logger.getLogger(APLMaterialServlet.class);
	
	private MaterialActionFactory materialActionFactory;
	
	private static final String FORM="form";
	
	private static final String MATERIAL_ADD_EDIT_VIEW="/WEB-INF/JSP/Material.jsp";
	private static final String MATERIAL_SEARCH_VIEW="/app/Material_search";
	private static final String MAIN_VIEW="/WEB-INF/JSP/Main.jsp";
	private static final String MATERIAL_SEARCH_PAGE="/WEB-INF/JSP/MaterialSearchPage.jsp";

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
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String referal=request.getRequestURI();
		logger.debug(String.format(referal));
		
		
		//Handle JobTemplate Page
		if(referal.endsWith("Material_save"))
		{
			saveMaterial(request, response);
		}
		else if(referal.endsWith("Material_display"))
		{
			displayMaterial(request, response);
		}
		else if(referal.endsWith("Material_search"))
		{
			searchMaterial(request, response);
		}
		else if(referal.endsWith("MaterialServlet"))
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
			RequestDispatcher rd=request.getRequestDispatcher(MAIN_VIEW);
			rd.forward(request, response);
		}
	}
	
	private void saveMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		logger.debug(String.format("Context Path:"+request.getContextPath()));
		//check if page is in create or edit mode
		String mode=request.getParameter(MODE);
		logger.debug(String.format("SaveMaterial:mode:[%s]%n", mode));//debug
		//create form
		
		//If an ID parameter is passed add it to the form
		MaterialForm jForm=(MaterialForm)request.getAttribute(MATERIAL);
		logger.debug(String.format("\nSubmitted Process:"+jForm));

		
		//Validate Form
		MaterialForm_Validator validator=new MaterialForm_Validator();
		List<String> errors=validator.validate(jForm);
		
		//Check if user is a role to allow changes to the database
		if(!(request.isUserInRole(Roles.QC.toString())||(request.isUserInRole(Roles.MANAGER.toString()))))
		{
				errors.add("User has no permissions to alter Material definitions!");
		}
		
		//If form validates with no errors
		if(errors.isEmpty())
		{
		
			//create model
			Material material;
			
			//String dispatcherURL="";
			
			SaveMaterialAction action=materialActionFactory.getSaveMaterialAction();
			try
			{
				material=MaterialForm.getMaterial(jForm);
				// New JobTemplate to Database
				//if(mode==null||mode.equals("Enter"))
				if(mode==null || MODE_ENTER.equals(mode))
				{
					logger.debug(String.format("SaveMaterial:Entering entry into database"));
					action.save(material);
					response.sendRedirect(request.getContextPath()+MATERIAL_SEARCH_VIEW); // Goto the Search Window
					return; // Exit function 
				}
				//else if(mode.equals("edit"))
				else if(MODE_EDIT.equals(mode))
				{
					logger.debug(String.format("SaveMaterial:Editing entry into database"));
					//Current JobTemplate is updated in the Database
					//processSheet.setId(Integer.parseInt(jForm.getId()));
					material.setId(Integer.parseInt(jForm.getId()));
					action.edit(material);
					response.sendRedirect(request.getContextPath()+MATERIAL_SEARCH_VIEW); // Goto the Search Window
					return; // Exit function 
				}
				else
				{
					throw new ServletException("Form received can't be processed");
				}

				//request.removeAttribute("form");
				//request.removeAttribute("errors");
				//RequestDispatcher rd=request.getRequestDispatcher(dispatcherURL);
				
				//rd.forward(request, response);
			}
			catch(NumberFormatException se)
			{
				throw new ServletException(se);
			}
			catch(DAOException se)
			{	
				throw new ServletException(se);
			}
			catch(Exception e)
			{
				throw new ServletException(e);
			}
			
			
		}
		else
		{
			//if the form doesn't validate without errors then
			//if(mode.equals("edit")) //Remember page is in edit mode
			if(MODE_EDIT.equals(mode))
			{
				request.setAttribute(MODE, mode);
			}
			request.setAttribute(ERRORS, errors);
			request.setAttribute(FORM, jForm);			
			
			RequestDispatcher rd=request.getRequestDispatcher(MATERIAL_ADD_EDIT_VIEW);
			rd.forward(request, response);
		}
	}
	private void displayMaterial(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		//Selected Process Page
		String idValue=request.getParameter(MODE_EDIT);
		//if(idValue!=null && (!idValue.equals("")))
		if(idValue==null || "".equals(idValue))
		{
			// No Process selected return to ProcessSearchPage
			response.sendRedirect(request.getContextPath()+MATERIAL_SEARCH_VIEW);	
		}
		else
		{
			try
			{
				SearchMaterialAction spt=materialActionFactory.getSearchMaterialAction();
				Material process=spt.getMaterial(idValue);
				request.setAttribute(PROCESS,process);
				RequestDispatcher rd=request.getRequestDispatcher(MATERIAL_ADD_EDIT_VIEW);
				rd.forward(request, response);
			} catch (DAOException e)
			{
				getServletContext().log(e.getMessage());
				e.printStackTrace();
				throw (ServletException)new ServletException("Database not available").initCause(e);			
			}
			
		}
		
	}
	
	private void searchMaterial(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		//check to if in search or edit mode TODO add delete mode
				String mode=request.getParameter(MODE);
				//Passed to use as a search term
				String searchWord=request.getParameter(SEARCH);
				//Passed if an entry is to be edited
				String idValue=request.getParameter(MODE_EDIT);
				
				//Debug
				logger.debug(String.format("searchMaterial:mode:[%s] searchWord:[%s] ID:[%s]%n", mode,searchWord,idValue));
				
				
				
				//create an action
				SearchMaterialAction spt=materialActionFactory.getSearchMaterialAction();
				String dispatchURL=null;
				try
				{
					if(mode==null || SEARCH.equals(mode))
					{
						Map<Integer,Material> list=null;
						
						MaterialSearchForm materialForm=new MaterialSearchForm();
						materialForm.setCompany(request.getParameter("company"));
						materialForm.setName(request.getParameter("name"));
						materialForm.setType(request.getParameter("type"));
						
						MaterialSearchValidator validator =new MaterialSearchValidator();
						
						validator.validate(materialForm);
						if(validator.hasErrors())
						{
							request.setAttribute(ControllerConstants.MESSAGE, validator.getErrors());
						}
						else
						{
							try
							{
								MaterialSearch materialSearch=MaterialSearchValidator.MaterialSearchBinder.getMaterialSearch(materialForm);
								list=spt.search(materialSearch);
							}
							catch(ParseException pe)
							{
								request.setAttribute(ControllerConstants.MESSAGE, "Search Parameters couldn't be parsed");
								list=new HashMap<Integer,Material>();
							}
						}
						request.setAttribute(MATERIALS, list); //Add the result list to the request object to be used by the JSP page
	
						logger.debug(list.keySet());
						logger.debug(String.format("%d results returned %n",list.size()));
					
						dispatchURL=MATERIAL_SEARCH_PAGE;
						
					}
					else if(mode!=null)
					{
						if(MODE_ADD.equals(mode)||idValue==null) //idValue will equal null if the checked box isn't selected
						{
							logger.debug(String.format("searchMaterial:Opening Material.jsp"));
							//open the JobTemplate JSPage in add mode
							Material material =new Material();
							request.setAttribute(FORM, material);
							dispatchURL=MATERIAL_ADD_EDIT_VIEW;
						}
						else if(MODE_EDIT.equals(mode) && idValue!=null)
						{
							//open the JobTemplate JSPage in edit mode
							logger.debug(String.format("searchMaterial:Opening Material.jsp in edit mode"));
							Material material=spt.getMaterial(idValue);
							dispatchURL=MATERIAL_ADD_EDIT_VIEW;
							request.setAttribute(FORM, material);
							request.setAttribute(MODE,MODE_EDIT);
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
	@Autowired
	public void setMaterialActionFactory(MaterialActionFactory materialActionFactory)
	{
		this.materialActionFactory = materialActionFactory;
	}
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		WebApplicationContext context2=(WebApplicationContext)getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		setMaterialActionFactory((MaterialActionFactory)context2.getBean("materialActionFactory"));
	}

	
}
