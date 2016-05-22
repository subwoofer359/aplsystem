package org.amc.myservlet.test.spc;

import java.util.List;

import javax.persistence.EntityManager;

import org.amc.DAOException;

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.model.spc.SPCPartsList;
import org.amc.servlet.APLSpcController;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

public class TestAPLSpcControllerIntegration {
    private TestSPCFixture fixture;
    private DAO<SPCPartsList> partsListDao;
    private DAO<Part> partsDAO;
    private APLSpcController controller;
    private static DatabaseFixture dbFixture = new DatabaseFixture();

    @BeforeClass
    public static void setupBeforeClass() {
        dbFixture.setUp();
    }

    @Before
    public void setUp() throws Exception {
        dbFixture.clearTables();
        fixture = new TestSPCFixture();

        partsListDao = new DAO<SPCPartsList>(SPCPartsList.class);
        partsDAO = new DAO<Part>(Part.class);
        controller = new APLSpcController();
        controller.setSPCListPartDAO(partsListDao);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        dbFixture.tearDown();
    }

    @Test
    public void testGetSPCPartList() throws DAOException {
        fixture.setupPartTable();
        List<Part> listOfParts = partsDAO.findEntities();

        for (int i = 0; (i < listOfParts.size() && i < 5); i++) {

            SPCPartsList spcPart = new SPCPartsList();
            Part part = null;

            EntityManager em = this.partsListDao.getEntityManager();
            em.getTransaction().begin();
            part = em.merge(listOfParts.get(i));
            em.getTransaction().commit();
            spcPart.setPart(part);
            partsListDao.addEntity(spcPart);
        }
        ModelAndView inputMav = new ModelAndView();

        ModelAndView mav = controller.getSPCPartList(inputMav);

        ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "parts", List.class);

        ModelAndViewAssert.assertViewName(mav, "spc/SPCPartList");

    }
}
