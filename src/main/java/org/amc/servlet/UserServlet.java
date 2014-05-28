package org.amc.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.amc.dao.UserDAO;
import org.amc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			System.out.println("DAO="+userDAO);
			List<User> list=userDAO.findUsers();
			model.getModel().put("users", list);
		//}
		
		return model;
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
}
