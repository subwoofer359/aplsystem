package org.amc.servlet;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.amc.Constants.Roles;
import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCData;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	private static final String CURRENT_SPC_MEASUREMENT="CURRENT_SPC_MEASUREMENT";
	private static final String SPC_MEASUREMENTS="spcmeasurements";
	private static final String MESSAGE="message";
	private static final String SPC_PART="spcPart";
	private static final String PART="part";
	private static final String ERRORS="errors";

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
			@RequestParam("spcPart") Integer spcPartid,ModelAndView mav,
			HttpSession session
	)
	{
		mav.setViewName("spc/SPCEntryPage");
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add SPC Data");
			mav.setViewName("Main");
			return mav;
		}
		try
		{
			List<SPCMeasurement> spcmeasurements=spcDimensionDAO.findEntities(PART, spcPartid);
			if(spcmeasurements.size()>0)
			{
				session.setAttribute(CURRENT_SPC_MEASUREMENT,spcmeasurements.get(0));
				session.setAttribute(SPC_MEASUREMENTS, spcmeasurements);
				Part part=partDAO.getEntity(String.valueOf(spcPartid));
				session.setAttribute(PART, part);
			}
			else
			{
				mav.getModelMap().put(MESSAGE, "There are no SPC Dimensions for this part");
				mav.setViewName("forward:spc/SPCPartsLst");
			}
		}
		catch(DAOException de)
		{
			LOG.error(de.getMessage());
			mav.getModelMap().put(MESSAGE, de.getMessage());
			mav.setViewName("forward:spc/SPCPartsList");
		}
		
		return mav;
	}
	
	@RequestMapping("spc/saveSPCData")
	public ModelAndView saveSPCData(
			HttpServletRequest request,
			HttpSession session,
			ModelAndView mav,
			@ModelAttribute List<SPCData> data,
			BindingResult result
			)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add SPC Data");
			mav.setViewName("Main");
			return mav;
		}
		
		//Return to the SPCEntryPage
		mav.setViewName("spc/SPCEntryPage");
		
		if(result.hasErrors())
		{
			LOG.debug("APLSpcDataController:/SPC/saveSPCData:BindingError:"+result.getAllErrors());
			mav.getModelMap().put(ERRORS, getErrors(result));
		}
		else
		{
			try
			{
				spcDataDAO.addEntities(data);
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE, "SPC Data was not saved. Error in application");
				LOG.error("APLSpcDataController:Call to "+SPCDataDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				//Todo where to go next
			}
			//Update Session values
			List<SPCMeasurement> measurments=(List<SPCMeasurement>)session.getAttribute(SPC_MEASUREMENTS);
			SPCMeasurement currSPCMeasurement=(SPCMeasurement)session.getAttribute(CURRENT_SPC_MEASUREMENT);
			int index=measurments.indexOf(currSPCMeasurement);
			if(index!=-1 && index<measurments.size())
			{
				currSPCMeasurement=measurments.get(index+1);
			}
			else
			{
				//Clear session values
				session.removeAttribute(CURRENT_SPC_MEASUREMENT);
				session.removeAttribute(PART);
				session.removeAttribute(SPC_MEASUREMENTS);
				mav.setViewName("spc/SPCPartList");
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
//	private List<SPCData> getSPCDataFromRequest(HttpServletRequest request)
//	{
//		List<SPCData> spcData=new ArrayList<SPCData>();
//		SimpleDateFormat sdf=new SimpleDateFormat();
//		
//		String[] ids=request.getParameterValues("id");
//		String[] dates=request.getParameterValues("date");
//		String[] userId=request.getParameterValues("userId");
//		String[] measurmentNos=request.getParameterValues("measurementNo");
//		String[] measurements=request.getParameterValues("measurement");
//		String[] spcMeasurementsId=request.getParameterValues("spcMeasurementId");
//		
//		
//		try
//		{
//			for(int i=0;i<ids.length;i++)
//			{
//				SPCData data=new SPCData();
//				data.setDate((java.sql.Date)sdf.parse(dates[i]));
//				
//				//data.setUser(user);
//				
//				data.setMeasurement(Float.parseFloat(measurements[i]));
//			}
//		}
//		catch(NullPointerException npe)
//		{
//			
//		}
//		catch(ParseException pe)
//		{
//			
//		}
//		
//		return spcData;
//	}
}
