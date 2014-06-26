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
	private static Logger logger=Logger.getLogger(APLSpcDataController.class);
	private DAO<SPCPartsList> spcListPartDAO;
	private DAO<Part> partDAO;
	private SPCMeasurementDAO spcDimensionDAO;
	
	
	
	/*   Spring injected resources   */
	@Resource(name="spcPartsListDAO")
	public void setSPCListPartDAO(DAO<SPCPartsList> spcListPartDAO)
	{
		this.spcListPartDAO=spcListPartDAO;
		logger.debug("spcPartsListDAO:"+this.spcListPartDAO);
	}
	
	@Resource(name="partDAO")
	public void setPartDAO(DAO<Part> partDAO)
	{
		this.partDAO=partDAO;
		logger.debug("partDAO:"+this.partDAO);
	}
	
	@Resource(name="spcDimensionDAO")
	public void setSPCDimensionDAO(SPCMeasurementDAO spcDimensionDAO)
	{
		this.spcDimensionDAO=spcDimensionDAO;
		logger.debug("spcDimensionDAO:"+this.spcDimensionDAO);
	}
}
