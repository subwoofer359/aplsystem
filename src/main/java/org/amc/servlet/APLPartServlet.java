package org.amc.servlet;

import static org.amc.servlet.ControllerConstants.ERRORS;
import static org.amc.servlet.ControllerConstants.FORM;
import static org.amc.servlet.ControllerConstants.MODE;
import static org.amc.servlet.ControllerConstants.MODE_ADD;
import static org.amc.servlet.ControllerConstants.PARTS;
import static org.amc.servlet.ControllerConstants.SEARCH;

import org.amc.Constants;
import org.amc.DAOException;
import org.amc.model.Part;
import org.amc.servlet.action.PartActionFactory;
import org.amc.servlet.action.SavePartAction;
import org.amc.servlet.action.SearchPartAction;
import org.amc.servlet.action.search.PartSearch;
import org.amc.servlet.model.PartForm;
import org.amc.servlet.model.PartSearchForm;
import org.amc.servlet.validator.PartSearchFormValidator;
import org.amc.servlet.validator.Part_Validator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class APLSystemServlet
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
@WebServlet(description = "Dispatching Servlet for the Problem Database", urlPatterns = {
        "/app/APLSystemServlet", "/app/Part_display", "/app/Part_search", "/app/Part_save",
        // "/Problem_save",
        // "/Problem_display",
        // "/ProblemDescription_save",
        // "/ProblemDescription_display",
        // "/SearchProblemDatabase",
        "/app/logout" }, loadOnStartup = 1)
public class APLPartServlet extends HttpServlet {
    private static final long serialVersionUID = 334034039L;

    private PartActionFactory partActionFactory;

    private static Logger logger = Logger.getLogger(APLPartServlet.class);

    private static final String MODE_EDIT = "edit Part";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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

    /**
     * Handles incoming requests
     */

