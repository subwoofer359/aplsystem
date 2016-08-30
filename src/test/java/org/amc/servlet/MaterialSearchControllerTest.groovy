package org.amc.servlet

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.amc.DAOException;
import org.amc.model.Material
import org.amc.servlet.action.MaterialActionFactory
import org.amc.servlet.action.SearchMaterialAction;
import org.amc.servlet.action.search.MaterialSearch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.ModelAndViewAssert
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView

import javax.servlet.ServletException;

class MaterialSearchControllerTest {
    MaterialSearchController controller;
    MaterialSearch matSearch;
    Material material;
    def resultsList;
    def company = 'Tosara';
    def name = 'pot';
    def type = '400g';
    def materialId = 2;
    
    @Mock
    BindingResult errors;
    
    @Mock
    MaterialActionFactory materialActionFactory;
    
    @Mock
    SearchMaterialAction searchMaterialAction;
    
    MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new MaterialSearchController();
        when(materialActionFactory.getSearchMaterialAction()).thenReturn(searchMaterialAction);
        controller.materialActionFactory = materialActionFactory;
        
        session = new MockHttpSession();
        
        matSearch = new MaterialSearch();
        matSearch.company = company;
        matSearch.name = name;
        matSearch.type = type;
        
        material = new Material();
        material.company = company;
        material.id = materialId;
        material.name = name;
        material.type = type;
        
        resultsList = [ materialId: material ];
        
        
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void MaterialSearchTest() {
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(resultsList);
        
        ModelAndView mav = controller.searchForMaterial(session, matSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, resultsList);
    }
    
    @Test(expected=ServletException)
    public void MaterialSearchTestThrowsDAOException() {
        when(searchMaterialAction.search(any(MaterialSearch))).thenThrow(new DAOException('Test Exception'));
        
        ModelAndView mav = controller.searchForMaterial(session, matSearch, errors);
    }
    
    @Test
    public void MaterialSearchTestValidationErrrors() {
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(resultsList);
        when(errors.hasErrors()).thenReturn(true);
        
        ModelAndView mav = controller.searchForMaterial(session, matSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MESSAGE, errors);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, Collections.EMPTY_MAP);
    }
    
    @Test
    public void EmptyMaterialSearchTest() {
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(Collections.EMPTY_MAP);
        
        ModelAndView mav = controller.searchForMaterial(session, matSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, Collections.EMPTY_MAP);
    }
    
    @Test
    public void EmptyMaterialSearchSavedMaterialTest() {
        MaterialSearch newMaterialSearch = new MaterialSearch();
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(resultsList);
        session.setAttribute(MaterialController.SESSION_MATERIALSEARCH, matSearch);
        
        ModelAndView mav = controller.searchForMaterial(session, newMaterialSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, resultsList);
    }
    
    @Test
    public void NotEmptyMaterialSearchSavedMaterialTest() {
        MaterialSearch newMaterialSearch = [company:'ALPS'] as MaterialSearch;
        when(searchMaterialAction.search(eq(newMaterialSearch))).thenReturn(resultsList);
        when(searchMaterialAction.search(eq(matSearch))).thenReturn(Collections.EMPTY_MAP);
        session.setAttribute(MaterialController.SESSION_MATERIALSEARCH, matSearch);
        
        ModelAndView mav = controller.searchForMaterial(session, newMaterialSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, resultsList);
    }
    
    @Test
    public void EmptyMaterialSearchNoSavedMaterialTest() {
        MaterialSearch newMaterialSearch = new MaterialSearch();
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(resultsList);
        
        ModelAndView mav = controller.searchForMaterial(session, newMaterialSearch, errors);
        
        ModelAndViewAssert.assertViewName(mav, MaterialController.MATERIAL_SEARCH_PAGE);
        ModelAndViewAssert.assertModelAttributeValue(mav, ControllerConstants.MATERIALS, Collections.EMPTY_MAP);
    }
    
    @Test
    public void MaterialSearchSavedTest() {
        when(searchMaterialAction.search(any(MaterialSearch))).thenReturn(resultsList);
        
        ModelAndView mav = controller.searchForMaterial(session, matSearch, errors);
        
        assert mav.model[MaterialController.SESSION_MATERIALSEARCH] == matSearch;
    }

}
