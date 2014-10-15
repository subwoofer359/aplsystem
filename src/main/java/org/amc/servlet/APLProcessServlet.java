package org.amc.servlet;

import java.io.IOException;

import org.amc.Constants;
import org.amc.Constants.Roles;
import org.amc.DAOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amc.servlet.action.MaterialActionFactory;
import org.amc.servlet.action.ProcessActionFactory;
import org.amc.servlet.action.SaveProcessSheetAction;
import org.amc.servlet.action.SearchProcessSheetAction;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.model.Material;
import org.amc.model.MouldingProcess;
import org.amc.servlet.model.MouldingProcessForm;
import org.amc.servlet.model.MouldingProcessSearchForm;
import org.amc.servlet.validator.MouldingProcessSearchFormValidator;
import org.amc.servlet.validator.ProcessForm_Validator;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.MATERIALS;
import static org.amc.servlet.ControllerConstants.MODE;
import static org.amc.servlet.ControllerConstants.MODE_ADD;
import static org.amc.servlet.ControllerConstants.MODE_EDIT;
import static org.amc.servlet.ControllerConstants.MODE_ENTER;
import static org.amc.servlet.ControllerConstants.SEARCH;
import static org.amc.servlet.ControllerConstants.MESSAGE;
import static org.amc.servlet.ControllerConstants.FORM;
import static org.amc.servlet.ControllerConstants.PROCESS_SHEETS;

