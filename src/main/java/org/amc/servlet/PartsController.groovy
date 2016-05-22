package org.amc.servlet

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring Controller for handling Part operations and returning a view
 * @author Adrian Mclaughlin
 * @version 2
 */
class PartsController {

    @RequestMapping(method=RequestMethod.GET, value="APLSystemServlet")
    String getAPLSystemServlet() {
        return "Main";
    }
    
    void logout() {
        
    }
}
