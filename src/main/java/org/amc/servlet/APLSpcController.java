package org.amc.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCPartsList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
}
