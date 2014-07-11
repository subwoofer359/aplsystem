package org.amc.servlet;

import javax.annotation.Resource;

import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCPartsList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * 
 * The controller for SPCData creation
 * @author Adrian McLaughlin
 * @version 1
 *
 */
@Controller
public class APLSpcDataController
{
	/**
	 * Logging Service
	 */
	private static final Logger LOG=Logger.getLogger(APLSpcDataController.class);
	
	/**
	 * SPCPartsList DAO injected by Spring IOC
	 */
	private DAO<SPCPartsList> spcListPartDAO;
	
	/**
	 * Part DAO injected by Spring IOC
	 */
	private DAO<Part> partDAO;
	
	/**
	 * SPCMeasurement DAO injected by Spring IOC
	 */
	private SPCMeasurementDAO spcDimensionDAO;

	/**
	 * Spring injected resources   
	 * @param spcListPartDAO
	 */
	@Resource(name="spcPartsListDAO")
	public void setSPCListPartDAO(DAO<SPCPartsList> spcListPartDAO)
	{
		this.spcListPartDAO=spcListPartDAO;
		LOG.debug(getClass().getSimpleName()+":"+this.spcListPartDAO);
	}
	
	/**
	 * Spring injected resources   
	 * @param partDAO
	 */
	@Resource(name="partDAO")
	public void setPartDAO(DAO<Part> partDAO)
	{
		this.partDAO=partDAO;
		LOG.debug(getClass().getSimpleName()+":"+this.partDAO);
	}
	
	/**
	 * Spring injected resources   
	 * @param spcDimensionDAO
	 */
	@Resource(name="spcDimensionDAO")
	public void setSPCDimensionDAO(SPCMeasurementDAO spcDimensionDAO)
	{
		this.spcDimensionDAO=spcDimensionDAO;
		LOG.debug(getClass().getSimpleName()+":"+this.spcDimensionDAO);
	}
}
