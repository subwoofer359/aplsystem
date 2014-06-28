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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.amc.Constants.Roles;

/**
 * 
 * @author Adrian McLaughlin
 * Controller for the SPC model and view
 *
 */

@Controller
public class APLSpcController
{
	private static Logger logger=Logger.getLogger(APLSpcController.class);
	private DAO<SPCPartsList> spcListPartDAO;
	private DAO<Part> partDAO;
	private SPCMeasurementDAO spcDimensionDAO;
	/**
	 * Retrieve and return SPC Part List
	 */
	@RequestMapping("/SPCPartsList")
	public ModelAndView getSPCPartList()
	{
		ModelAndView mav=new ModelAndView();
		logger.debug("spcPartsListDAO in getSPCPartList:"+this.spcListPartDAO);
		List<SPCPartsList> list=null;
		try
		{
			list=spcListPartDAO.findEntities();
		}
		catch(DAOException de)
		{
			mav.getModel().put("message",de.getMessage());
		}
		
		if(list==null)
		{
			list=new ArrayList<SPCPartsList>();
		}
		
		mav.getModel().put("parts", list);
		mav.setViewName("spc/SPCPartList");
		return mav;
	}
	
	@RequestMapping("/AddToSPC")
	public String addToSPC(@RequestParam("edit") Integer id,HttpServletRequest request)
	{
		//If User not in role QC then return with error message
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			request.setAttribute("message", "User can't not add Parts to the SPCList");
			return "forward:/Part_search";
		}
		Part part=null;
		try
		{
				
			part=this.partDAO.getEntity(String.valueOf(id));
		}
		catch(DAOException de)
		{
			request.setAttribute("message",de.getMessage());
		}
		
