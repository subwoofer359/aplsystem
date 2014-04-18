package org.amc.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserServlet
{
	@RequestMapping("/User")
	public String getUserPage()
	{
		return "UserInfo";
	}
}
