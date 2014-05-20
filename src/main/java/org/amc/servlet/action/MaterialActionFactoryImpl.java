package org.amc.servlet.action;

import org.amc.dao.MaterialDAORemote;
import org.springframework.beans.factory.annotation.Autowired;

public class MaterialActionFactoryImpl implements MaterialActionFactory
{

	private MaterialDAORemote materialDAO;
	
	@Autowired
	public MaterialActionFactoryImpl(MaterialDAORemote materialDAO)
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
