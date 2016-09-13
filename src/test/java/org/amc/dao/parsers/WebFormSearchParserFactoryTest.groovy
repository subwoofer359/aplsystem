package org.amc.dao.parsers

import static org.junit.Assert.*;

import org.amc.servlet.action.search.WebFormSearch
import org.junit.Test;;

class WebFormSearchParserFactoryTest {

    @Test(expected = NoSuchWebFormParserException)
    void expectionTest() {
        WebFormSearchParserFactory.getWebFormSearchParser(null, new NotImplementedWebForm());
        fail('No NoSuchWebFormParserException exception thrown');
    }
    
    
    static class NotImplementedWebForm implements WebFormSearch
    {

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
