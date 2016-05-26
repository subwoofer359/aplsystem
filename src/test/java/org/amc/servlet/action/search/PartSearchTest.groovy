package org.amc.servlet.action.search;

import static org.junit.Assert.*;

import org.apache.openjpa.datacache.AbstractQueryCache.Default;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class PartSearchTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsEmpty() {
        def partSearch = new PartSearch();
        assert partSearch.isEmpty() == true;
    }
    
    @Test
    public void testIsEmptyWithWhiteSpace() {
        def partSearch = new PartSearch(company: '');
        assert partSearch.isEmpty() == true;
    }

}
