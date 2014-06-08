package org.amc.servlet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.amc.dao.DAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.amc.servlet.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@Controller
public class APLUserController
{
	private final static String MANAGER="manager";//Super User of the system
	private final String DIGEST="SHA-256";
	private final String PASSWORD_DEFAULT="PaSsWoRd24432322535342";
	private  UserRolesDAO userRolesDAO;
	private  DAO<User> userDAO;
	private static Logger logger=Logger.getLogger(APLUserController.class);
	
	
//	@InitBinder("user")
//	protected void initBinder(WebDataBinder binder)
//	{
//		binder.addValidators(new UserValidator());
//	}
	
	
	@RequestMapping("/User")
	public String getUserPage()
	{
		return "UserInfo";
	}
	
	/**
	 * 
	 * @param model
	 * @return ModelAndView containing list of users from the database
	 */
	@RequestMapping("/Users")
	public ModelAndView getUsersPage(ModelAndView model,HttpServletRequest request)
	{
		if(request.isUserInRole(MANAGER))
		{
			List<User> list=userDAO.findEntities();
			model.getModel().put("users", list);
			model.setViewName("UsersSearchPage");
		}
		else
		{
			//Not Manager so return to Main.jsp
			model.setViewName("Main");
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
						 @Valid @ModelAttribute("user") User user,
						 BindingResult result, 
						 @RequestParam("mode") String mode,
						 @RequestParam(value="active",required=false) String active,
						 @RequestParam(value="role",required=false) String[] roles,
						 HttpServletRequest request
						 )
	{
		if(!request.isUserInRole(MANAGER))
		{
			//Return to the Main page
			return "Main";
		}
		//Set the user's active state
		if(active==null)
			user.setActive(false);
		else
			user.setActive(true);
		
		//Check for new password
		//Get old password
		User tempUser =userDAO.getEntity(String.valueOf(user.getId()));
		//If the user's password doesn't equals the DEFAULT password then hash and save the password
		if(!user.getPassword().equals(PASSWORD_DEFAULT))
		{
			user.setPassword(hash(user.getPassword()));
		}
		// Save the old password
		else
		{
			user.setPassword(tempUser.getPassword());
		}
		
		//Get the roles currently assigned to the user
		List<UserRoles> currentListOfRoles=userRolesDAO.getEntities(user);
		//Create a new list of roles
		List<UserRoles> newListOfRoles=new ArrayList<UserRoles>();
		//Check if roles equals null or no roles selected
		
		if(roles==null)
		{
			roles=new String[0];
		}
		//Check to see if the User's role already exists
		for(int i=0;i<roles.length;i++)
		{
			boolean exists=false;
			for(UserRoles tempRole:currentListOfRoles)
			{
				if(tempRole.getRoleName().equals(roles[i]))
				{
					//If the role already exists copy to new list
					newListOfRoles.add(tempRole);
					exists=true;
				}
			}
			if(!exists)
			{
				//If the role doesn't exist then create a new role for the user and add to new list
				UserRoles newRole=new UserRoles();
				newRole.setRoleName(roles[i]);
				newRole.setUser(user);
				newListOfRoles.add(newRole);
			}
		}
		
		
		//Check to see what roles are no longer required and delete them
		for(int i=0;i<currentListOfRoles.size();i++)
		{
			boolean exists=false;
			for(int t=0;t<roles.length;t++)
			{
				if(currentListOfRoles.get(i).getRoleName().equals(roles[t]))
				{
					exists=true;
				}
			}
			if(exists==false)
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
			model.addAttribute("errors", result.getAllErrors());
			model.addAttribute("user", user);
			return "UserAddOrEdit";
		}
				
		//If in Edit mode update user otherwise add user
		if(mode.equals("edit"))
		{
			userDAO.updateEntity(user);
		}
		else
		{
			userDAO.addEntity(user);
		}
		//Return to the search page
		return "forward:/user/Users";
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
			@RequestParam("mode") String mode, 
			@RequestParam(value="edit",required=false)  Integer id,
			ModelAndView model,
			HttpServletRequest request
			
			)
	{
		//If not in role manager return the Main.jsp
		if(!request.isUserInRole(MANAGER))
		{
			model.setViewName("Main");
			return model;
			
		}
		logger.debug("UserServlet:In mode "+mode);
		logger.debug("UserServlet:UserDAO is "+userDAO);
		User u=null;
		model.getModel().put("mode", mode);
		if(mode.equals("edit"))
		{
			u=userDAO.getEntity(String.valueOf(id));
			logger.debug("Users_edit: User retrieved"+u);
			
		}
		else
			if(mode.equals("add"))
			{
				u=new User();
				
			}
		else if(mode.equals("delete"))
		{
			u=userDAO.getEntity(String.valueOf(id)); 
			logger.debug("User about to be deleted "+u);
			userDAO.deleteEntity(u);
			return getUsersPage(new ModelAndView(), request);
			
		}
		else
		{
			logger.debug("UserServlet:editUsers: passed straight through if/else block shouldn't happen");
		}
		
		model.setViewName("UserAddOrEdit");
		//Blank password
		u.setPassword(PASSWORD_DEFAULT);
		model.getModel().put("user", u);
		
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
		if(password==null)
		{
			return "";
		}
		byte[] hash=null;
		try
		{
			MessageDigest digest=MessageDigest.getInstance(DIGEST);
			hash=digest.digest(new String(password).getBytes());
		}
		catch(NoSuchAlgorithmException nae)
		{
			//to implement
		}
		if(hash!=null)
		{
			password=hexEncode(hash);
		}
		return password;
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
