package org.amc.myservlet.test.spc;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.amc.Constants;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.APLSpcController;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;


public class TestAPLSpcController
{
	
	
	private DAO<SPCPartsList> partsListDao;
	private DAO<Part> partsDAO;
	private APLSpcController controller;
	private MockHttpServletRequest request;
	private BindingResult result;
	private SPCMeasurementDAO spcMeasurementDAO;
	private int spcPartid=1;
	
	
	
	
	
	
	
	@Before
	public void setUp()
	{
		request=new MockHttpServletRequest();
		//request.addUserRole(Constants.roles.QC.toString());
		
		result=mock(BindingResult.class);
		//when(result.hasErrors()).thenReturn(false);
		
		
		spcMeasurementDAO=mock(SPCMeasurementDAO.class);
		partsDAO=mock(DAO.class);
		partsListDao=mock(DAO.class);
		
		//when(spcListPartDAO.getEntity(anyString())).thenReturn(spcPartsList);
		
		controller=new APLSpcController();
		controller.setSPCDimensionDAO(spcMeasurementDAO);
		controller.setPartDAO(partsDAO);
		controller.setSPCListPartDAO(partsListDao);
	}
	
	
	/**
	 * Preconditions:The User is not in the correct role
	 * Preconditions:No PersistenceException thrown
	 */
	@Test
	public void testGetDimensionListNotInRole()
	{
		
		ModelAndView mav=new ModelAndView();
		
		//The user is not in the required role QC
		request.addUserRole(Constants.roles.GUEST.toString());
		
		SPCMeasurement spcMeasurement=getSPCMeasurement();
		
		//Call APLSpcController method
		mav=controller.addDimension(request,mav,spcPartid,spcMeasurement,result);
		
		//Check Error message set
		ModelAndViewAssert.assertModelAttributeAvailable(mav,"message");
		
		//Check the user is directed the Main.jsp
		ModelAndViewAssert.assertViewName(mav,"Main");
		
		//Check there were no calls on the SPCMeasurement DAO
		verifyZeroInteractions(spcMeasurementDAO);
	}
	
	/**
	 * 
	 * Preconditions:The User is in the correct role
	 * Preconditions:No PersistenceException thrown
	 */
	@Test
	public void testGetDimensionList()
	{
		ModelAndView mav=new ModelAndView();
		//The user is in the correct role
		request.addUserRole(Constants.roles.QC.toString());
		
		when(this.partsListDao.getEntity(anyString())).thenReturn(this.getSPCPartsList());
		
		SPCMeasurement spcMeasurement=getSPCMeasurement();
		
		//Call APLSpcController method
		mav=controller.addDimension(request,mav,spcPartid,spcMeasurement,result);
		
		//Check that the required attributes were set
		ModelAndViewAssert.assertModelAttributeAvailable(mav,"spcPart");
		ModelAndViewAssert.assertModelAttributeAvailable(mav,"dimensions");
		ModelAndViewAssert.assertModelAttributeAvailable(mav,"part");
		
		//Check the user is sent to the correct view
		ModelAndViewAssert.assertViewName(mav,"spc/SPCMeasurement");
	}
	
	/**
	 * Precondition:User in Role QC
	 * Precondition:Binding Result returns error
	 * Precondition:No PersistenceException thrown
	 */
	@Test
	public void testAddDimensionBindingError()
	{
		
		ModelAndView mav=new ModelAndView();
		
		request.addUserRole(Constants.roles.QC.toString());
		
		when(this.partsListDao.getEntity(anyString())).thenReturn(this.getSPCPartsList());
		
		//Binding Results returns true. Errors mapping the form values to the Model attribute found
		when(result.hasErrors()).thenReturn(true);
		
		SPCMeasurement spcMeasurement=getSPCMeasurement();
		
		mav=controller.addDimension(request,mav,spcPartid,spcMeasurement,result);

		//verifyZeroInteractions(spcMeasurementDAO);
		
		//Check the user is sent to the correct view
		ModelAndViewAssert.assertViewName(mav,"spc/SPCMeasurement");
		
		//Check Error message set
		Object errors=request.getAttribute("errors");
		
		//Check errors is a list
		assertTrue(errors instanceof List);
	}
	
	/** 
	 * Precondition:User not in Role QC
	 * Precondition:Binding Result no error
	 * Precondition:No PersistenceException thrown
	 */
	@Test
	public void testAddDimensionNotInRole()
	{
		
		ModelAndView mav=new ModelAndView();
		
		//User is not in the required role
		request.addUserRole(Constants.roles.GUEST.toString());
		
		//No binding errors
		when(result.hasErrors()).thenReturn(false);
		
		SPCMeasurement spcMeasurement=getSPCMeasurement();
		
		mav=controller.addDimension(request,mav,spcPartid,spcMeasurement,result);
		
		//Check there were no calls on the SPCMeasurement DAO
		verifyZeroInteractions(spcMeasurementDAO);
		
		//Check the user is sent to the correct view
		ModelAndViewAssert.assertViewName(mav,"Main");
		
		//Check Error message set
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "message");
	}
	
	/**
	 * Precondition:User in Role QC
	 * Precondition:Binding Result no error
	 * Precondition:No PersistenceException thrown
	 */
	@Test
	public void testAddDimension()
	{
		ModelAndView mav=new ModelAndView();
		
		request.addUserRole(Constants.roles.QC.toString());
		
		when(result.hasErrors()).thenReturn(false);
		
		SPCMeasurement spcMeasurement=getSPCMeasurement();
		
		when(this.partsListDao.getEntity(anyString())).thenReturn(this.getSPCPartsList());
		
		mav=controller.addDimension(request,mav,spcPartid,spcMeasurement,result);
		
		//Check that entity SPCMeasurement is passed to the DAO for persistence
		verify(spcMeasurementDAO).addEntity(spcMeasurement);
		
		//Check the user is sent to the correct view
		ModelAndViewAssert.assertViewName(mav,"spc/SPCMeasurement");
		
		//Check that the required attribute was set
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "part");
	}
	
	/**
	 * Precondition:User in Role QC
	 * Precondition:Binding Result no error
	 * Precondition:PersistenceException thrown
	 */
	@Test
	public void testAddDimensionExceptionThrown()
	{
		assertTrue(false);
	}
	/**
	 * 
	 * @return typical SPCMeasurement Object
	 */
	private SPCMeasurement getSPCMeasurement()
	{
		SPCMeasurement spcMeasurement=new SPCMeasurement();
		
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
	private SPCPartsList getSPCPartsList()
	{
		SPCPartsList spcPartsList=new SPCPartsList();
		spcPartsList.setId(1);
		spcPartsList.setPart(new Part());
		return spcPartsList;
	}
}
