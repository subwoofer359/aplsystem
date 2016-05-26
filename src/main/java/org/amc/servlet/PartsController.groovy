package org.amc.servlet

abstract class PartsController {
    static final String SEARCH = 'search';
    static final String ERRORS = 'errors';
    static final String SESSION_PARTSEARCH = 'PARTSEARCH';
    static final String COMPANY = 'company';
    static final String PARTNAME = 'partName';
    static final String QSS_NUMBER = 'qssNumber';
    static final String MODEL_ATTR_PARTS = 'parts';
    static final String VIEW_SEARCH_PAGE = 'PartsSearchPage';
    static final String VIEW_PART_PAGE = 'Part';
    static final String VIEW_MAIN_PAGE = 'Main';
    static final String SEARCH_PARSE_ERROR_MSG = 'Search Parameters couldn\'t be parsed';
    static final String EDIT_MODE = 'edit Part';
    static final String ERROR_PAGE_EDIT = 'Can\'t edit Part';
    static final String ERROR_DAO = 'Database not available: ${de.message}';
}
