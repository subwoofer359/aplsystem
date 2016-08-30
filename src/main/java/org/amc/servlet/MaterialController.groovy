package org.amc.servlet

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller

import javax.servlet.RequestDispatcher;;

@Controller
class MaterialController {
    private static final String SESSION_MATERIALSEARCH = "MATERIALSEARCH";
    
     // Views
     static final String MATERIAL_ADD_EDIT_VIEW = "Material";
     static final String MAIN_VIEW = "Main";
     static final String MATERIAL_SEARCH_PAGE = "MaterialSearchPage";
     static final String ERRORS = 'errors';
     static final String MODE = 'mode';
     static final String MODE_EDIT = 'edit';
     static final String ERROR_DAO = 'Database not available: ${de.message}';
     static final String ERROR_MATERIAL_EDIT = 'Can\'t edit material';
     static final String SESSION_MATERIALSEARCH = 'MATERIALSEARCH';
}
