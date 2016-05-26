package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.amc.Constants;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;

import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.APLSpcController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

public class TestAPLSpcController {

    private DAO<SPCPartsList> partsListDao;
    private DAO<Part> partsDAO;
    private APLSpcController controller;
    private MockHttpServletRequest request;
    private BindingResult result;
    private SPCMeasurementDAO spcMeasurementDAO;
    private int spcPartid = 1;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        // request.addUserRole(Constants.roles.QC.toString());

        result = mock(BindingResult.class);
        // when(result.hasErrors()).thenReturn(false);

        spcMeasurementDAO = mock(SPCMeasurementDAO.class);

        partsDAO = mock(DAO.class);
        partsListDao = mock(DAO.class);

        // when(spcListPartDAO.getEntity(anyInt())).thenReturn(spcPartsList);

        controller = new APLSpcController();
        controller.setSPCDimensionDAO(spcMeasurementDAO);
        controller.setPartDAO(partsDAO);
        controller.setSPCListPartDAO(partsListDao);

        try {
            when(this.partsListDao.getEntity(anyInt())).thenReturn(this.getSPCPartsList());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Preconditions:The User is not in the correct role Preconditions:No
     * PersistenceException thrown
     */
    @Test
    public void testGetDimensionListNotInRole() {

        ModelAndView mav = new ModelAndView();

        // The user is not in the required role QC
        request.addUserRole(Constants.Roles.GUEST.toString());

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        // Call APLSpcController method
        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // Check Error message set
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "message");

        // Check the user is directed the Main.jsp
        ModelAndViewAssert.assertViewName(mav, "Main");

        // Check there were no calls on the SPCMeasurement DAO
        verifyZeroInteractions(spcMeasurementDAO);
    }

    /**
     * 
     * Preconditions:The User is in the correct role Preconditions:No
     * PersistenceException thrown
     */
    @Test
    public void testGetDimensionList() throws DAOException {
        ModelAndView mav = new ModelAndView();
        // The user is in the correct role
        request.addUserRole(Constants.Roles.QC.toString());

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        // Call APLSpcController method
        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // Check that the required attributes were set
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "spcPart");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "dimensions");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "part");

        // Check the user is sent to the correct view
        ModelAndViewAssert.assertViewName(mav, "spc/SPCMeasurement");
    }

    /**
     * Precondition:User in Role QC Precondition:Binding Result returns error
     * Precondition:No PersistenceException thrown
     */
    @Test
    public void testAddDimensionBindingError() throws DAOException {

        ModelAndView mav = new ModelAndView();

        request.addUserRole(Constants.Roles.QC.toString());

        when(this.partsListDao.getEntity(anyInt())).thenReturn(this.getSPCPartsList());

        // Binding Results returns true. Errors mapping the form values to the
        // Model attribute found
        when(result.hasErrors()).thenReturn(true);
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getField()).thenReturn("Input Field");
        when(fieldError.getCode()).thenReturn("Input Code");
        when(result.getFieldError()).thenReturn(fieldError);

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // verifyZeroInteractions(spcMeasurementDAO);

        // Check the user is sent to the correct view
        ModelAndViewAssert.assertViewName(mav, "spc/SPCMeasurement");

        // Check Error message set
        Object errors = mav.getModelMap().get("errors");

        // Check errors is a list
        assertTrue(errors instanceof Map);
    }

    /**
     * Precondition:User not in Role QC Precondition:Binding Result no error
     * Precondition:No PersistenceException thrown
     */
    @Test
    public void testAddDimensionNotInRole() {

        ModelAndView mav = new ModelAndView();

        // User is not in the required role
        request.addUserRole(Constants.Roles.GUEST.toString());

        // No binding errors
        when(result.hasErrors()).thenReturn(false);

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // Check there were no calls on the SPCMeasurement DAO
        verifyZeroInteractions(spcMeasurementDAO);

        // Check the user is sent to the correct view
        ModelAndViewAssert.assertViewName(mav, "Main");

        // Check Error message set
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "message");
    }

    /**
     * Precondition:User in Role QC Precondition:Binding Result no error
     * Precondition:No PersistenceException thrown
     */
    @Test
    public void testAddDimension() throws DAOException {
        ModelAndView mav = new ModelAndView();

        request.addUserRole(Constants.Roles.QC.toString());

        when(result.hasErrors()).thenReturn(false);

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // Check that entity SPCMeasurement is passed to the DAO for persistence
        verify(spcMeasurementDAO).addEntity(spcMeasurement);

        // Check the user is sent to the correct view
        ModelAndViewAssert.assertViewName(mav, "spc/SPCMeasurement");

        // Check that the required attribute was set
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "part");
    }

    /**
     * Precondition:User in Role QC Precondition:Binding Result no error
     * Precondition:PersistenceException thrown
     */
    @Test
    public void testAddDimensionExceptionThrown() throws DAOException {
        ModelAndView mav = new ModelAndView();

        // User is not in the required role
        request.addUserRole(Constants.Roles.QC.toString());
        Map<String, Object> params = new HashMap<String, Object>();
        request.setParameters(params);

        // No binding errors
        when(result.hasErrors()).thenReturn(false);

        SPCMeasurement spcMeasurement = getSPCMeasurement();

        doThrow(new DAOException()).when(spcMeasurementDAO).addEntity(any(SPCMeasurement.class));

        mav = controller.addDimension(request, mav, spcPartid, spcMeasurement, result);

        // Check the user is sent to the correct view
        ModelAndViewAssert.assertViewName(mav, "spc/SPCMeasurement");

        // Check that the required attribute was set
        /* @Todo */
        // ModelAndViewAssert.assertModelAttributeAvailable(mav, "message");
        assertNotNull(mav.getModelMap().get("message"));

    }

    @Test
    public void testDeleteSPCMeasurement() {
        ModelAndView mav = new ModelAndView();

        request.addUserRole(Constants.Roles.QC.toString());

        int id = 1;

        mav = controller.removeDimension(request, mav, spcPartid, id);

        try {
            verify(spcMeasurementDAO).deleteEntity(any(SPCMeasurement.class));
        } catch (DAOException e) {
            // Handled by Controller
        }
        // No Error message
        assertNull(mav.getModelMap().get("message"));
    }

    @Test
    public void testDeleteSPCMeasurementThrowException() {
        ModelAndView mav = new ModelAndView();

        request.addUserRole(Constants.Roles.QC.toString());

        try {
            doThrow(new DAOException()).when(spcMeasurementDAO).deleteEntity(
                            any(SPCMeasurement.class));
        } catch (DAOException de) {
            // Handled by Controller
        }

        int id = 1;

        mav = controller.removeDimension(request, mav, spcPartid, id);

        ModelAndViewAssert.assertModelAttributeAvailable(mav, "message");

    }

    /**
     * 
     * @return typical SPCMeasurement Object
     */
    private SPCMeasurement getSPCMeasurement() {
        SPCMeasurement spcMeasurement = new SPCMeasurement();

        spcMeasurement.setDimension("Length of Side");
        spcMeasurement.setActive(true);
        spcMeasurement.setLowerLimit(3);
        spcMeasurement.setUpperLimit(3);
        spcMeasurement.setNoOfMeasurements(5);
        spcMeasurement.setNominal(123.3f);
        return spcMeasurement;
    }

    /**
     * 
     * @return typical SPCPartsList Object
     */
    private SPCPartsList getSPCPartsList() {
        SPCPartsList spcPartsList = new SPCPartsList();
        spcPartsList.setId(1);
        spcPartsList.setPart(new Part());
        return spcPartsList;
    }
}
