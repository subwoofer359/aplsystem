package org.amc.servlet;

import static org.amc.Constants.PASSWORD_DEFAULT;
import static org.amc.Constants.PASSWORD_DIGEST;
import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.MAIN_VIEW;
import static org.amc.servlet.ControllerConstants.MESSAGE;
import static org.amc.servlet.ControllerConstants.MODE;
import static org.amc.servlet.ControllerConstants.MODE_ADD;
import static org.amc.servlet.ControllerConstants.MODE_DELETE;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.USER;
import static org.amc.servlet.ControllerConstants.USERS;
import static org.amc.servlet.ControllerConstants.USER_EDIT_VIEW;
import static org.amc.servlet.ControllerConstants.USER_INFO_VIEW;
import static org.amc.servlet.ControllerConstants.USER_SEARCH;
import static org.amc.servlet.ControllerConstants.USER_SEARCH_VIEW;

import org.amc.Constants.Roles;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.amc.servlet.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@Controller
public class APLUserController
{
	private  UserRolesDAO userRolesDAO;
	private  DAO<User> userDAO;
	private static Logger logger=Logger.getLogger(APLUserController.class);
	
	
	
//	@InitBinder(USER)
//	protected void initBinder(WebDataBinder binder)
//	{
//		binder.addValidators(new UserValidator());
//	}
	
	
	@RequestMapping("/User")
	public String getUserPage()
	{
		return USER_INFO_VIEW;
	}
	
	/**
	 * 
	 * @param model
	 * @return ModelAndView containing list of users from the database
	 */
	@RequestMapping("/Users")
	public ModelAndView getUsersPage(ModelAndView model,HttpServletRequest request)
	{
		if(request.isUserInRole(Roles.MANAGER.toString()))
		{
			List<User> list=null;
			try
			{
				list=userDAO.findEntities();
				
			}
			catch(DAOException de)
			{
				model.getModel().put(MESSAGE,de.getMessage());
				list=new ArrayList<User>();
			}
			model.getModel().put(USERS, list);
			model.setViewName(USER_SEARCH_VIEW);
		}
		else
		{
			//Not Manager so return to Main.jsp
			model.setViewName(MAIN_VIEW);
		}
		
		
		
		return model;
	}
	
