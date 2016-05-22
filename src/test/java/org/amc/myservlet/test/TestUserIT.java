package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.model.User;
import org.amc.model.UserRoles;
import org.amc.myservlet.test.spc.DatabaseFixture;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUserIT {

    private static final DatabaseFixture dbFixture = new DatabaseFixture();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbFixture.setUp();
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dbFixture.tearDown();
    }
    
    @Before
    public void setUp() throws Exception {
        dbFixture.clearTables();
    }

    /**
     * 
     * @return Sample User
     */
    public User getTestUser() {
        User u = new User();
        u.setFullName("Adrian McLaughlin");
        u.setUserName("subwoofer359");
        u.setEmailAddress("subwoofer359@gmail.com");
        u.setPassword("orororo03");
        u.setActive(true);
        return u;
    }

    /**
     * Tests methods addUser(User u) and getUser(String id)
     */
    @Test
    public void testAddUser() throws DAOException {
        User u = getTestUser();
        // UserRoles roles=new UserRoles();
        // roles.setRoleName("QC");
        // roles.setUser(u);
        //
        // UserRoles roles2=new UserRoles();
        // roles2.setRoleName("Manager");
        // roles2.setUser(u);
        //
        // List<UserRoles> listOfRoles=new ArrayList<UserRoles>();
        // listOfRoles.add(roles);
        // listOfRoles.add(roles2);
        // u.setRoles(listOfRoles);
        DAO<User> ud = new DAO<User>(User.class);
        ud.addEntity(u);

        User tu = ud.getEntity(String.valueOf(u.getId()));

        assertTrue(tu.equals(u));
    }

    @Test
    public void testFindUsers() throws DAOException {
        DAO<User> ud = new DAO<User>(User.class);
        User[] users = { getTestUser(), getTestUser(), getTestUser(), getTestUser() };

        for (User u : users) {
            ud.addEntity(u);

        }
        List<User> list = ud.findEntities();
        assertEquals(users.length, list.size());

    }

    @Test
    public void testFindUsersByValue() throws DAOException {
        String userName = "Bunny";

        DAO<User> ud = new DAO<User>(User.class);

        User u1 = getTestUser();
        User u2 = getTestUser();
        u2.setUserName(userName);
        User u3 = getTestUser();
        User u4 = getTestUser();
        ud.addEntity(u1);
        ud.addEntity(u2);
        ud.addEntity(u3);
        ud.addEntity(u4);

        List<User> list = ud.findEntities("userName", userName);
        assertEquals(list.size(), 1);
        User actualUser = list.get(0);
        assertEquals(actualUser.getUserName(), userName);

    }

    @Test
    public void testUpdateUser() throws DAOException {
        String emailAddress = "chris@eircom.net";
        DAO<User> ud = new DAO<User>(User.class);
        User u = getTestUser();

        ud.addEntity(u);

        u.setEmailAddress(emailAddress);

        ud.updateEntity(u);

        User tu = ud.getEntity(String.valueOf(u.getId()));

        assertEquals(tu.getEmailAddress(), emailAddress);
    }

    @Test
    public void testRoles() throws DAOException {
        String userName = "Bunny";
        String[] roles = { "QC", "MANAGER" };

        DAO<User> ud = new DAO<User>(User.class);

        User u1 = getTestUser();
        User u2 = getTestUser();
        u2.setUserName(userName);

        UserRoles r1 = new UserRoles();
        r1.setRoleName(roles[0]);
        r1.setUser(u1);

        UserRoles r2 = new UserRoles();
        r2.setRoleName(roles[1]);
        r2.setUser(u1);

        UserRoles r3 = new UserRoles();
        r3.setRoleName(roles[0]);
        r3.setUser(u2);

        List<UserRoles> u1_roles = new ArrayList<UserRoles>();
        List<UserRoles> u2_roles = new ArrayList<UserRoles>();

        u1_roles.add(r1);
        u1_roles.add(r2);
        u2_roles.add(r3);

        u1.setRoles(u1_roles);
        u2.setRoles(u2_roles);

        ud.addEntity(u1);
        ud.addEntity(u2);

        User t1 = ud.getEntity(String.valueOf(u1.getId()));
        assertTrue(t1 != null);

        ud.deleteEntity(u1);
        try {
            t1 = ud.getEntity(String.valueOf(u1.getId()));
        } catch (DAOException de) {
            // Catch throw exception but for testing purposes carry on
            t1 = null;
        }

        assertTrue(t1 == null);

    }

    @Test
    public void testDeleteUser() throws DAOException {
        User u1 = getTestUser();

        UserRoles role1 = new UserRoles();
        role1.setRoleName("QC");
        role1.setUser(u1);
        UserRoles role2 = new UserRoles();
        role2.setRoleName("MANAGER");
        role2.setUser(u1);
        List<UserRoles> rolesList = new ArrayList<UserRoles>();
        rolesList.add(role1);
        rolesList.add(role2);
        u1.setRoles(rolesList);
        DAO<User> ud = new DAO<User>(User.class);

        ud.addEntity(u1);

        // Test user had persisted
        User ru1 = ud.getEntity(String.valueOf(u1.getId()));
        assertTrue(ru1.equals(u1));

        // Delete user
        ud.deleteEntity(ru1);

        // Test user has been deleted
        try {
            ru1 = ud.getEntity(String.valueOf(u1.getId()));
        } catch (DAOException de) {
            // Catch throw exception but for testing purposes carry on
            ru1 = null;
        }
        assertNull(ru1);
    }

    @Test
    public void testSetPassword() {
        User u = new User();
        User u2 = new User();
        String password = "helloworld";

        u.setPassword(password);
        u2.setPassword(password);
        assertTrue(new String(u.getPassword()).equals(new String(u2.getPassword())));
    }

}
