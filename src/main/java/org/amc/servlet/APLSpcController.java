package org.amc.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.validator.SPCMeasurementValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.amc.Constants.Roles;
import static org.amc.servlet.ControllerConstants.MESSAGE;
import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.PART;
import static org.amc.servlet.ControllerConstants.PARTS;
import static org.amc.servlet.ControllerConstants.SPC_PART;
import static org.amc.servlet.ControllerConstants.DIMENSIONS;
import static org.amc.servlet.ControllerConstants.MAIN_VIEW;
import static org.amc.servlet.ControllerConstants.PARTSEARCH_VIEW;
import static org.amc.servlet.ControllerConstants.SPC_PARTLIST_VIEW;
import static org.amc.servlet.ControllerConstants.SPC_MEASUREMENT_VIEW;

/**
 * 
 * @author Adrian McLaughlin
 * Controller for the SPC model and view
 *
 */

@Controller
public class APLSpcController
{
	/**
	 * Logging Service
	 */
	private static Logger lOG=Logger.getLogger(APLSpcController.class);
	/**
	 * SPCPartsList DAO injected by Spring IOC
	 */
	private DAO<SPCPartsList> spcPartsListDAO;
	/**
	 * Part DAO injected by Spring IOC
	 */
	private DAO<Part> partDAO;
	/**
	 * SPCMeasurement DAO injected by Spring IOC
	 */
	private SPCMeasurementDAO spcMeasurementDAO;
	
	
	
	/**
	 * Retrieve and return SPC Part List
	 */
	@RequestMapping("/SPCPartsList")
	public ModelAndView getSPCPartList(ModelAndView mav)
	{
		
		lOG.debug("spcPartsListDAO in getSPCPartList:"+this.spcPartsListDAO);
		List<SPCPartsList> list=null;
		try
		{
			list=spcPartsListDAO.findEntities();
		}
		catch(DAOException de)
		{
			mav.getModel().put(MESSAGE,de.getMessage());
		}
		
		if(list==null)
		{
			list=new ArrayList<SPCPartsList>();
		}
		
		mav.getModel().put(PARTS, list);
		mav.setViewName(SPC_PARTLIST_VIEW);
		return mav;
	}
	
	@RequestMapping("/AddToSPC")
	public ModelAndView addToSPC(ModelAndView mav,@RequestParam(MODE_EDIT) Integer id,HttpServletRequest request)
	{
		//If User not in role QC then return with error message
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add Parts to the SPCList");
			mav.setViewName("forward:"+PARTSEARCH_VIEW);
			return mav;
		}
		Part part=null;
		try
		{		
			part=this.partDAO.getEntity(String.valueOf(id));
		}
		catch(DAOException de)
		{
			mav.getModelMap().put(MESSAGE,de.getMessage());
		}
		
