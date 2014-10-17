package org.amc.servlet;

import static org.junit.Assert.*;

import org.amc.DAOException;
import org.amc.dao.MaterialDAO;
import org.amc.servlet.APLMaterialController;
import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.MaterialActionFactoryImpl;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.model.MaterialSearchForm;
import org.amc.servlet.validator.MaterialSearchValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.amc.servlet.ControllerConstants.MATERIALS;

public class TestAPLMaterialController {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSearchMaterial() throws DAOException{
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        APLMaterialController controller=new APLMaterialController();
        controller.setMaterialActionFactory(actionFactory);
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        MaterialSearchForm materialForm=new MaterialSearchForm();
        materialForm.setCompany("company");
        materialForm.setName("name");
        materialForm.setType("type");
       
        ModelAndView mav=controller.searchForMaterial(request, materialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,MATERIALS);
        
        verify(session,times(1)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
    }
    
    @Test
    public void testSearchMaterialEmptyWebFormNoSessionAttribute() throws DAOException{
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        APLMaterialController controller=new APLMaterialController();
        controller.setMaterialActionFactory(actionFactory);
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        MaterialSearchForm materialForm=new MaterialSearchForm();
       
        ModelAndView mav=controller.searchForMaterial(request, materialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,MATERIALS);
        
        verify(session,times(1)).getAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH));
        verify(session,times(0)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
    }
    
    @Test
    public void testSearchMaterialEmptyWebFormSessionAttribute() throws DAOException,ParseException{
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialSearchForm emptyMaterialForm=new MaterialSearchForm();
        MaterialSearchForm savedMaterialForm=new MaterialSearchForm();
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        APLMaterialController controller=new APLMaterialController();
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        
        savedMaterialForm.setCompany("company");
        savedMaterialForm.setName("name");
        savedMaterialForm.setType("type");
        MaterialSearch materialSearch = MaterialSearchValidator.MaterialSearchBinder
                        .getMaterialSearch(savedMaterialForm);
        
        controller.setMaterialActionFactory(actionFactory);
        when(session.getAttribute(APLMaterialController.SESSION_MATERIALSEARCH)).thenReturn(materialSearch);
    
        ModelAndView mav=controller.searchForMaterial(request, emptyMaterialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,MATERIALS);
        
        verify(session,times(2)).getAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH));
        verify(session,times(0)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
        
        HashMap list=(HashMap)mav.getModel().get(MATERIALS);
        assertTrue(list.isEmpty());
    }
    
    
    //todo finish this test case 
    public void testSearchMaterialThrowsParseException() throws DAOException,ParseException{
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialSearchForm emptyMaterialForm=new MaterialSearchForm();
        MaterialSearchForm savedMaterialForm=new MaterialSearchForm();
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        APLMaterialController controller=new APLMaterialController();
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        
        savedMaterialForm.setCompany("company");
        savedMaterialForm.setName("name");
        savedMaterialForm.setType("type");
        MaterialSearch materialSearch = MaterialSearchValidator.MaterialSearchBinder
                        .getMaterialSearch(savedMaterialForm);
        
        controller.setMaterialActionFactory(actionFactory);
        when(session.getAttribute(APLMaterialController.SESSION_MATERIALSEARCH)).thenReturn(materialSearch);
    
        ModelAndView mav=controller.searchForMaterial(request, emptyMaterialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,MATERIALS);
        
        verify(session,times(2)).getAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH));
        verify(session,times(0)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
        
        HashMap list=(HashMap)mav.getModel().get(MATERIALS);
        assertTrue(list.isEmpty());
    }



}
