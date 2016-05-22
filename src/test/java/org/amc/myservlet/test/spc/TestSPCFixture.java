package org.amc.myservlet.test.spc;

import java.sql.Date;

import org.amc.DAOException;
import org.amc.dao.DAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.Material;
import org.amc.model.MouldingProcess;
import org.amc.model.Part;
import org.amc.model.User;
import org.amc.model.spc.SPCMeasurement;

/**
 * 
 * @author Adrian McLaughlin
 * 
 *         Create Entities required to test the different elements of the spc
 *         package
 *
 */
public class TestSPCFixture {
    
    public TestSPCFixture() {
    }
    
    public void setupPartTable() throws DAOException {
        // Get DAO object
        DAO<Part> partDAO = new DAO<Part>(Part.class);
        String[] colours = { "red", "blue", "green", "yellow" };
        String[] companies = { "HMV", "Granada", "Apple", "Apple" };
        String[] names = { "CD", "Car", "IPOD", "IPHONE" };
        String[] part_ids = { "393939", "99w2933", "ap3003", "ap2202" };
        String[] qss_nos = { "CD 001", "GA 002", "A 001", "A 002" };

        Part part = null;
        for (int i = 0; i < colours.length; i++) {
            part = new Part();
            part.setName(names[i]);
            part.setColour(colours[i]);
            part.setCompany(companies[i]);
            part.setExternal(true);
            part.setPart_id(part_ids[i]);
            part.setQss_no(qss_nos[i]);
            part.setRevision("0.2" + i);
            part.setVersion(String.valueOf(i));
            partDAO.addEntity(part);
        }
        // Set it to be garbage collected
        partDAO = null;
    }

    public void setUpMaterialTable() throws DAOException {
        DAO<Material> materialDAO = new DAO<Material>(Material.class);
        String[] company = { "Exxon Mobil" };
        String[] type = { "PC" };
        String[] name = { "Moplen 540P" };

        for (int i = 0; i < company.length; i++) {
            Material material = new Material();
            material.setCompany(company[i]);
            material.setType(type[i]);
            material.setName(name[i]);

            materialDAO.addEntity(material);
        }
    }

    /**
     * A Material table is set up when calling this method
     * 
     * @throws DAOException
     */
    public void setUpMouldingProcessTable() throws DAOException {
        setUpMaterialTable();
        DAO<Material> materialDAO = new DAO<Material>(Material.class);
        Material material = materialDAO.findEntities().get(0);

        DAO<MouldingProcess> mpDAO = new DAO<MouldingProcess>(MouldingProcess.class);

        String[] partId = { "1", "2", "3", "4" };
        int[] machineSize = { 150, 100, 125, 75 };
        String[] machineNo = { "Fanuc 1", "Fanuc 7", "Fanuc 5", "Boy 2" };
        int[] materials = { material.getId(), material.getId(), material.getId(), material.getId() };
        String[] masterbatchNo = { "20303", "030030", "303002", "30302123" };
        Date[] dateOfIssue = { Date.valueOf("2014-06-12"), Date.valueOf("2013-05-11"),
                Date.valueOf("2010-02-12"), Date.valueOf("2004-01-02") };
        String signOffBy = "John Malone";

        for (int i = 0; i < partId.length; i++) {
            MouldingProcess mp = new MouldingProcess();
            mp.setPartId(partId[i]);
            mp.setMachineSize(machineSize[i]);
            mp.setMachineNo(machineNo[i]);
            mp.setMaterial(materials[i]);
            mp.setMasterbatchNo(masterbatchNo[i]);
            mp.setDateOfIssue(dateOfIssue[i]);
            mp.setSignOffBy(signOffBy);
            mpDAO.addEntity(mp);
        }

        mpDAO = null;

    }

    public void setUpUserTable() throws DAOException {
        // Get DAO object
        DAO<User> userDAO = new DAO<User>(User.class);

        // Create User Entities and add them to the database
        String[] fullnames = { "Adrian McLaughlin", "Stephen Nolan", "Chris Dalton" };
        String[] userNames = { "adrian", "stephen", "chris" };
        String[] emailAddresses = { "adrian@adrianmclaughlin.ie", "stephen@gmail.com",
                "chris@gmail.com" };
        String[] passwords = { "wewew", "3i3i3i3i", "fpefpefle" };
        User user = null;
        for (int i = 0; i < fullnames.length; i++) {
            user = new User();
            user.setActive(true);
            user.setEmailAddress(emailAddresses[i]);
            user.setFullName(fullnames[i]);
            user.setUserName(userNames[i]);
            user.setPassword(passwords[i]);
            userDAO.addEntity(user);
        }
        // Set it to be garbage collected
        userDAO = null;
    }

    public void setUpSPCMeasurementTable() throws DAOException {
        DAO<Part> partDAO = new DAO<Part>(Part.class);
        SPCMeasurementDAO measurementDAO = new SPCMeasurementDAO();

        // Presuming there are entries in the part database table;
        Part p = partDAO.findEntities().get(0);

        String[] dimensions = { "length", "height", "width", "radius" };
        float[] lowerLimits = { 0.3f, 0.2f, 0f, 3f, 1f };
        float[] upperLimits = { 0.3f, 0.2f, 0f, 3f, 1f };
        float[] nominals = { 123.34f, 221.2f, 11.22f, 21.23f, 0.33f };
        int[] noOfMeasurments = { 3, 2, 5, 5, 10 };
        // String[]
        // tableIds={"tableId0020202020","tableId03","tableId0020ffrrf0","tableId022ff202020","tableId0020330"};

        for (int i = 0; i < dimensions.length; i++) {
            SPCMeasurement measurement = new SPCMeasurement();
            measurement.setActive(true);
            measurement.setDimension(dimensions[i]);
            measurement.setLowerLimit(lowerLimits[i]);
            measurement.setUpperLimit(upperLimits[i]);
            measurement.setNominal(nominals[i]);
            measurement.setNoOfMeasurements(noOfMeasurments[i]);
            // measurement.setTableId(tableIds[i]);
            measurement.setPart(p);
            measurementDAO.addEntity(measurement);
        }

    }
}
