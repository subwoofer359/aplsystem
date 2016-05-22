package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.amc.Constants;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.myservlet.test.TestPartandMouldingProcessDAOIT;
import org.amc.servlet.APLSpcController;
import org.amc.servlet.APLSpcDataController;
import org.amc.servlet.APLSpcDataController.SPCDataForm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.verification.VerificationMode;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

public class TestAPLSpcDataController {

    private DAO<SPCPartsList> partsListDao;
    private DAO<Part> partsDAO;
    private SPCDataDAO spcDataDAO;
    private APLSpcDataController controller;
    private SPCMeasurementDAO spcMeasurementDAO;
    private MockHttpServletRequest request;
    private BindingResult result;
    private HttpSession session;
    private int spcPartid = 1;

    private static final String CURRENT_SPC_MEASUREMENT = "CURRENT_SPC_MEASUREMENT";
    private static final String SPC_MEASUREMENTS = "spcmeasurements";
    private static final String MESSAGE = "message";
    private static final String ERRORS = "errors";
    private static final String SPC_PART = "spcPart";
    private static final String PART = "part";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        result = mock(BindingResult.class);
        spcMeasurementDAO = mock(SPCMeasurementDAO.class);

        partsDAO = mock(DAO.class);
        partsListDao = mock(DAO.class);
        spcDataDAO = mock(SPCDataDAO.class);

        controller = new APLSpcDataController();
        controller.setSPCDimensionDAO(spcMeasurementDAO);
        controller.setPartDAO(partsDAO);
        controller.setSPCListPartDAO(partsListDao);
        controller.setSPCDataDAO(spcDataDAO);

        // Default User
        User user = new User();
        user.setUserName("teddyBear");
        user.setFullName("Sir Teddy Bear");
        user.setEmailAddress("Teddy@gmail.com");
        user.setActive(true);
        session.setAttribute(Constants.SESSIONVAR_USER, user);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Tests method openSPCDataEntry
     * 
     * @throws DAOException
     */
    @Test
    public void testOpenSPCDataEntry() throws DAOException {
        ModelAndView mav = new ModelAndView();
        request.addUserRole(Constants.Roles.QC.toString());

        SPCMeasurement measurement = new SPCMeasurement();
        measurement.setActive(true);
        measurement.setDimension("length");
        measurement.setLowerLimit(0.3f);
        measurement.setUpperLimit(0.3f);
        measurement.setNominal(152f);
        measurement.setNoOfMeasurements(5);
        Part part = TestPartandMouldingProcessDAOIT.getPart("new Part");
        SPCPartsList spcPartsList = new SPCPartsList();
        spcPartsList.setPart(part);

        List<SPCMeasurement> spcDimensions = new ArrayList<SPCMeasurement>();
        spcDimensions.add(measurement);

        when(this.spcMeasurementDAO.findEntities(eq("part.id"), anyObject())).thenReturn(
                        spcDimensions);
        when(this.partsListDao.getEntity(String.valueOf(spcPartid))).thenReturn(spcPartsList);

        controller.openSPCDataEntry(request, spcPartid, mav, session);

        // Test all three variables set and are valid
        assertNotNull(session.getAttribute(CURRENT_SPC_MEASUREMENT));
        assertEquals(measurement, session.getAttribute(CURRENT_SPC_MEASUREMENT));

        assertNotNull(session.getAttribute(SPC_MEASUREMENTS));
        assertTrue(((List<SPCMeasurement>) session.getAttribute(SPC_MEASUREMENTS)).size() > 0);

        assertNotNull(session.getAttribute(PART));
        assertEquals(part, session.getAttribute(PART));

        // Not error message set
        assertNull(mav.getModel().get(MESSAGE));

        controller.openSPCDataEntry(request, spcPartid, mav, session);
    }

    @Test
    public void testSaveSPCData() throws DAOException {
        ModelAndView mav = new ModelAndView();
        request.addUserRole(Constants.Roles.QC.toString());
        when(result.hasErrors()).thenReturn(false);
        SPCDataForm form = new SPCDataForm();

        List<SPCMeasurement> measurements = getMeasurements();
        Part part = TestPartandMouldingProcessDAOIT.getPart("new Part");

        session.setAttribute(CURRENT_SPC_MEASUREMENT, measurements.get(0));
        session.setAttribute(SPC_MEASUREMENTS, measurements);
        session.setAttribute(PART, part);

        List<Float> measurement = new ArrayList<Float>();
        measurement.add(1f);
        form.setMeasurement(measurement);
        List<Integer> measurementNumbers = new ArrayList<Integer>();
        measurementNumbers.add(1);
        form.setMeasurementNumber(measurementNumbers);

        controller.saveSPCData(request, session, mav, form, result);

        verify(spcDataDAO, times(1)).addEntities(any(SPCMeasurement.class),
                        anyListOf(SPCData.class));
        assertEquals(measurements.get(1), session.getAttribute(CURRENT_SPC_MEASUREMENT));
        ModelAndViewAssert.assertViewName(mav, "spc/SPCEntryPage");
        // No error
        assertNull(mav.getModel().get(MESSAGE));
        assertNull(mav.getModel().get(ERRORS));

    }