		if(part!=null)
		{
			SPCPartsList spcPart=new SPCPartsList();
			spcPart.setPart(part);
			try
			{
				this.spcListPartDAO.addEntity(spcPart);
			}
			catch(Exception e)
			{
				request.setAttribute("message", "Part already on the SPC list");
			}
		}
		return "forward:/Part_search";
	}
	
	@RequestMapping("/SPC/removePart")
	public String removePart(Model model,
							@RequestParam(value="edit",required=true)  Integer id,
							HttpServletRequest request
							)
	{
		logger.debug("ROLES:"+Roles.QC.toString());
		logger.debug("ID:"+id);
		if(request.isUserInRole(Roles.QC.toString())||request.isUserInRole(Roles.MANAGER.toString()))
		{
			SPCPartsList spcPart=null;
			try
			{
				spcPart=spcListPartDAO.getEntity(String.valueOf(id));
				logger.debug(spcPart+" being deleted");
				spcListPartDAO.deleteEntity(spcPart);
			}
			catch(DAOException de)
			{
				request.setAttribute("message",de.getMessage());
			}
			
			
		}
		
		return "forward:/spc/SPCPartsList";
		
	}
	
	@RequestMapping("/Dimensions")
	public ModelAndView getDimensionList(ModelAndView mav,HttpServletRequest request,@RequestParam("edit") Integer id)
	{
		//If user not in role QC then return to main menu
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User can't not add Parts to the SPCList");
			mav.setViewName("Main");
			return  mav;
		}
		
		SPCPartsList spcPart=null;
		List<SPCMeasurement> dimensions=null;
		try
		{
			spcPart=spcListPartDAO.getEntity(String.valueOf(id));
			dimensions=spcDimensionDAO.findEntities("part.id", spcPart.getPart().getId());
		}
		catch(DAOException de)
		{
			mav.getModelMap().put("message",de.getMessage());
			//If exception is raised return empty objects
			spcPart=new SPCPartsList();
			dimensions=new ArrayList<SPCMeasurement>();
		}
		
		mav.getModelMap().put("spcPart", spcPart);
		mav.getModelMap().put("dimensions", dimensions);
		mav.getModelMap().put("part", spcPart.getPart());
		mav.setViewName("spc/SPCMeasurement");
		return mav;
	}
	
	@RequestMapping("/SPC/deActivate")
	public ModelAndView de_activate(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@RequestParam("edit") Integer id)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		try
		{
			SPCMeasurement dimension=spcDimensionDAO.getEntity(String.valueOf(id));
		
			if(dimension!=null)
			{
				boolean active=dimension.isActive();
				dimension.setActive(!active); //Flip the boolean
				spcDimensionDAO.updateEntity(dimension);
			}
			logger.debug(mav.getModelMap().get("part"));
			logger.debug(mav.getModelMap().get("dimensions"));
		}
		catch(DAOException de)
		{
			mav.getModel().put("message",de.getMessage());
		}
		return getDimensionList(mav,request, spcPartid);
		
	}
	
	@RequestMapping("/SPC/addDimension")
	public ModelAndView addDimension(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(bindingResult.hasErrors())
		{
			logger.debug("APLSpcController:/SPC/addDimension:BindingError:"+bindingResult.getAllErrors());
			mav.getModelMap().put("errors", getErrors(bindingResult));
			
			//call to getDimensionList is required
		}
		else
		{
			try
			{
				SPCPartsList spclist=spcListPartDAO.getEntity(String.valueOf(spcPartid));
				if(spclist!=null)
				{
				
					Part p=spclist.getPart();
					spcMeasurement.setPart(p);
					spcDimensionDAO.addEntity(spcMeasurement);
				}
			}
			catch(DAOException de)
			{
				mav.getModelMap().put("message", "SPC Measurement was not updated. Error in application");
				logger.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		}
		
		return getDimensionList(mav,request, spcPartid);
	}
	
	@RequestMapping("/SPC/editDimension")
	public ModelAndView editDimension(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@RequestParam("DimensionId")Integer dimensionId,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(bindingResult.hasErrors())
		{
			mav.getModelMap().put("errors", getErrors(bindingResult));
			logger.debug("APLSpcController:/SPC/editDimension:BindingError:"+bindingResult.getAllErrors());
			return getDimensionList(mav,request, spcPartid);
		}
		else
		{
			try
			{
				SPCPartsList spclist=spcListPartDAO.getEntity(String.valueOf(spcPartid));
				if(spclist!=null)
				{
					Part p=spclist.getPart();
					spcMeasurement.setPart(p);
					spcMeasurement.setId(dimensionId);
					spcDimensionDAO.updateEntity(spcMeasurement);
				}
			}
			catch(DAOException de)
			{
				mav.getModelMap().put("message", "SPC Measurement was not updated. Error in application");
				logger.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		}
		
		return getDimensionList(mav,request, spcPartid);
	}
	
	
	@RequestMapping("/SPC/deleteDimension")
	public ModelAndView removeDimension(
			HttpServletRequest request,
			ModelAndView mav,
			@RequestParam("spcPart") Integer spcPartid,
			@RequestParam("edit") Integer id)
	{
		if(!request.isUserInRole(Roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User edit SPC definitions");
			return getDimensionList(mav,request, spcPartid);
		}
		
		
			try
			{
				SPCMeasurement measurement=(SPCMeasurement)spcDimensionDAO.getEntity(String.valueOf(id));
				spcDimensionDAO.deleteEntity(measurement);
			}
			catch(DAOException de)
			{
				mav.getModelMap().put("message", "SPC Measurement was not deleted."+de.getMessage());
				logger.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+de.getMessage());
				return getDimensionList(mav,request, spcPartid);
			}
		logger.debug("deleteDimension:"+mav.toString()+":"+request.toString()+":"+spcPartid);
		return getDimensionList(mav,request, spcPartid);
	}
	
	
	
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
	
	private Map<String,String> getErrors(BindingResult result)
	{
		Map<String,String> errors=new HashMap<String,String>();
		
		
		FieldError e=result.getFieldError();
		errors.put(e.getField(),e.getCode());
		
		
		return errors;
	}
}
