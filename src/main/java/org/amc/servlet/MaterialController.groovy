package org.amc.servlet

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller

import javax.servlet.RequestDispatcher;;

@Controller
class MaterialController {
    private static final String SESSION_MATERIALSEARCH = "MATERIALSEARCH";
    
     // Views
     static final String MATERIAL_ADD_EDIT_VIEW = "/WEB-INF/JSP/Material.jsp";
     static final String MATERIAL_SEARCH_VIEW = "/app/Material_search";
     static final String MAIN_VIEW = "/WEB-INF/JSP/Main.jsp";
     static final String MATERIAL_SEARCH_PAGE = "/WEB-INF/JSP/MaterialSearchPage.jsp";
     static final String ERRORS = 'errors';
     static final String MODE = 'mode';
     static final String MODE_EDIT = 'edit';
     static final String ERROR_DAO = 'Database not available: ${de.message}';
}
