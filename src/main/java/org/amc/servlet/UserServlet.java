package org.amc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.amc.dao.UserDAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;
import org.apache.log4j.Logger;
/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@Controller
public class UserServlet
{
	private static UserRolesDAO userRolesDAO;
	private static UserDAO userDAO;
	private static Logger logger=Logger.getLogger(UserServlet.class);
	
	@RequestMapping("/User")
	public String getUserPage()
	{
		return "UserInfo";
	}
	
	@RequestMapping("/Users")
	public ModelAndView getUsersPage(ModelAndView model)
	{
		
		//if(request.isUserInRole("Manager"))
		//{
			model.setViewName("Users");
			List<User> list=userDAO.findUsers();
			model.getModel().put("users", list);
		//}
		
		return model;
	}
	
	@RequestMapping("/User_Save")
	public ModelAndView saveUser(ModelAndView model,@ModelAttribute("user") User user, 
						 @RequestParam("mode") String mode,
						 @RequestParam("role") String[] roles
						 )
	{
		List<UserRoles> currentListOfRoles=userRolesDAO.getUserRoles(user);
		List<UserRoles> newListOfRoles=new ArrayList<UserRoles>();
		
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
				//If the role doesn't exist then create a new role for the user
				UserRoles newRole=new UserRoles();
				newRole.setRoleName(roles[i]);
				newRole.setUser(user);
				newListOfRoles.add(newRole);
			}
		}
		
		logger.info(newListOfRoles);
		user.setRoles(newListOfRoles);
		logger.info(user);
		userDAO.updateUser(user);
		return getUsersPage(model);
	}
	
	@RequestMapping("/Users_edit")
	public ModelAndView editUsers(@RequestParam("mode") String mode,@RequestParam("edit") String id,ModelAndView model)
	{
		logger.debug("UserServlet:In mode "+mode+" for ID "+id);
		User u=null;
		model.getModel().put("mode", mode);
		if(mode.equals("edit"))
		{
			u=userDAO.getUser(id);
			logger.debug("Users_edit: User retrieved"+u);
			
		}
		else
			if(mode.equals("add"))
			{
				u=new User();
				
			}
			else
			{
				logger.debug("UserServlet:editUsers: passed straight through if/else block shouldn't happen");
			}
		model.setViewName("User");
		model.getModel().put("user", u);
		
		return model; 
	}
	/*
	 * Spring injected
	 */
	public static void setUserDAO(UserDAO ud)
	{
		userDAO=ud;
		logger.debug("UserDAO added to UserServlet:"+userDAO);
	}
	/*
	 * Spring injected
	 */
	public static void setUserRolesDAO(UserRolesDAO ud)
	{
		userRolesDAO=ud;
		logger.debug("UserDAO added to UserServlet:"+userDAO);
	}
}
