package org.amc.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.amc.Constants;
import org.amc.Constants.Roles;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.amc.servlet.ControllerConstants.PART;
import static org.amc.servlet.ControllerConstants.MESSAGE;
import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.SPC_MEASUREMENTS;
import static org.amc.servlet.ControllerConstants.CURRENT_SPC_MEASUREMENT;
import static org.amc.servlet.ControllerConstants.MAIN_VIEW;
import static org.amc.servlet.ControllerConstants.SPCLISTPARTS;
import static org.amc.servlet.ControllerConstants.SPC_ENTRYPAGE_VIEW;
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
	 * SPCDataDAO injected by Spring IOC
	 */
	private SPCDataDAO spcDataDAO;
	
	

	/**
	 * Fetches SPCMeasurements related to the Part in spcPart and pass them to the view SPCEntryPage
	 * @param request
	 * @param spcPartid
	 * @param mav
	 * @return ModelandView
	 */
	@RequestMapping("/SPC/addData")
	public ModelAndView openSPCDataEntry(
			HttpServletRequest request,
			@RequestParam(MODE_EDIT) Integer spcPartid,
			ModelAndView mav,
			HttpSession session
	)
	{
		mav.setViewName(SPC_ENTRYPAGE_VIEW);
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add SPC Data");
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		try
		{
			SPCPartsList spcPartList=spcListPartDAO.getEntity(String.valueOf(spcPartid));
			Part part=spcPartList.getPart();
			LOG.debug("Part"+part+" retrieved");
			LOG.debug("Part ID:"+part.getId());
			List<SPCMeasurement> spcmeasurements=spcDimensionDAO.findEntities(PART+".id",part.getId());
			LOG.debug("SPCMeasurements:"+spcmeasurements.size());
			if(spcmeasurements.size()>0)
			{
				LOG.debug("Does spcmeasurements contain the current SPC measurement:("+session.getAttribute(CURRENT_SPC_MEASUREMENT)+")"+spcmeasurements.contains((SPCMeasurement)session.getAttribute(CURRENT_SPC_MEASUREMENT)));
				if(session.getAttribute(CURRENT_SPC_MEASUREMENT)==null|| !spcmeasurements.contains((SPCMeasurement)session.getAttribute(CURRENT_SPC_MEASUREMENT)))
				{
					session.setAttribute(CURRENT_SPC_MEASUREMENT,spcmeasurements.get(0));
				}
				session.setAttribute(SPC_MEASUREMENTS, spcmeasurements);
				
				session.setAttribute(PART, part);
			}
			else
			{
				mav.getModelMap().put(MESSAGE, "There are no SPC Dimensions for this part");
				mav.setViewName("forward:"+SPCLISTPARTS);
			}
		}
		catch(DAOException de)
		{
			LOG.error(de.getMessage());
			mav.getModelMap().put(MESSAGE, de.getMessage());
			mav.setViewName("forward:"+SPCLISTPARTS);
		}
		LOG.info("APLSPCDataController:At the end for the Function");
		return mav;
	}
	
	@RequestMapping("SPC/saveSPCData")
	public ModelAndView saveSPCData(
			HttpServletRequest request,
			HttpSession session,
			ModelAndView mav,
			//@ModelAttribute SPCData data[],
			BindingResult result
			)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add SPC Data");
			mav.setViewName(MAIN_VIEW);
			return mav;
		}
		
		//Return to the SPCEntryPage
		mav.setViewName(SPC_ENTRYPAGE_VIEW);
		
		List<SPCData> data=new ArrayList<SPCData>();
		String[] measurements=request.getParameterValues("measurement");
		String[] measurementNumber=request.getParameterValues("measurementNumber");
		
		for(int i=0;i<measurements.length;i++)
		{
			SPCData spcData=new SPCData();
			spcData.setMeasurement(Float.parseFloat(measurements[i]));
			spcData.setMeasurementNumber(Integer.parseInt(measurementNumber[i]));
			spcData.setDate(new java.sql.Date(System.currentTimeMillis()));
			spcData.setSpcMeasurement((SPCMeasurement)session.getAttribute(CURRENT_SPC_MEASUREMENT));
			spcData.setUser((User)session.getAttribute(Constants.SESSIONVAR_USER));
			LOG.debug(spcData);
			data.add(spcData);
		}
		
		
		if(result.hasErrors())
		{
			LOG.debug("APLSpcDataController:/SPC/saveSPCData:BindingError:"+result.getAllErrors());
			mav.getModelMap().put(ERRORS, getErrors(result));
		}
		else
		{
			try
			{
				spcDataDAO.addEntities((SPCMeasurement)session.getAttribute(CURRENT_SPC_MEASUREMENT),data);
				LOG.debug("APLSpcDataController:SPC Data saved:"+data);
				//Update Session values
				@SuppressWarnings("unchecked")
				List<SPCMeasurement> measurments=(List<SPCMeasurement>)session.getAttribute(SPC_MEASUREMENTS);
				int index=measurments.indexOf(session.getAttribute(CURRENT_SPC_MEASUREMENT));
				if(index!=-1 && index<measurments.size()-1)
				{
					session.setAttribute(CURRENT_SPC_MEASUREMENT,measurments.get(index+1));
				}
				else
				{
					//Clear session values
					session.removeAttribute(CURRENT_SPC_MEASUREMENT);
					session.removeAttribute(PART);
					session.removeAttribute(SPC_MEASUREMENTS);
					mav.setViewName("forward:"+SPCLISTPARTS);
				}
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE, "SPC Data was not saved. Error in application");
				LOG.error("APLSpcDataController:Call to "+SPCDataDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
			}
		}
		
		return mav;
	}
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
	
	/**
	 * Spring injected resources
	 * @param spcDataDAO
	 */
	@Resource(name="spcDataDAO")
	public void setSPCDataDAO(SPCDataDAO spcDataDAO)
	{
		this.spcDataDAO=spcDataDAO;
	}
	
	private Map<String,String> getErrors(BindingResult result)
	{
		Map<String,String> errors=new HashMap<String,String>();
		
		
		FieldError e=result.getFieldError();
		errors.put(e.getField(),e.getCode());
		
		
		return errors;
	}
}
