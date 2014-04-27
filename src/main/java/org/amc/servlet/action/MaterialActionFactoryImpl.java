package org.amc.servlet.action;

import org.amc.dao.MaterialDAOBeanRemote;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialActionFactoryImpl implements MaterialActionFactory
{

	private MaterialDAOBeanRemote materialDAO;
	
	@Autowired
	public MaterialActionFactoryImpl(MaterialDAOBeanRemote materialDAO)
	{
		this.materialDAO=materialDAO;
	}
	@Override
	public SearchMaterialAction getSearchMaterialAction()
	{
		return new SearchMaterialAction(materialDAO);
	}

	public SaveMaterialAction getSaveMaterialAction()
	{
		return new SaveMaterialAction(materialDAO);
	}
}