    @Test
    public void testSaveLastSPCData() throws DAOException {
        ModelAndView mav = new ModelAndView();
        request.addUserRole(Constants.Roles.QC.toString());
        when(result.hasErrors()).thenReturn(false);
        SPCDataForm form = new SPCDataForm();
        List<Float> measurement = new ArrayList<Float>();
        measurement.add(1f);
        form.setMeasurement(measurement);
        List<Integer> measurementNumbers = new ArrayList<Integer>();
        measurementNumbers.add(1);
        form.setMeasurementNumber(measurementNumbers);

        List<SPCMeasurement> measurements = getMeasurements();
        Part part = TestPartandMouldingProcessDAOIT.getPart("new Part");

        session.setAttribute(CURRENT_SPC_MEASUREMENT, measurements.get(measurements.size() - 1));
        session.setAttribute(SPC_MEASUREMENTS, measurements);
        session.setAttribute(PART, part);
        controller.saveSPCData(request, session, mav, form, result);

        verify(spcDataDAO, times(1)).addEntities(any(SPCMeasurement.class),
                        anyListOf(SPCData.class));
        ModelAndViewAssert.assertViewName(mav, "forward:/app/spc/SPCListParts");
        assertNull(session.getAttribute(CURRENT_SPC_MEASUREMENT));
        assertNull(session.getAttribute(SPC_MEASUREMENTS));
        assertNull(session.getAttribute(PART));

    }

    @Test
    public void testSaveSPCDataExceptionThrown() throws DAOException {
        ModelAndView mav = new ModelAndView();
        request.addUserRole(Constants.Roles.QC.toString());
        when(result.hasErrors()).thenReturn(false);
        doThrow(DAOException.class).when(spcDataDAO).addEntities(any(SPCMeasurement.class),
                        anyListOf(SPCData.class));

        SPCDataForm form = new SPCDataForm();
        List<Float> measurement = new ArrayList<Float>();
        measurement.add(1f);
        form.setMeasurement(measurement);
        List<Integer> measurementNumbers = new ArrayList<Integer>();
        measurementNumbers.add(1);
        form.setMeasurementNumber(measurementNumbers);

        List<SPCMeasurement> measurements = getMeasurements();
        Part part = TestPartandMouldingProcessDAOIT.getPart("new Part");

        session.setAttribute(CURRENT_SPC_MEASUREMENT, measurements.get(0));
        session.setAttribute(SPC_MEASUREMENTS, measurements);
        session.setAttribute(PART, part);

        controller.saveSPCData(request, session, mav, form, result);

        assertNotNull(mav.getModel().get(MESSAGE));
        assertNull(mav.getModel().get(ERRORS));
        ModelAndViewAssert.assertViewName(mav, "spc/SPCEntryPage");
        // The session attributed shouldn't be changed
        assertEquals(measurements.get(0), session.getAttribute(CURRENT_SPC_MEASUREMENT));

        // No error

    }

    private List<SPCMeasurement> getMeasurements() {
        List<SPCMeasurement> measurements = new ArrayList<SPCMeasurement>();
        String[] dimensions = { "length", "height", "width", "radius" };
        float[] lowerLimits = { 0.3f, 0.2f, 0f, 3f, 1f };
        float[] upperLimits = { 0.3f, 0.2f, 0f, 3f, 1f };
        float[] nominals = { 123.34f, 221.2f, 11.22f, 21.23f, 0.33f };
        int[] noOfMeasurments = { 3, 2, 5, 5, 10 };
        String[] tableIds = { "tableId0020202020", "tableId03", "tableId0020ffrrf0",
                "tableId022ff202020", "tableId0020330" };

        for (int i = 0; i < dimensions.length; i++) {
            SPCMeasurement measurement = new SPCMeasurement();
            measurement.setActive(true);
            measurement.setDimension(dimensions[i]);
            measurement.setLowerLimit(lowerLimits[i]);
            measurement.setUpperLimit(upperLimits[i]);
            measurement.setNominal(nominals[i]);
            measurement.setNoOfMeasurements(noOfMeasurments[i]);
            measurement.setTableId(tableIds[i]);
            measurements.add(measurement);
        }

        return measurements;
    }

}
