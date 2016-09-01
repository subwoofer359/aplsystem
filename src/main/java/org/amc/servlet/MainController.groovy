package org.amc.servlet

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;;;;

@Controller
class MainController {
    
    static final String VIEW_MAIN_PAGE = 'Main';
    
    @RequestMapping(method = RequestMethod.GET, value = "/APLSystemServlet")
    String getAPLSystemServlet() {
        return VIEW_MAIN_PAGE;
    }
    
    @RequestMapping(value = "/logout")
    String logout(HttpSession session, HttpServletRequest httpServletRequest) {
        session?.invalidate();
        httpServletRequest.logout();
        
        return "redirect:/";
    }
}