	/**
	 * Saves the user to the database
	 * @param model 
	 * @param user User created by Spring from the request parameters
	 * @param mode The mode the page was in: Edit or add
	 * @param roles The roles select on the JSP
	 * @return
	 */
	@RequestMapping("/User_Save")
	public String saveUser(Model model,
						 @Valid @ModelAttribute(USER) User user,
						 BindingResult result, 
						 @RequestParam(MODE) String mode,
						 @RequestParam(value="active",required=false) String active,
						 @RequestParam(value="role",required=false) String[] roles,
						 HttpServletRequest request
						 )
	{
		if(!request.isUserInRole(org.amc.Constants.Roles.MANAGER.toString()))
		{
			//Return to the Main page
			return MAIN_VIEW;
		}
		//Set the user's active state
		if(active==null)
			user.setActive(false);
		else
			user.setActive(true);
		
		//Check for new password
		//Get old password
		User tempUser=null;
		try
		{
			try
			{
				tempUser=userDAO.getEntity(String.valueOf(user.getId()));
			}
			catch(DAOException de)
			{
				//Expecting a NoResultException as the User doesn't exist in the database
				if(!de.getCause().getClass().equals(NoResultException.class))
				{
					throw new DAOException(de);
				}
			}
			// Save the old password

			if(user.getPassword().equals(PASSWORD_DEFAULT))
			{
				if(tempUser==null)
				{
					user.setPassword("");
				}
				else
				{
					user.setPassword(tempUser.getPassword());
				}
			}
			//If the user's password doesn't equals the DEFAULT password then hash and save the password
			else
			{
				user.setPassword(hash(user.getPassword()));
				
			}
		
			//Get the roles currently assigned to the user
			List<UserRoles> currentListOfRoles=userRolesDAO.getEntities(user);
			//Create a new list of roles
			List<UserRoles> newListOfRoles=new ArrayList<UserRoles>();
			//Check if roles equals null or no roles selected
		
			String[] tempRoles;
			
			if(roles==null)
			{
				tempRoles=new String[0];
			}
			else
			{
				tempRoles=roles;
			}
			//Check to see if the User's role already exists
			for(int i=0;i<tempRoles.length;i++)
			{
				boolean exists=false;
				for(UserRoles currRole:currentListOfRoles)
				{
					if(currRole.getRoleName().equals(tempRoles[i]))
					{
						//If the role already exists copy to new list
						newListOfRoles.add(currRole);
						exists=true;
					}
				}
				if(!exists)
				{
					//If the role doesn't exist then create a new role for the user and add to new list
					UserRoles newRole=new UserRoles();
					newRole.setRoleName(tempRoles[i]);
					newRole.setUser(user);
					newListOfRoles.add(newRole);
				}
			}
		
		
			//Check to see what roles are no longer required and delete them
			for(int i=0;i<currentListOfRoles.size();i++)
			{
				boolean exists=false;
				for(int t=0;t<tempRoles.length;t++)
				{
					if(currentListOfRoles.get(i).getRoleName().equals(tempRoles[t]))
					{
						exists=true;
					}
				}
				if(!exists)
				{
					userRolesDAO.deleteEntity(currentListOfRoles.get(i));
				}
			}
			
			logger.debug(newListOfRoles);
			//Set the user's roles
			user.setRoles(newListOfRoles);
			logger.debug(user);
			
		
			//Check validation if fails return to page to get correct information
			Validator v=new UserValidator();//@ToDo to be Injected
			v.validate(user, result);
			if(result.hasErrors())
			{
				logger.debug("Errors in Model User found:"+result);
				model.addAttribute(ERRORS, result.getAllErrors());
				model.addAttribute(USER, user);
				return USER_EDIT_VIEW;
			}
				
			//If in Edit mode update user otherwise add user
			if(MODE_EDIT.equals(mode))
			{
				logger.debug("Updating User in database");
				userDAO.updateEntity(user);
			}
			else
			{
				logger.debug("Adding User in database");
				userDAO.addEntity(user);
			}
		}
		catch(DAOException de)
		{
			logger.error(de.getMessage());
			request.setAttribute(MESSAGE, de.getMessage());
		}
		//Return to the search page
		return "forward:"+USER_SEARCH;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/isUserNameAvailable")
	public void isUserNameAvailable(@RequestParam final String username,HttpServletResponse response)throws Exception{
		Callable<String> action=new Callable<String>(){
			public String call() throws Exception{
				List<User> users=userDAO.findEntities("userName",username);
				return String.valueOf(users.isEmpty());
			}
		};	
		String result=action.call();
		response.setContentType("plain/text");
		response.getWriter().println(result);
	}
	
	/**
	 * 
	 * @param mode The mode the page is in: Edit or add
	 * @param id The database id of the User from page
	 * @param model 
	 * @return ModelAndView containing the user to edit and setting view to User.jsp
	 */
	@RequestMapping("/Users_edit")
	public ModelAndView editUsers(
			@RequestParam(MODE) String mode, 
			@RequestParam(value=MODE_EDIT,required=false)  Integer id,
			ModelAndView model,
			HttpServletRequest request
			
			)
	{
		//If not in role manager return the Main.jsp
		if(!request.isUserInRole(Roles.MANAGER.toString()))
		{
			model.setViewName(MAIN_VIEW);
			return model;
			
		}
		logger.debug("UserServlet:In mode "+mode);
		logger.debug("UserServlet:UserDAO is "+userDAO);
		User u=null;
		model.getModel().put(MODE, mode);
		try
		{
			if(MODE_EDIT.equals(mode))
			{
				u=userDAO.getEntity(String.valueOf(id));
				logger.debug("Users_edit: User retrieved"+u);
			
			}
			else
				if(MODE_ADD.equals(mode))
				{
					u=new User();				
				}
				else if(MODE_DELETE.equals(mode))
				{
					u=userDAO.getEntity(String.valueOf(id)); 
					logger.debug("User about to be deleted "+u);
					userDAO.deleteEntity(u);
					return getUsersPage(new ModelAndView(), request);			
				}
				else
				{
					logger.error("UserServlet:editUsers: passed straight through if/else block shouldn't happen");
					throw new IllegalArgumentException("mode wasn't set to a correct value");
					
				}
			model.setViewName(USER_EDIT_VIEW);
			u.setPassword(PASSWORD_DEFAULT);
			model.getModel().put(USER, u);
		}
		catch(DAOException de)
		{
			model.getModelMap().put(MESSAGE, de.getMessage());
			//Redirect to search page
			model.setViewName(USER_SEARCH_VIEW);
		}
		
		//Blank password
		
		
		return model; 
	}
	
	@ExceptionHandler(PersistenceException.class)
	public ModelAndView handleJPAException(PersistenceException exception)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("ErrorPage");
		mv.getModel().put("exception", exception);
		return mv;
	}
	
	
	
	/*
	 * Spring injected
	 */
	@Resource(name="myUserDAO")
	public void setUserDAO(DAO<User> ud)
	{
		userDAO=ud;
		logger.debug("UserDAO added to UserServlet:"+userDAO);
	}
	/*
	 * Spring injected
	 */
	@Autowired
	public void setUserRolesDAO(UserRolesDAO ud)
	{
		userRolesDAO=ud;
	}
	
	/**
	 * To hash the password
	 * @param password
	 * @return hashed password
	 */
	private String hash(String password)
	{
		String hashedPassword="";
		if(password==null)
		{
			return hashedPassword;
		}
		byte[] hash=null;
		try
		{
			MessageDigest digest=MessageDigest.getInstance(PASSWORD_DIGEST);
			hash=digest.digest(password.getBytes(Charset.defaultCharset()));
		}
		catch(NoSuchAlgorithmException nae)
		{
			logger.error("HashPassword:The hashing algorithm not supported");
			//todo implement
		}
		if(hash!=null)
		{
			hashedPassword=hexEncode(hash);
		}
		return hashedPassword;
	}
	
	/**
	 * 
	 * @param aInput
	 * @return Password in hexidecimal format
	 */
	private String hexEncode( byte[] aInput)
	{
	    StringBuffer result = new StringBuffer();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < aInput.length; ++idx) 
	    {
	    	byte b = aInput[idx];
	    	result.append( digits[ (b&0xf0) >> 4 ] );
	    	result.append( digits[ b&0x0f] );
	    }
	    return result.toString();
	  } 
}
