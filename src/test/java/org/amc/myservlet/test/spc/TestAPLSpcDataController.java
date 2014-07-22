package org.amc.myservlet.test.spc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.amc.Constants;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.myservlet.test.TestPartandMouldingProcessDAO;
import org.amc.servlet.APLSpcController;
import org.amc.servlet.APLSpcDataController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

public class TestAPLSpcDataController
{

	private DAO<SPCPartsList> partsListDao;
	private DAO<Part> partsDAO;
	private APLSpcDataController controller;
	private SPCMeasurementDAO spcMeasurementDAO;
	private MockHttpServletRequest request;
	private BindingResult result;
	private HttpSession session;
	private int spcPartid=1;
	
	private static final String CURRENT_SPC_MEASUREMENT="CURRENT_SPC_MEASUREMENT";
	private static final String SPC_MEASUREMENTS="spcmeasurements";
	private static final String MESSAGE="message";
	private static final String SPC_PART="spcPart";
	private static final String PART="part";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		request=new MockHttpServletRequest();
		session=new MockHttpSession();
		result=mock(BindingResult.class);
		spcMeasurementDAO=mock(SPCMeasurementDAO.class);
		
		partsDAO=mock(DAO.class);
		partsListDao=mock(DAO.class);
		
		controller=new APLSpcDataController();
		controller.setSPCDimensionDAO(spcMeasurementDAO);
		controller.setPartDAO(partsDAO);
		controller.setSPCListPartDAO(partsListDao);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Tests method openSPCDataEntry
	 * @throws DAOException
	 */
	@Test
	public void testOpenSPCDataEntry() throws DAOException
	{
		ModelAndView mav=new ModelAndView();
		request.addUserRole(Constants.Roles.QC.toString());
		
		SPCMeasurement measurement=new SPCMeasurement();
		measurement.setActive(true);
		measurement.setDimension("length");
		measurement.setLowerLimit(0.3f);
		measurement.setUpperLimit(0.3f);
		measurement.setNominal(152f);
		measurement.setNoOfMeasurements(5);
		Part part= TestPartandMouldingProcessDAO.getPart("new Part");
		
		List<SPCMeasurement> spcDimensions=new ArrayList<SPCMeasurement>();
		spcDimensions.add(measurement);
		
		when(this.spcMeasurementDAO.findEntities(eq(PART), anyObject())).thenReturn(spcDimensions);
		when(this.partsDAO.getEntity(String.valueOf(spcPartid))).thenReturn(part);
		
		controller.openSPCDataEntry(request, spcPartid, mav, session);
		
		
		//Test all three variables set and are valid
		assertNotNull(session.getAttribute(CURRENT_SPC_MEASUREMENT));
		assertEquals(measurement, session.getAttribute(CURRENT_SPC_MEASUREMENT));
		
		assertNotNull(session.getAttribute(SPC_MEASUREMENTS));
		assertTrue(((List<SPCMeasurement>)session.getAttribute(SPC_MEASUREMENTS)).size()>0);
		
		assertNotNull(session.getAttribute(PART));
		assertEquals(part, session.getAttribute(PART));
		
		//Not error message set
		assertNull(session.getAttribute(MESSAGE));
	}

}
