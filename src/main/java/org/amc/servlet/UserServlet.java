package org.amc.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserServlet
{
	@RequestMapping("/User")
	public String getUserPage()
	{
		return "UserInfo";
	}
	
	@RequestMapping("/Users")
	public ModelAndView getUsersPage(Model model)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Users");
		return mv;
	}
}
