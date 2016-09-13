package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.amc.dao.DAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.amc.servlet.APLUserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.*;
import org.amc.Constants;
import org.amc.Constants.Roles;
import org.amc.DAOException;

/**
 *
 * @author Adrian Mclaughlin
 *
 */
public class TestAPLUserController {
    
    @Mock
    private DAO<User> dao;

    // Availible roles
    private static String[] ROLES = { Roles.MANAGER.toString(), Roles.GUEST.toString(), "user",
            Roles.QC.toString() };
    // Test Fixture
    private APLUserController userServlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userServlet = new APLUserController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUserPage() {
        String result = userServlet.getUserPage();
        assertEquals("UserInfo", result);
    }

    private User getTestUser(String username, String fullname) {
        User u1 = new User();
        u1.setFullName(fullname);
        u1.setUserName(username);
        u1.setEmailAddress("adrian@adrianmclaughlin.ie");
        u1.setPassword("84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec");
        return u1;
    }

    /**
     * Should return a ModelAndView object with a viewname="UsersSearchPage" and
     * contain a List of Users
     */
    @Test
    public void testGetUsersPage() throws DAOException {
        // A User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");

        // List of Users
        List<User> list = new ArrayList<User>();
        list.add(u1);
        // Mock object to return this list of objects
        when(dao.findEntities()).thenReturn(list);
        userServlet.setUserDAO(dao);

        // Empty model to pass to getUsersPage
        ModelAndView model = new ModelAndView();

        // MockHttpServletRequest required for the isUserInRole method
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");

        ModelAndView returnedMav = userServlet.getUsersPage(model, request);

        // Assert the method has returned the correct model attribute and view
        ModelAndViewAssert.assertModelAttributeValue(returnedMav, "users", list);
        ModelAndViewAssert.assertViewName(returnedMav, "UsersSearchPage");

    }

