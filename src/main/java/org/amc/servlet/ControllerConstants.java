package org.amc.servlet;

final class ControllerConstants 
{
	static final String FORM="form";
	static final String USERS="users";
	static final String USER="user";
	static final String PROCESS="process";
	static final String PROCESS_SHEETS="processSheets";
	static final String MATERIAL="material";
	static final String MATERIALS="materials";
	static final String PART="part";
	static final String PARTS="parts";
	
	
	static final String SEARCH="search";
	
	static final String MESSAGE="message";
	static final String ERRORS="errors";
	
	static final String MODE="mode";
	static final String MODE_ENTER="Enter";
	static final String MODE_ADD="add";
	static final String MODE_DELETE="delete";
	static final String MODE_EDIT="edit";
	
	
	
	static final String SPC_PART="spcPart";
	static final String DIMENSIONS="dimensions";
	static final String CURRENT_SPC_MEASUREMENT="CURRENT_SPC_MEASUREMENT";
	static final String SPC_MEASUREMENTS="spcmeasurements";
	
	//Controller paths
	static final String PARTSEARCH="/app/Part_search";
	static final String SPCLISTPARTS="/app/spc/SPCListParts";
	static final String USER_SEARCH="/app/user/Users";
	
	//Views
	static final String MAIN_VIEW="Main";
	static final String SPC_PARTLIST_VIEW="spc/SPCPartList";
	static final String SPC_MEASUREMENT_VIEW="spc/SPCMeasurement";
	static final String SPC_ENTRYPAGE_VIEW="spc/SPCEntryPage";
	static final String USER_SEARCH_VIEW="UsersSearchPage";
	static final String USER_EDIT_VIEW="UserAddOrEdit";
	static final String USER_INFO_VIEW="UserInfo";
	
	private ControllerConstants()
	{
		throw new AssertionError(ControllerConstants.class.getSimpleName()+"  is a utility class");
	}
}
