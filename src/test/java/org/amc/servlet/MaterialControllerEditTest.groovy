package org.amc.servlet

import static org.mockito.Mockito.*;
import static org.amc.servlet.ControllerConstants.*;
import static org.junit.Assert.*;

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.MaterialActionFactory
import org.amc.servlet.action.SearchMaterialAction;
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
    MaterialActionFactory materialActionFactory;
    
    @Mock
    SearchMaterialAction searchMaterialAction;
    
    @Before
    void setup() {
        MockitoAnnotations.initMocks(this);
        
        controller = new MaterialSearchController();
        controller.materialActionFactory = materialActionFactory;
        
        material = new Material();
        
        when(materialActionFactory.getSearchMaterialAction()).thenReturn(searchMaterialAction);
    }
    
    @Test
    void editMaterialTest() {
        when(searchMaterialAction.getMaterial(anyString())).thenReturn(material);
        ModelAndView mav = controller.editMaterial(materialIdStr);
        assert mav.viewName == MaterialController.MATERIAL_ADD_EDIT_VIEW;
        ModelAndViewAssert.assertModelAttributeValue(mav, FORM, material);
        ModelAndViewAssert.assertModelAttributeValue(mav, MODE, MODE_EDIT);
    }
    
    @Test
    void editIDNullTest() {
        String idValue = null;
        ModelAndView mav = controller.editMaterial(idValue);
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_ADD_EDIT_VIEW);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void editIDEmptyStringTest() {
        String idValue = '';
        ModelAndView mav = controller.editMaterial(idValue);
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_ADD_EDIT_VIEW);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
    }
    
    @Test
    void editSearchTestDAOException() {
        when(searchMaterialAction.getMaterial(anyString())).thenThrow(new DAOException("Test Exception"));
        String idValue = '2';
        try {
            ModelAndView mav = controller.editMaterial(idValue);
            ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_ADD_EDIT_VIEW);
            ModelAndViewAssert.assertModelAttributeAvailable(mav, ControllerConstants.MESSAGE);
        } catch(DAOException de) {
            fail("No exception should be thrown to top level");
        }
    }
}
