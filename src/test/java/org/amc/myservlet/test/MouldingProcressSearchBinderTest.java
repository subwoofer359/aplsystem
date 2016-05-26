package org.amc.myservlet.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.model.MouldingProcessSearchForm;
import org.amc.servlet.validator.MouldingProcessSearchFormValidator.MouldingProcessSearchBinder;
import org.junit.Test;

public class MouldingProcressSearchBinderTest {

    private static final String PART_ID = "60g lids";
    private static final String MACHINE_NO = "Fanuc 3";
    private static final String MASTERBATCH_NO = "3939939";
    private static final String SIGNED_OFF_BY = "John Tobin";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testBinderParsesDatesCorrectly() throws ParseException {
        MouldingProcessSearchForm form = new MouldingProcessSearchForm();

        form.setPartId(PART_ID);
        form.setMachineNo(MACHINE_NO);
        form.setMasterbatchNo(MASTERBATCH_NO);
        form.setSignedOffBy(SIGNED_OFF_BY);
        form.setStartDate("2014-01-01");
        form.setEndDate("2014-01-02");
        
        MouldingProcessSearch search = MouldingProcessSearchBinder.getMouldingProcessSearch(form);

        assertEquals(form.getPartId(), search.getPartId());
        assertEquals(form.getMachineNo(), search.getMachineNo());
        assertEquals(form.getMasterbatchNo(), search.getMasterBatchNo());
        assertEquals(form.getSignedOffBy(), search.getSignedOffBy());

        assertEquals(new java.sql.Date(sdf.parse(form.getStartDate()).getTime()),
                        search.getStartDate());
        assertEquals(new java.sql.Date(sdf.parse(form.getEndDate()).getTime()), search.getEndDate());
    }

}
