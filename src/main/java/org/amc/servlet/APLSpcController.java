package org.amc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCMeasurement;
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.validator.SPCMeasurementValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.amc.Constants.roles;

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
		List<SPCPartsList> list=spcListPartDAO.findEntities();
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
		if(!request.isUserInRole(roles.QC.toString()))
		{
			request.setAttribute("message", "User can't not add Parts to the SPCList");
			return "forward:/Part_search";
		}
		Part part=this.partDAO.getEntity(String.valueOf(id));
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
		logger.debug("ROLES:"+roles.QC.toString());
		logger.debug("ID:"+id);
		if(request.isUserInRole(roles.QC.toString())||request.isUserInRole(roles.MANAGER.toString()))
		{
			SPCPartsList spcPart=spcListPartDAO.getEntity(String.valueOf(id));
			logger.debug(spcPart+" being deleted");
			spcListPartDAO.deleteEntity(spcPart);
			
		}
		
		return "forward:/spc/SPCPartsList";
		
	}
	
	@RequestMapping("/Dimensions")
	public ModelAndView getDimensionList(HttpServletRequest request,@RequestParam("edit") Integer id)
	{
		ModelAndView mav=new ModelAndView();
		//If user not in role QC then return to main menu
		if(!request.isUserInRole(roles.QC.toString()))
		{
			mav.getModelMap().put("message", "User can't not add Parts to the SPCList");
			mav.setViewName("Main");
			return  mav;
		}
		SPCPartsList spcPart=spcListPartDAO.getEntity(String.valueOf(id));
		List<SPCMeasurement> dimensions=spcDimensionDAO.findEntities("part.id", spcPart.getPart().getId());
		mav.getModelMap().put("spcPart", spcPart);
		mav.getModelMap().put("dimensions", dimensions);
		mav.getModelMap().put("part", spcPart.getPart());
		mav.setViewName("spc/SPCMeasurement");
		return mav;
	}
	
	@RequestMapping("/SPC/deActivate")
	public ModelAndView de_activate(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@RequestParam("edit") Integer id)
	{
		if(!request.isUserInRole(roles.QC.toString()))
		{
			request.setAttribute("message", "User edit SPC definitions");
			return getDimensionList(request, spcPartid);
		}
		SPCMeasurement dimension=spcDimensionDAO.getEntity(String.valueOf(id));
		
		if(dimension!=null)
		{
			boolean active=dimension.isActive();
			dimension.setActive(!active); //Flip the boolean
			spcDimensionDAO.updateEntity(dimension);
		}
		logger.debug(mav.getModelMap().get("part"));
		logger.debug(mav.getModelMap().get("dimensions"));
		return getDimensionList(request, spcPartid);
		
	}
	
	@RequestMapping("/SPC/addDimension")
	public ModelAndView addDimension(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(roles.QC.toString()))
		{
			request.setAttribute("message", "User edit SPC definitions");
			return getDimensionList(request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(!bindingResult.hasErrors())
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
			catch(PersistenceException pe)
			{
				request.setAttribute("message", "SPC Measurement was not updated. Error in application");
				logger.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+pe.getMessage());
				return getDimensionList(request, spcPartid);
			}
		}
		else
		{
			logger.debug("APLSpcController:/SPC/addDimension:BindingError:"+bindingResult.getAllErrors());
			request.setAttribute("errors", bindingResult.getAllErrors());
		}
		
		return getDimensionList(request, spcPartid);
	}
	
	@RequestMapping("/SPC/editDimension")
	public ModelAndView editDimension(HttpServletRequest request,ModelAndView mav,@RequestParam("spcPart") Integer spcPartid,@RequestParam("DimensionId")Integer dimensionId,@ModelAttribute SPCMeasurement spcMeasurement,BindingResult bindingResult)
	{
		if(!request.isUserInRole(roles.QC.toString()))
		{
			request.setAttribute("message", "User edit SPC definitions");
			return getDimensionList(request, spcPartid);
		}
		
		//Valid SPCMeasurement
		Validator v=new SPCMeasurementValidator();
		v.validate(spcMeasurement, bindingResult);
		
		if(!bindingResult.hasErrors())
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
			catch(PersistenceException pe)
			{
				request.setAttribute("message", "SPC Measurement was not updated. Error in application");
				logger.error("APLSpcController:Call to "+SPCMeasurementDAO.class.getSimpleName()+" has cause an exception:"+pe.getMessage());
				return getDimensionList(request, spcPartid);
			}
		}
		else
		{
			logger.debug("APLSpcController:/SPC/editDimension:BindingError:"+bindingResult.getAllErrors());
		}
		
		return getDimensionList(request, spcPartid);
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
}
