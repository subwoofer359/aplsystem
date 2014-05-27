package org.amc.servlet;

import java.util.List;

import org.amc.dao.UserDAO;
import org.amc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
		model.setViewName("Users");
		System.out.println("DAO="+userDAO);
		List<User> list=userDAO.findUsers();
		model.getModel().put("users", list);
		
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
