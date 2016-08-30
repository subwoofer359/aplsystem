package org.amc.servlet

import static org.mockito.Mockito.*;
import static org.amc.servlet.ControllerConstants.*;

import org.amc.DAOException;
import org.amc.model.Material;
import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.SaveMaterialAction
import org.amc.servlet.model.MaterialForm;
import org.amc.servlet.validator.MaterialForm_Validator;
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor;
import org.mockito.Mock
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView

import javax.servlet.ServletException;;

class MaterialSaveControllerTest {  
    @Mock
    SaveMaterialAction saveMaterialAction;
    
    @Mock
    MaterialActionFactory materialActionFactory;
    
    @Mock
    BindingResult bindingResult;
    
    MaterialSaveController controller;
    
    ArgumentCaptor<String> urlString;
    
    Material material;
    
    def company = 'ALPS';
    def density = 0.1f;
    def id = 1;
    def linearExpansion = 1.2f;
    def material_drying = 80;
    def melting_temp_upper = 200;
    def melting_temp_lower = 120;
    def mould_shrinkage = 2.3f;
    def mould_temp_low = 20;
    def mould_temp_upper = 90;
    def water_absorption = 2;
    
    @Before
    void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new MaterialSaveController();
        controller.setMaterialActionFactory(materialActionFactory);
        
        material = new Material();
        material.company = company;
        material.density = density;
        material.id = id;
        material.linear_expansion = linearExpansion;
        material.material_drying = material_drying;
        material.melting_temp_lower = melting_temp_lower;
        material.melting_temp_upper = melting_temp_upper;
        material.mould_shrinkage = mould_shrinkage;
        material.mould_temp_low = mould_temp_low;
        material.mould_temp_upper = mould_temp_upper;
        material.water_absorption = water_absorption;
        
        when(materialActionFactory.getSaveMaterialAction()).thenReturn(saveMaterialAction);
    }
    
    @Test
    void testSaveMaterial() {
        when(bindingResult.hasErrors()).thenReturn(false);
        ModelAndView mav = controller.saveMaterial(material, bindingResult);
        assert MaterialController.MATERIAL_SEARCH_VIEW == mav.viewName;
        verify(saveMaterialAction, times(1)).save(material);
    }
    
    @Test(expected=ServletException)
    void testSaveMaterialThrowsDAOException() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(saveMaterialAction.save(any(Material))).thenThrow(new DAOException('Test exception'));
        ModelAndView mav = controller.saveMaterial(material, bindingResult);
    }
    
    @Test
    void testSaveMaterialHasErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        ModelAndView mav = controller.saveMaterial(material, bindingResult);
        assert MaterialController.MATERIAL_ADD_EDIT_VIEW == mav.viewName;
        verify(saveMaterialAction, never()).save(material);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, FORM);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, MaterialController.ERRORS);
    }
    
    @Test
    void testEditMaterial() {
        when(bindingResult.hasErrors()).thenReturn(false);
        ModelAndView mav = controller.updateMaterial(material, bindingResult);
        assert MaterialController.MATERIAL_SEARCH_VIEW == mav.viewName;
        verify(saveMaterialAction, times(1)).edit(material);
    }
    
    @Test(expected=ServletException)
    void testEditMaterialThrowsDAOException() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(saveMaterialAction.edit(any(Material))).thenThrow(new DAOException('Test exception'));
        ModelAndView mav = controller.updateMaterial(material, bindingResult);
    }
    
    @Test
    void testEditMaterialHasErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        ModelAndView mav = controller.updateMaterial(material, bindingResult);
        assert MaterialController.MATERIAL_ADD_EDIT_VIEW == mav.viewName;
        verify(saveMaterialAction, never()).edit(material);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, FORM);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, MaterialController.ERRORS);
        ModelAndViewAssert.assertModelAttributeAvailable(mav, MaterialController.MODE);
    }
}
