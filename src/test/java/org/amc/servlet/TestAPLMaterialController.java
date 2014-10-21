package org.amc.servlet;

import static org.junit.Assert.*;

import org.amc.DAOException;
import org.amc.dao.MaterialDAO;
import org.amc.servlet.APLMaterialController;
import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.MaterialActionFactoryImpl;
import org.amc.servlet.action.search.MaterialSearch;
import org.amc.servlet.model.MaterialSearchForm;
import org.amc.servlet.validator.MaterialSearchBinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.util.HashMap;

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
        MaterialSearch materialSearch = new MaterialSearchBinder()
                        .getMaterialSearch(savedMaterialForm);
        
        controller.setMaterialActionFactory(actionFactory);
        when(session.getAttribute(APLMaterialController.SESSION_MATERIALSEARCH)).thenReturn(materialSearch);
    
        ModelAndView mav=controller.searchForMaterial(request, emptyMaterialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,MATERIALS);
        
        verify(session,times(2)).getAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH));
        verify(session,times(0)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
        
        HashMap<?,?> list=(HashMap<?,?>)mav.getModel().get(MATERIALS);
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testSearchMaterialThrowsParseException() throws DAOException,ParseException{
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialSearchForm materialForm=new MaterialSearchForm();
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        
        APLMaterialController controller=new APLMaterialController();
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        MaterialSearchBinder materialBinder=mock(MaterialSearchBinder.class);
        
        materialForm.setCompany("company");
        materialForm.setName("name");
        materialForm.setType("type");
        MaterialSearch materialSearch = new MaterialSearchBinder().getMaterialSearch(materialForm);
        
        controller.setMaterialActionFactory(actionFactory);
        controller.setMaterialSearchBinder(materialBinder);
        
        when(session.getAttribute(APLMaterialController.SESSION_MATERIALSEARCH)).thenReturn(materialSearch);
        when(materialBinder.getMaterialSearch(materialForm)).thenThrow(ParseException.class);
        ModelAndView mav=controller.searchForMaterial(request, materialForm, session);
        
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,ControllerConstants.MESSAGE);
        
        verify(session,times(0)).getAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH));
        verify(session,times(0)).setAttribute(eq(APLMaterialController.SESSION_MATERIALSEARCH), anyObject());
        
        HashMap<?,?> list=(HashMap<?,?>)mav.getModel().get(MATERIALS);
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testAddMaterial(){
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        MaterialSearchBinder materialBinder=mock(MaterialSearchBinder.class);
        HttpServletRequest request=mock(HttpServletRequest.class);
        APLMaterialController controller=new APLMaterialController();
        controller.setMaterialActionFactory(actionFactory);
   
        ModelAndView mav=controller.addMaterial(request);
    
        ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_ADD_EDIT_VIEW);
        ModelAndViewAssert.assertModelAttributeAvailable(mav,ControllerConstants.FORM);
    }
    
    @Test
    public void testEditMaterial(){
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        MaterialSearchBinder materialBinder=mock(MaterialSearchBinder.class);
        HttpServletRequest request=mock(HttpServletRequest.class);
        APLMaterialController controller=new APLMaterialController();
        controller.setMaterialActionFactory(actionFactory);
        String id="1";//Database ID of Material
        
        try{
            ModelAndView mav=controller.editMaterial(request,id);
            ModelAndViewAssert.assertViewName(mav, APLMaterialController.MATERIAL_ADD_EDIT_VIEW);
            ModelAndViewAssert.assertModelAttributeAvailable(mav,ControllerConstants.FORM);
        }catch(ServletException se){
            fail("Exception not expected");
        }
    }
    
    @Test(expected=ServletException.class)
    public void testEditMaterialthrowsDAOException() throws DAOException, ServletException{
        String id="1";//Database ID of Material
        MaterialDAO dao=mock(MaterialDAO.class);
        MaterialActionFactory actionFactory=new MaterialActionFactoryImpl(dao);
        MaterialSearchBinder materialBinder=mock(MaterialSearchBinder.class);
        HttpServletRequest request=mock(HttpServletRequest.class);
        APLMaterialController controller=new APLMaterialController();
        
        controller.setMaterialActionFactory(actionFactory);
        when(dao.getEntity(eq(id))).thenThrow(DAOException.class);
        
        controller.editMaterial(request,id);
        
    }



}