/**
 * Servlet implementation class APLProcessServlet
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@WebServlet(description = "To serve up Process Sheet Data", urlPatterns = {
        "/app/APLProcessServlet", "/app/ProcessSheet_display", "/app/ProcessSheet_search",
        "/app/ProcessSheet_save", "/app/ProcessSheet_analysis" }, loadOnStartup = 2)
public class APLProcessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(APLProcessServlet.class);

    private static final String SESSION_PROCESS_SHEET_SEARCH = "PROCESSSEARCH";

    private ProcessActionFactory processActionFactory;

    private MaterialActionFactory materialActionFactory;

    // Views
    private static final String MAIN_JSP = "/WEB-INF/JSP/Main.jsp";
    private static final String PROCESS_PAGE_JSP = "/WEB-INF/JSP/ProcessPage.jsp";
    private static final String DISPLAY_PAGE_JSP = "/WEB-INF/JSP/DisplayProcess.jsp";
    private static final String PROCESS_SEARCH_JSP = "/WEB-INF/JSP/ProcessSheetSearchPage.jsp";

    // Controllers
    private static final String PROCESS_SEARCH = "/app/ProcessSheet_search";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        process(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        String referal = request.getRequestURI();
        LOG.debug(referal);

        // Handle JobTemplate Page
        if (referal.endsWith("ProcessSheet_save")) {
            saveProcessSheet(request, response);
        } else if (referal.endsWith("ProcessSheet_display")) {
            displayProcessSheet(request, response);
        } else if (referal.endsWith("ProcessSheet_search")) {
            searchProcessSheets(request, response);
        } else if (referal.endsWith("APLProcessServlet")) {
            RequestDispatcher rd = request.getRequestDispatcher(MAIN_JSP);
            rd.forward(request, response);
        }
    }

    private void saveProcessSheet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        LOG.debug(String.format("Context Path:" + request.getContextPath()));
        // check if page is in create or edit mode
        String mode = request.getParameter(MODE);
        LOG.debug(String.format("SaveProcessSheet:mode:[%s]%n", mode));// debug
        LOG.debug(String.format("SaveProcessSheet:" + request.getParameter("dateOfIssue")));
        // create form

        // If an ID parameter is passed add it to the form
        MouldingProcessForm jForm = (MouldingProcessForm) request.getAttribute("processSheet");
        LOG.debug(String.format("\nSubmitted Process:" + jForm));

        // Validate Form
        ProcessForm_Validator validator = new ProcessForm_Validator();
        List<String> errors = validator.validate(jForm);

        // Check if user is a role to allow changes to the database
        if (!(request.isUserInRole(Roles.QC.toString()) || (request.isUserInRole(Roles.MANAGER
                        .toString())))) {
            errors.add("User has no permissions to alter Process sheet definitions!");
        }

        // If form validates with no errors
        if (errors.isEmpty()) {

            // create model
            MouldingProcess processSheet;

            // Default dispatch URL
            // String dispatcherURL="/ProcessSheet_search";

            SaveProcessSheetAction action = processActionFactory.getSaveProcessSheetAction();
            try {
                processSheet = MouldingProcessForm.getMouldingProcess(jForm);
                // New JobTemplate to Database
                if (mode == null || MODE_ENTER.equals(mode)) {
                    LOG.debug(String.format("SaveProcessSheet:Entering entry into database"));
                    action.save(processSheet);
                    response.sendRedirect(request.getContextPath() + PROCESS_SEARCH); // Goto
                                                                                      // the
                                                                                      // Search
                                                                                      // Window
                    return; // Exit function
                } else if (MODE_EDIT.equals(mode)) {
                    LOG.debug(String.format("SaveProcessSheet:Editing entry into database"));
                    // Current JobTemplate is updated in the Database
                    // processSheet.setId(Integer.parseInt(jForm.getId()));
                    processSheet.setId(Integer.parseInt(jForm.getId()));
                    action.edit(processSheet);
                    response.sendRedirect(request.getContextPath() + PROCESS_SEARCH); // Goto
                                                                                      // the
                                                                                      // Search
                                                                                      // Window
                    return; // Exit function
                } else {
                    throw new ServletException("Form received can't be processed");
                }

            } catch (NumberFormatException se) {
                throw new ServletException(se);
            } catch (DAOException se) {

                throw new ServletException(se);
            } catch (Exception e) {
                throw new ServletException(e);
            }

        } else {
            try {
                // if the form doesn't validate without errors then
                // Remember page is in edit mode
                if (MODE_EDIT.equals(mode)) {
                    request.setAttribute(MODE, mode);
                }
                // Get List of Material
                Map<Integer, Material> materials = materialActionFactory.getSearchMaterialAction()
                                .search();

                request.setAttribute(MATERIALS, materials);
                request.setAttribute(ERRORS, errors);
                request.setAttribute(FORM, jForm);

                RequestDispatcher rd = request.getRequestDispatcher(PROCESS_PAGE_JSP);
                rd.forward(request, response);
            } catch (DAOException sqle) {
                throw new ServletException(sqle);
            }
        }
    }

    private void displayProcessSheet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        // Selected Process Page
        String idValue = request.getParameter(MODE_EDIT);
        if (idValue == null || ("".equals(idValue))) {
            // No Process selected return to ProcessSearchPage
            response.sendRedirect(request.getContextPath() + PROCESS_SEARCH);
        } else {
            try {
                SearchProcessSheetAction spt = processActionFactory.getSearchProcessSheetAction();
                MouldingProcess process = spt.getMouldingProcess(idValue);
                request.setAttribute("process", process);
                RequestDispatcher rd = request.getRequestDispatcher(DISPLAY_PAGE_JSP);

                // Get List of Material and add to the request for
                // DisplayProcess.jsp to use.
                Map<Integer, Material> materials = materialActionFactory.getSearchMaterialAction()
                                .search();
                request.setAttribute(MATERIALS, materials);

                rd.forward(request, response);
            } catch (DAOException e) {
                getServletContext().log(e.getMessage());
                e.printStackTrace();
                throw (ServletException) new ServletException("Database not available")
                                .initCause(e);
            }
        }
    }

    private void searchProcessSheets(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        // check to if in search or edit mode TODO add delete mode
        String mode = request.getParameter(MODE);
        // Passed if an entry is to be edited
        String idValue = request.getParameter(MODE_EDIT);

        // Debug
        LOG.debug(String.format("searchProcessSheets:mode:[%s] ID:[%s]%n", mode, idValue));

        // create an action
        SearchProcessSheetAction spt = processActionFactory.getSearchProcessSheetAction();
        String dispatchURL = null;
        try {
            List<MouldingProcess> list = null;
            // if the page is to do a search
            if (mode == null || SEARCH.equals(mode)) {

                MouldingProcessSearchForm mpform = new MouldingProcessSearchForm();

                mpform.setPartId(request.getParameter("partId"));
                mpform.setMachineNo(request.getParameter("machineNo"));
                mpform.setMasterbatchNo(request.getParameter("masterBatchNo"));
                mpform.setMaterial(request.getParameter("material"));
                mpform.setSignedOffBy(request.getParameter("signedOffBy"));
                mpform.setStartDate(request.getParameter("startDate"));
                mpform.setEndDate(request.getParameter("endDate"));

                LOG.debug("FORM:" + mpform);

                MouldingProcessSearchFormValidator validator = new MouldingProcessSearchFormValidator();

                validator.validate(mpform);

                if (validator.hasErrors()) {
                    LOG.debug("Form has errors");
                    LOG.debug(validator.getErrors());
                    request.setAttribute(MESSAGE, validator.getErrors());
                } else {
                    try {
                        if (mpform.isEmpty()) {
                            LOG.debug("FORM is empty");
                            synchronized (request.getSession()) {
                                if (request.getSession().getAttribute(SESSION_PROCESS_SHEET_SEARCH) == null) {
                                    list = new ArrayList<MouldingProcess>();
                                } else {
                                    MouldingProcessSearch mouldingProcessSearch = (MouldingProcessSearch) request
                                                    .getSession().getAttribute(
                                                                    SESSION_PROCESS_SHEET_SEARCH);
                                    LOG.debug(mouldingProcessSearch);
                                    list = spt.search(mouldingProcessSearch);
                                }
                            }
                        } else {
                            MouldingProcessSearch mouldingProcessSearch = MouldingProcessSearchFormValidator.MouldingProcessSearchBinder
                                            .getMouldingProcessSearch(mpform);
                            LOG.debug(mouldingProcessSearch);
                            list = spt.search(mouldingProcessSearch);
                            synchronized (request.getSession()) {
                                request.getSession().setAttribute(SESSION_PROCESS_SHEET_SEARCH,
                                                mouldingProcessSearch);
                            }
                        }
                    } catch (ParseException pe) {
                        request.setAttribute(ControllerConstants.MESSAGE,
                                        "Search Parameters couldn't be parsed");
                        list = new ArrayList<MouldingProcess>();
                    }
                    LOG.debug(String.format("%d results returned %n", list.size()));
                }

                request.setAttribute(PROCESS_SHEETS, list);

                dispatchURL = PROCESS_SEARCH_JSP;

            } else if (mode != null) {
                if (MODE_ADD.equals(mode) || idValue == null) // idValue will
                                                              // equal null if
                                                              // the checked box
                                                              // isn't selected
                {
                    LOG.debug(String.format("searchProcessSheets:Opening ProcessPage.jsp"));
                    // open the JobTemplate JSPage in add mode
                    MouldingProcess process = new MouldingProcess();
                    request.setAttribute(FORM, process);
                    dispatchURL = PROCESS_PAGE_JSP;
                } else if (MODE_EDIT.equals(mode) && idValue != null) {
                    // open the JobTemplate JSPage in edit mode
                    LOG.debug(String.format("searchProcessSheets:Opening ProcessPage.jsp in edit mode"));
                    MouldingProcess process = spt.getMouldingProcess(idValue);
                    dispatchURL = PROCESS_PAGE_JSP;
                    request.setAttribute(FORM, process);
                    request.setAttribute(MODE, MODE_EDIT);
                }
            }
            // Get List of Material
            Map<Integer, Material> materials = materialActionFactory.getSearchMaterialAction()
                            .search();

            request.setAttribute(MATERIALS, materials);
            LOG.debug(String.format("Materials:" + materials));

            RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
            rd.forward(request, response);
        } catch (org.amc.DAOException se) {
            se.printStackTrace();
            throw (ServletException) new ServletException("Database not available:"
                            + se.getMessage()).initCause(se);
        }

    }

    /*
     * Required by Spring
     */

    public void setProcessActionFactory(ProcessActionFactory processActionFactory) {
        this.processActionFactory = processActionFactory;
    }

    public void setMaterialActionFactory(MaterialActionFactory materialActionFactory) {
        this.materialActionFactory = materialActionFactory;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context2 = (WebApplicationContext) getServletContext().getAttribute(
                        Constants.SPRING_WEBAPPCONTEXT);
        setProcessActionFactory((ProcessActionFactory) context2.getBean("processActionFactory"));
        setMaterialActionFactory((MaterialActionFactory) context2.getBean("materialActionFactory"));
    }
}
