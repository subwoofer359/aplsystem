package org.amc.servlet.action;

import org.amc.servlet.dao.MaterialDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialActionFactoryImpl implements MaterialActionFactory
{

	private MaterialDAO materialDAO;
	
	@Autowired
	public MaterialActionFactoryImpl(MaterialDAO materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	@Override
	public SearchMaterialAction getSearchMaterialAction()
	{
		return new SearchMaterialAction(materialDAO);
	}

}