		if(part!=null)
		{
			SPCPartsList spcPart=new SPCPartsList();
			spcPart.setPart(part);
			try
			{
				this.spcPartsListDAO.addEntity(spcPart);
			}
			catch(Exception e)
			{
				lOG.debug("Part already on the SPC list");
				mav.getModelMap().put(MESSAGE, "Part already on the SPC list");
			}
		}
		mav.setViewName("forward:"+PARTSEARCH_VIEW);
		return mav;
	}
	
	@RequestMapping("/SPC/removePart")
	public ModelAndView removePart(ModelAndView mav,
							@RequestParam(value=MODE_EDIT,required=true)  Integer id,
							HttpServletRequest request
							)
	{
		lOG.debug("ROLES:"+Roles.QC.toString());
		lOG.debug("ID:"+id);
		if(request.isUserInRole(Roles.QC.toString())||request.isUserInRole(Roles.MANAGER.toString()))
		{
			SPCPartsList spcPart=null;
			try
			{
				spcPart=spcPartsListDAO.getEntity(String.valueOf(id));
				lOG.debug(spcPart+" being deleted");
				spcPartsListDAO.deleteEntity(spcPart);
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE,de.getMessage());
			}
			
			
		}
		else
		{
			mav.getModelMap().put(MESSAGE,"User has no permission to delete SPC Measurements");
		}
		mav.setViewName("forward:/app/"+SPC_PARTLIST_VIEW);
		return mav;
		
	}
	
	@RequestMapping("/Dimensions")
	public ModelAndView getDimensionList(ModelAndView mav,HttpServletRequest request,@RequestParam(MODE_EDIT) Integer id)
	{
		//If user not in role QC then return to main menu
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User can't not add Parts to the SPCList");
			mav.setViewName(MAIN_VIEW);
			return  mav;
		}
		
		SPCPartsList spcPart=null;
		List<SPCMeasurement> dimensions=null;
		try
		{
			spcPart=spcPartsListDAO.getEntity(String.valueOf(id));
			dimensions=spcMeasurementDAO.findEntities("part.id", spcPart.getPart().getId());
		}
		catch(DAOException de)
		{
			mav.getModelMap().put(MESSAGE,de.getMessage());
			//If exception is raised return empty objects
			spcPart=new SPCPartsList();
			dimensions=new ArrayList<SPCMeasurement>();
		}
		
		mav.getModelMap().put(SPC_PART, spcPart);
		mav.getModelMap().put(DIMENSIONS, dimensions);
		mav.getModelMap().put(PART, spcPart.getPart());
		mav.setViewName(SPC_MEASUREMENT_VIEW);
		return mav;
	}
	
	@RequestMapping("/SPC/deActivate")
	public ModelAndView de_activate(HttpServletRequest request,ModelAndView mav,@RequestParam(SPC_PART) Integer spcPartid,@RequestParam(MODE_EDIT) Integer id)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		try
		{
			SPCMeasurement dimension=spcMeasurementDAO.getEntity(String.valueOf(id));
		
			if(dimension!=null)
			{
				boolean active=dimension.isActive();
				dimension.setActive(!active); //Flip the boolean
				spcMeasurementDAO.updateEntity(dimension);
			}
			lOG.debug(mav.getModelMap().get(PART));
			lOG.debug(mav.getModelMap().get(DIMENSIONS));
		}
		catch(DAOException de)
		{
			mav.getModel().put(MESSAGE,de.getMessage());
		}
		return getDimensionList(mav,request, spcPartid);
		
	}
	
	@RequestMapping("/SPC/addDimension")
	public ModelAndView addDimension(HttpServletRequest request,ModelAndView mav,@RequestParam(SPC_PART) Integer spcPartid,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(bindingResult.hasErrors())
		{
			lOG.debug("APLSpcController:/SPC/addDimension:BindingError:"+bindingResult.getAllErrors());
			mav.getModelMap().put(ERRORS, getErrors(bindingResult));
			
			//call to getDimensionList is required
		}
		else
		{
			try
			{
				SPCPartsList spclist=spcPartsListDAO.getEntity(String.valueOf(spcPartid));
				if(spclist!=null)
				{
				
					Part p=spclist.getPart();
					spcMeasurement.setPart(p);
					spcMeasurementDAO.addEntity(spcMeasurement);
				}
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE, "SPC Measurement was not updated. Error in application");
				lOG.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		}
		
		return getDimensionList(mav,request, spcPartid);
	}
	
	@RequestMapping("/SPC/editDimension")
	public ModelAndView editDimension(HttpServletRequest request,ModelAndView mav,@RequestParam(SPC_PART) Integer spcPartid,@RequestParam("DimensionId")Integer dimensionId,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(bindingResult.hasErrors())
		{
			mav.getModelMap().put(ERRORS, getErrors(bindingResult));
			lOG.debug("APLSpcController:/SPC/editDimension:BindingError:"+bindingResult.getAllErrors());
			return getDimensionList(mav,request, spcPartid);
		}
		else
		{
			try
			{
				SPCPartsList spclist=spcPartsListDAO.getEntity(String.valueOf(spcPartid));
				if(spclist!=null)
				{
					Part p=spclist.getPart();
					spcMeasurement.setPart(p);
					spcMeasurement.setId(dimensionId);
					spcMeasurementDAO.updateEntity(spcMeasurement);
				}
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE, "SPC Measurement was not updated. Error in application");
				lOG.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		}
		
		return getDimensionList(mav,request, spcPartid);
	}
	
	
	@RequestMapping("/SPC/deleteDimension")
	public ModelAndView removeDimension(
			HttpServletRequest request,
			ModelAndView mav,
			@RequestParam(SPC_PART) Integer spcPartid,
			@RequestParam(MODE_EDIT) Integer id)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put(MESSAGE, "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		
			try
			{
				SPCMeasurement measurement=spcMeasurementDAO.getEntity(String.valueOf(id));
				spcMeasurementDAO.deleteEntity(measurement);
			}
			catch(DAOException de)
			{
				mav.getModelMap().put(MESSAGE, "SPC Measurement was not deleted."+de.getMessage());
				lOG.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		lOG.debug("deleteDimension:"+mav.toString()+":"+request.toString()+":"+spcPartid);
		return getDimensionList(mav,request, spcPartid);
	}
	
	
	
	/**
	 * Spring injected resources
	 * @param spcListPartDAO
	 */
	@Resource(name="spcPartsListDAO")
	public void setSPCListPartDAO(DAO<SPCPartsList> spcListPartDAO)
	{
		this.spcPartsListDAO=spcListPartDAO;
		lOG.debug("spcPartsListDAO:"+this.spcPartsListDAO);
	}
	
	/**
	 * Spring injected resources
	 * @param partDAO
	 */
	@Resource(name="partDAO")
	public void setPartDAO(DAO<Part> partDAO)
	{
		this.partDAO=partDAO;
		lOG.debug("partDAO:"+this.partDAO);
	}
	
	/**
	 * Spring injected resources
	 * @param spcDimensionDAO
	 */
	@Resource(name="spcDimensionDAO")
	public void setSPCDimensionDAO(SPCMeasurementDAO spcDimensionDAO)
	{
		this.spcMeasurementDAO=spcDimensionDAO;
		lOG.debug("spcDimensionDAO:"+this.spcMeasurementDAO);
	}
	
	/**
	 * @deprecated To be removed
	 * @param result
	 * @return a Map of error messages
	 */
	@Deprecated
	private Map<String,String> getErrors(BindingResult result)
	{
		Map<String,String> errors=new HashMap<String,String>();
		
		
		FieldError e=result.getFieldError();
		errors.put(e.getField(),e.getCode());
		
		
		return errors;
	}
}
