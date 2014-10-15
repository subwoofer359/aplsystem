package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.amc.dao.parsers.MouldingProcessSearchParser;
import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.junit.Test;

public class MouldingProcessSearchParserTest {
    private static final String QUERY_SELECT = "Select x from "
                    + MouldingProcess.class.getSimpleName() + " x WHERE ";
    private static final String TEST_QUERY1 = "x.partId LIKE :PART_NAME";
    private static final String TEST_QUERY2 = "x.material LIKE :MATERIAL AND x.masterbatchNo LIKE :MASTERBATCH";
    private static final String TEST_QUERY3 = "x.dateOfIssue = :START_DATE";
    private static final String TEST_QUERY4 = "x.dateOfIssue BETWEEN :START_DATE AND :END_DATE";
    private static final String TEST_QUERY5 = TEST_QUERY1 + " AND " + TEST_QUERY4;
    private static final String TEST_QUERY6 = TEST_QUERY1 + " AND " + TEST_QUERY3;

    @Test
    public void testQuery1Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setPartId("60g lid");

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY1, actual);
    }

    @Test
    public void testQuery2Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setMasterBatchNo("303003");
        search.setMaterial(3);

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY2, actual);
    }

    @Test
    public void testQuery3Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setStartDate(new Date(System.currentTimeMillis()));

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY3, actual);
    }

    @Test
    public void testQuery4Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setStartDate(new Date(System.currentTimeMillis()));
        search.setEndDate(new Date(System.currentTimeMillis()));

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY4, actual);
    }

    @Test
    public void testQuery5Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setPartId("60g lid");
        search.setStartDate(new Date(System.currentTimeMillis()));
        search.setEndDate(new Date(System.currentTimeMillis()));

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY5, actual);
    }

    @Test
    public void testQuery6Parse() {
        MouldingProcessSearch search = new MouldingProcessSearch();
        search.setPartId("60g lid");
        search.setStartDate(new Date(System.currentTimeMillis()));

        MouldingProcessSearchParser parser = new MouldingProcessSearchParser();
        String actual = parser.parse(MouldingProcess.class, search);

        assertEquals(QUERY_SELECT + TEST_QUERY6, actual);
    }

}
