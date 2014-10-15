package org.amc.servlet.el;

import org.amc.model.Material;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
public final class MyFunctions {

    private static final Logger LOG = Logger.getLogger(MyFunctions.class);

    /**
     * Utility class
     */
    private MyFunctions() {
        throw new AssertionError(getClass().getSimpleName() + " is a utility class");
    }

    /**
     * @param request
     *            The HttpServletRequest from the user
     * @param role
     *            The role the user should be in
     * @return true if the user is in the role
     */
    public static boolean isUserInRole(HttpServletRequest request, String role) {
        return request.isUserInRole(role);
    }

    /**
     * To get a string representation of the Material
     * 
     * @param material
     *            Material Entity
     * @return String
     */
    public static String toString(Material material) {
        LOG.info("Helper Function:" + material.getName());
        return material.getCompany() + " " + material.getName() + " " + material.getType();
    }
}
