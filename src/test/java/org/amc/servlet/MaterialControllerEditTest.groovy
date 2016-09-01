package org.amc.servlet

import static org.mockito.Mockito.*;
import static org.amc.servlet.ControllerConstants.*;
import static org.junit.Assert.*;

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.ActionFactory
import org.amc.servlet.action.SearchAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.junit.Before;
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

class MaterialControllerEditTest {
    MaterialSearchController controller;
    def materialIdStr = '1';
    Material material;
    
    @Mock
    ActionFactory<Material, MaterialSearch> materialActionFactory;
    
    @Mock
    SearchAction<Material, MaterialSearch> searchMaterialAction;
    
    @Before
    void setup() {
        MockitoAnnotations.initMocks(this);
        
        controller = new MaterialSearchController();
        controller.init();
        controller.materialActionFactory = materialActionFactory;
        
        material = new Material();
        
        when(materialActionFactory.getSearchAction()).thenReturn(searchMaterialAction);
    }
    
    @Test
    void editMaterialTest() {
        when(searchMaterialAction.get(anyString())).thenReturn(material);
        ModelAndView mav = controller.editMaterial(materialIdStr);
        assert mav.viewName == MaterialSearchController.ITEM_VIEW;
        ModelAndViewAssert.assertModelAttributeValue(mav, FORM, material);
        ModelAndViewAssert.assertModelAttributeValue(mav, MODE, MODE_EDIT);
    }
    
    @Test
    void editIDNullTest() {
        String idValue = null;
        ModelAndView mav = controller.editMaterial(idValue);
        ModelAndViewAssert.assertViewName(mav, MaterialSearchController.SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void editIDEmptyStringTest() {
        String idValue = '';
        ModelAndView mav = controller.editMaterial(idValue);
        ModelAndViewAssert.assertViewName(mav, MaterialSearchController.SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void editSearchTestDAOException() {
        when(searchMaterialAction.get(anyString())).thenThrow(new DAOException("Test Exception"));
        String idValue = '2';
        try {
            ModelAndView mav = controller.editMaterial(idValue);
            ModelAndViewAssert.assertViewName(mav, MaterialSearchController.SEARCH_PAGE);
            ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
        } catch(DAOException de) {
            fail("No exception should be thrown to top level");
        }
    }
}
