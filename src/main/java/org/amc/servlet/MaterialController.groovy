package org.amc.servlet

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller

import javax.servlet.RequestDispatcher;;

@Controller
class MaterialController {
    private static final String SESSION_MATERIALSEARCH = "MATERIALSEARCH";
    
     // Views
     static final String MAIN_VIEW = "Main";
     static final String ERROR_MATERIAL_EDIT = 'Can\'t edit material';
     static final String SESSION_MATERIALSEARCH = 'MATERIALSEARCH';
     static final String MATERIAL_SEARCH_PAGE = "MaterialSearchPage";
     static final String MATERIAL_ADD_EDIT_VIEW = "Material";
     static final String MODE_EDIT = 'edit';
     static final String ERROR_DAO = 'Database not available: ${de.message}';
}
