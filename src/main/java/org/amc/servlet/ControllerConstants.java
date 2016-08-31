package org.amc.servlet;

final class ControllerConstants {
    public static final String FORM = "form";
    public static final String USERS = "users";
    public static final String USER = "user";
    public static final String PROCESS = "process";
    public static final String PROCESS_SHEETS = "processSheets";
    public static final String MATERIAL = "material";
    public static final String MATERIALS = "materials";
    public static final String PART = "part";
    public static final String PARTS = "parts";

    public static final String SEARCH = "search";

    public static final String MESSAGE = "message";
    public static final String ERRORS = "errors";

    public static final String MODE = "mode";
    public static final String MODE_ENTER = "Enter";
    public static final String MODE_ADD = "add";
    public static final String MODE_DELETE = "delete";
    public static final String MODE_EDIT = "edit";

    public static final String SPC_PART = "spcPart";
    public static final String DIMENSIONS = "dimensions";
    public static final String CURRENT_SPC_MEASUREMENT = "CURRENT_SPC_MEASUREMENT";
    public static final String SPC_MEASUREMENTS = "spcmeasurements";

    // Controller paths
    public static final String PARTSEARCH = "/app/Part_search";
    public static final String SPCLISTPARTS = "/app/spc/SPCListParts";
    public static final String USER_SEARCH = "/app/user/Users";

    // Views
    public static final String MAIN_VIEW = "Main";
    public static final String SPC_PARTLIST_VIEW = "spc/SPCPartList";
    public static final String SPC_MEASUREMENT_VIEW = "spc/SPCMeasurement";
    public static final String SPC_ENTRYPAGE_VIEW = "spc/SPCEntryPage";
    public static final String USER_SEARCH_VIEW = "UsersSearchPage";
    public static final String USER_EDIT_VIEW = "UserAddOrEdit";
    public static final String USER_INFO_VIEW = "UserInfo";

    private ControllerConstants() {
        throw new AssertionError(ControllerConstants.class.getSimpleName() + "  is a utility class");
    }
}
