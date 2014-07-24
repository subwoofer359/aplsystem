package org.amc.servlet;

class ControllerConstants 
{
	
	static final String USERS="users";
	static final String USER="user";
	static final String PROCESS="process";
	static final String MATERIAL="material";
	static final String MATERIALS="materials";
	
	static final String SEARCH="search";
	
	static final String MESSAGE="message";
	static final String ERRORS="errors";
	
	static final String MODE="mode";
	static final String MODE_ENTER="Enter";
	static final String MODE_ADD="add";
	static final String MODE_DELETE="delete";
	static final String MODE_EDIT="edit";
	
	static final String PART="part";
	static final String PARTS="parts";
	
	static final String SPC_PART="spcPart";
	static final String DIMENSIONS="dimensions";
	
	//Views
	static final String MAIN_VIEW="Main";
	static final String PARTSEARCH_VIEW="/app/Part_search";
	
	private ControllerConstants()
	{
		throw new AssertionError(ControllerConstants.class.getSimpleName()+"  is a utility class");
	}
}