    /**
     * Test to make sure that if a user isn't in role 'manager' they are
     * redirected to the Main page
     */
    @Test
    public void testSaveUserNotInRole() throws DAOException {
        // Request parameters
        String[] roles = { ROLES[0], ROLES[1], ROLES[2] };
        String mode = "add";// Save new user mode
        String active = "true";
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");

        UserRolesDAO roleDao = mock(UserRolesDAO.class);
        when(roleDao.getEntities(u1)).thenReturn(getUserRoles(2, u1));
        when(dao.getEntity(anyInt())).thenReturn(u1);
        // Inject DAOs
        userServlet.setUserDAO(dao);
        userServlet.setUserRolesDAO(roleDao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("guest");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        Model m = mock(Model.class);

        String returnedResult = userServlet.saveUser(m, u1, result, mode, active, roles, request);

        assertEquals("Main", returnedResult);
    }

    private List<UserRoles> getUserRoles(int number, User user) {

        List<UserRoles> roles = new ArrayList<UserRoles>();

        // Return the least of the requested size or size of ROLES array
        number = Math.min(number, ROLES.length);

        for (int i = 0; i < number; i++) {
            UserRoles userRole = new UserRoles();
            userRole.setRoleName(ROLES[i]);
            userRole.setUser(user);
            roles.add(userRole);
        }
        return roles;
    }

    /**
     * Preconditions: mode="add" User and UserRoles objects exist in database
     * BindResult returns with no errors
     */
    @Test
    public void testSaveNewUser() throws DAOException {
        // Case one: User has currently no roles but 3 new roles are selected
        // Request parameters
        String[] roles = { ROLES[0], ROLES[1], ROLES[2] };
        String mode = "add";// Save new user mode
        String active = "true";
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");
        u1.setPassword(Constants.PASSWORD_DEFAULT);

        UserRolesDAO roleDao = mock(UserRolesDAO.class);
        when(roleDao.getEntities(u1)).thenReturn(getUserRoles(2, u1));
        // when(dao.getEntity(anyString())).thenReturn(u1);
        // Inject DAOs
        userServlet.setUserDAO(dao);
        userServlet.setUserRolesDAO(roleDao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        
        Model m = mock(Model.class);

        
        String returnedResult = userServlet.saveUser(m, u1, result, mode, active, roles, request);

        // Verify add user was called on UserDao object
        verify(dao).addEntity(any(User.class));
        // Verify the correct view String was returned.
        assertEquals("forward:/app/user/Users", returnedResult);

        // Verify 3 roles were create for the user
        List<UserRoles> finalRoles = u1.getRoles();
        assertEquals(finalRoles.size(), roles.length);
        
        reset(dao);
        // Case 2: User has current 2 Roles but has no roles selected in page
        roles = new String[] {};

        // when(dao.getEntity(anyString())).thenReturn(u1);
        userServlet.setUserDAO(dao);
        returnedResult = userServlet.saveUser(m, u1, result, mode, active, roles, request);
        // Verify add user was called on UserDao object
        verify(dao).addEntity(any(User.class));
        // Verify the correct view String was returned.
        assertEquals("forward:/app/user/Users", returnedResult);

        // The user should now have no roles
        finalRoles = u1.getRoles();
        assertEquals(finalRoles.size(), roles.length);

    }

    /**
     * Preconditions: mode="edit" User and UserRoles objects exist in database
     * BindResult returns with no errors
     */
    @Test
    public void testSaveEditedUser() throws DAOException {
        // Request parameters
        String[] roles = { ROLES[0], ROLES[1], ROLES[2] };
        String mode = "edit";// Save new user mode
        String active = "true";
        String PASSWORD_DEFAULT = Constants.PASSWORD_DEFAULT;
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");
        String oldPassword = u1.getPassword();
        u1.setPassword(PASSWORD_DEFAULT);
        // Create DAO objects
        
        UserRolesDAO roleDao = mock(UserRolesDAO.class);
        when(roleDao.getEntities(u1)).thenReturn(getUserRoles(2, u1));
        when(dao.getEntity(anyInt())).thenReturn(getTestUser("adrian", "Adrian McLaughlin"));
        // Inject DAOs
        userServlet.setUserDAO(dao);
        userServlet.setUserRolesDAO(roleDao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        Model m = mock(Model.class);

        String returnedResult = userServlet.saveUser(m, u1, result, mode, active, roles, request);

        verify(dao).updateEntity(any(User.class));

        assertTrue(u1.getPassword().equals(oldPassword));
        // Verify the correct view String was returned.
        assertEquals("forward:/app/user/Users", returnedResult);

        // The user should now have no roles
        List<UserRoles> finalRoles = u1.getRoles();
        assertEquals(finalRoles.size(), roles.length);
    }

    /**
     * Preconditions: mode="edit" User and UserRoles objects exist in database
     * BindResult returns with errors
     */
    @Test
    public void testSaveEditedUserWithValidationErrors() throws DAOException {
        // Request parameters
        String[] roles = { ROLES[0], ROLES[1], ROLES[2] };
        String mode = "edit";// Save new user mode
        String active = "true";
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");


        UserRolesDAO roleDao = mock(UserRolesDAO.class);
        when(roleDao.getEntities(u1)).thenReturn(getUserRoles(2, u1));
        when(dao.getEntity(anyInt())).thenReturn(u1);
        // Inject DAOs
        userServlet.setUserDAO(dao);
        userServlet.setUserRolesDAO(roleDao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");
        BindingResult result = mock(BindingResult.class);
        // result has errors
        when(result.hasErrors()).thenReturn(true);

        Model model = mock(Model.class);

        String returnedResult = userServlet.saveUser(model, u1, result, mode, active, roles,
                        request);
        verify(model).addAttribute(eq("errors"), any(List.class));
        verify(model).addAttribute(eq("user"), any(User.class));
        // Verify the correct view String was returned.
        assertEquals("UserAddOrEdit", returnedResult);

    }

    /**
     * Test to see if delete user succeeds and the user is directed to the
     * correct page
     */
    @Test
    public void testDeleteUser() throws DAOException {
        String mode = "delete";// delete user mode
        int id = 1;
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");

        when(dao.getEntity(anyInt())).thenReturn(u1);
        // Inject DAOs
        userServlet.setUserDAO(dao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");

        ModelAndView model = mock(ModelAndView.class);

        ModelAndView mav = userServlet.editUsers(mode, id, model, request);

        verify(dao).deleteEntity(any(User.class));
        // Verify the correct view String was returned.
        ModelAndViewAssert.assertViewName(mav, "UsersSearchPage");

    }

    /**
     * Test that the user directed to the User edit page
     */
    @Test
    public void testEditUsers() throws DAOException {
        String mode = "edit";// delete user mode
        int id = 1;
        // Create User
        User u1 = getTestUser("adrian", "Adrian McLaughlin");

        // Create DAO objects
        when(dao.getEntity(anyInt())).thenReturn(u1);
        // Inject DAOs
        userServlet.setUserDAO(dao);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addUserRole("manager");

        ModelAndView modelView = new ModelAndView();

        ModelAndView mav = userServlet.editUsers(mode, id, modelView, request);

        // verify UserDAO method getUser was called
        verify(dao).getEntity(anyInt());
        // Verify the correct view String was returned and Model attribute
        // "user" was set.
        ModelAndViewAssert.assertViewName(mav, "UserAddOrEdit");
        ModelAndViewAssert.assertModelAttributeAvailable(mav, "user");
    }

    @Test
    public void testUserNotAvailable() throws DAOException, Exception {
        final String existingUsername = "adrian";
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        List<User> users = new ArrayList<User>();

        users.add(new User());
        this.userServlet.setUserDAO(dao);

        when(dao.findEntities(anyString(), anyString())).thenReturn(users);
        when(response.getWriter()).thenReturn(writer);

        this.userServlet.isUserNameAvailable(existingUsername, response);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(writer).println(argument.capture());

        assertEquals(argument.getValue(), String.valueOf(Boolean.FALSE));

    }

    @Test
    public void testUserIsAvailable() throws DAOException, Exception {
        final String username = "nonusername";
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        List<User> users = new ArrayList<User>();

        this.userServlet.setUserDAO(dao);

        when(dao.findEntities(anyString(), anyString())).thenReturn(users);
        when(response.getWriter()).thenReturn(writer);

        this.userServlet.isUserNameAvailable(username, response);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(writer).println(argument.capture());

        assertEquals(argument.getValue(), String.valueOf(Boolean.TRUE));

    }

    @Test
    public void testUserIsAvailable_emptyStringReturnsFalse() throws DAOException, Exception {
        final String username = "";
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);

        List<User> users = new ArrayList<User>();

        this.userServlet.setUserDAO(dao);

        when(dao.findEntities(anyString(), anyString())).thenReturn(users);
        when(response.getWriter()).thenReturn(writer);

        this.userServlet.isUserNameAvailable(username, response);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(writer).println(argument.capture());

        assertEquals(argument.getValue(), String.valueOf(Boolean.FALSE));

    }
}