    private void process(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        String referal = request.getRequestURI();
        logger.debug(referal);

        // Handle Part Page
        if (referal.endsWith("Part_save")) {

            savePart(request, response);
        } else if (referal.endsWith("Part_display")) {
            displayPart(request, response);
        } else if (referal.endsWith("Part_search")) {
            searchForPart(request, response);
        }// To log out the current user
        else if (referal.endsWith("logout")) {
            logout(request, response);
        } else if (referal.endsWith("APLSystemServlet")) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Main.jsp");
            rd.forward(request, response);
        }

    }

    /**
     * Saves the Part to the Database
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void savePart(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        // check if page is in create or edit mode
        String mode = request.getParameter(MODE);
        logger.debug(String.format("mode:[%s]", mode));// debug
        // create form
        PartForm jForm = new PartForm();

        // If an ID parameter is passed add it to the form
        String id = request.getParameter("id");
        if (id != null) {
            jForm.setId(id);
        }
        jForm.setName(request.getParameter("name"));
        jForm.setColour(request.getParameter("colour"));
        // checkedbox
        String external = request.getParameter("external");
        if (external == null) {
            jForm.setExternal(false);
        } else {
            jForm.setExternal(true);
        }

        jForm.setPart_id(request.getParameter("part_id"));
        jForm.setQss_no(request.getParameter("qss_no"));
        jForm.setRevision(request.getParameter("revision"));
        jForm.setVersion(request.getParameter("version"));
        jForm.setCompany(request.getParameter("company"));

        // Validate Form
        Part_Validator validator = new Part_Validator();
        List<String> errors = validator.validate(jForm);

        // Check if user is a role to allow changes to the database
        if (!(request.isUserInRole(Constants.Roles.QC.toString()) || (request
                        .isUserInRole(Constants.Roles.MANAGER.toString())))) {
            errors.add("User has no permissions to alter Part definitions!");
        }

        // If form validates with no errors
        if (errors.isEmpty()) {

            // create model
            Part part = new Part();
            part.setCompany(jForm.getCompany());
            part.setName(jForm.getName());
            part.setColour(jForm.getColour());
            part.setExternal(jForm.isExternal());
            part.setQss_no(jForm.getQss_no());
            part.setRevision(jForm.getRevision());
            part.setVersion(jForm.getVersion());
            part.setPart_id(jForm.getPart_id());

            String dispatcherURL = "";
            SavePartAction action = partActionFactory.getSaveJobTemplateAction();
            try {
                // New Part to Database
                if (mode == null || "Enter".equals(mode)) {
                    action.save(part);
                    request.setAttribute(FORM, jForm);
                    request.setAttribute("result", part.toString() + " saved");
                    dispatcherURL = "/WEB-INF/JSP/Part.jsp";
                } else if ("Edit".equals(mode)) {
                    // Current Part is updated in the Database
                    part.setId(Integer.parseInt(jForm.getId()));
                    action.edit(part);
                    dispatcherURL = "Part_search";
                    response.sendRedirect(request.getContextPath() + "/app/Part_search"); // Goto
                                                                                          // the
                                                                                          // Search
                                                                                          // Window
                    return; // Exit function
                } else {
                    throw new ServletException("Form received can't be processed");
                }

                // request.removeAttribute(FORM);
                request.removeAttribute(ERRORS);
                RequestDispatcher rd = request.getRequestDispatcher(dispatcherURL);

                rd.forward(request, response);
            } catch (NumberFormatException se) {
                throw new ServletException(se);
            } catch (DAOException se) {

                throw (ServletException) new ServletException("Database not available:"
                                + se.getMessage()).initCause(se);
            }

        } else {
            // if the form doesn't validate without errors then
            request.setAttribute(ERRORS, errors);
            request.setAttribute(FORM, jForm);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Part.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * display a blank Part page
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void displayPart(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Part.jsp");
        rd.forward(request, response);
    }

    /**
     * Create SearchPage
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void searchForPart(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        // check to if in search or edit mode TODO add delete mode
        String mode = request.getParameter(MODE);
        // Passed if an entry is to be edited
        String idValue = request.getParameter("edit");

        // Debug
        logger.debug(String.format("mode:[%s] ID:[%s]%n", mode, idValue));

        // create an action
        SearchPartAction sjt = this.partActionFactory.getSearchJobTemplateAction();
        String dispatchURL = null;
        try {
            // if the page is to do a search
            if (mode == null || SEARCH.equals(mode)) {

                List<Part> list = null;

                PartSearchForm partSearchForm = new PartSearchForm();
                partSearchForm.setCompany(request.getParameter("company"));
                partSearchForm.setPartName(request.getParameter("partName"));
                partSearchForm.setQSSNumber(request.getParameter("QSSNumber"));

                PartSearchFormValidator validator = new PartSearchFormValidator();

                validator.validate(partSearchForm);

                if (validator.hasErrors()) {
                    request.setAttribute(ControllerConstants.MESSAGE, validator.getErrors());
                } else {
                    try {
                        PartSearch partSearch = null;
                        if (partSearchForm.isEmpty()) {
                            synchronized (request.getSession()) {
                                if (request.getSession().getAttribute("PARTSEARCH") == null) {
                                    list = new ArrayList<Part>();
                                } else {
                                    partSearch = (PartSearch) request.getSession().getAttribute(
                                                    "PARTSEARCH");
                                    list = sjt.search(partSearch);
                                }
                            }
                        } else {
                            partSearch = PartSearchFormValidator.PartSearchBinder
                                            .getPartSearch(partSearchForm);
                            list = sjt.search(partSearch);
                        }

                        synchronized (request.getSession()) {
                            request.getSession().setAttribute("PARTSEARCH", partSearch);
                        }

                    } catch (ParseException pe) {
                        request.setAttribute(ControllerConstants.MESSAGE,
                                        "Search Parameters couldn't be parsed");
                        list = new ArrayList<Part>();
                    }
                    logger.debug(String.format("%d results returned %n", list.size()));
                }

                request.setAttribute(PARTS, list);

                dispatchURL = "/WEB-INF/JSP/PartsSearchPage.jsp";

            } else if (mode != null) {
                if (MODE_ADD.equals(mode) || idValue == null) // idValue will
                                                              // equal null if
                                                              // the checked box
                                                              // isn't selected
                {
                    // open the Part JSPage in add mode
                    dispatchURL = "/WEB-INF/JSP/Part.jsp";
                } else if (MODE_EDIT.equals(mode) && idValue != null) {
                    // open the Part JSPage in edit mode
                    Part part = sjt.getPart(idValue);
                    dispatchURL = "/WEB-INF/JSP/Part.jsp";
                    request.setAttribute(FORM, part);
                    request.setAttribute(MODE, MODE_EDIT);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
            rd.forward(request, response);
        } catch (DAOException se) {

            se.printStackTrace();
            throw (ServletException) new ServletException("Database not available:"
                            + se.getMessage()).initCause(se);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.logout();

        response.sendRedirect(getServletContext().getContextPath() + "/");
    }

    public void setPartActionFactory(PartActionFactory partActionFactory) {
        this.partActionFactory = partActionFactory;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context2 = (ApplicationContext) getServletContext().getAttribute(
                        "org.springframework.web.context.WebApplicationContext.ROOT");
        setPartActionFactory((PartActionFactory) context2.getBean("partActionFactory"));

    }
}
