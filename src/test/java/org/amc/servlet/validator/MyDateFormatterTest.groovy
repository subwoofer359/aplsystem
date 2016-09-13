package org.amc.servlet.validator

import org.junit.Before;
import org.junit.Test;

class MyDateFormatterTest {
    MyDateFormatter formatter = new MyDateFormatter();
    Calendar expected;
    Calendar actual;
    
    @Before
    void setup() {
        expected = Calendar.getInstance();
        actual = Calendar.getInstance();
    }
    
    @Test
    void parseStringTest() {
        expected.set(1979,Calendar.JULY ,14,0,0,0); 
        String dateStr = '1979-07-14';
        actual.setTime(formatter.parse(dateStr, Locale.default));
        assert actual.get(Calendar.DATE) == expected.getAt(Calendar.DATE);
        assert actual.get(Calendar.MONTH) == expected.getAt(Calendar.MONTH);
        assert actual.get(Calendar.YEAR) == expected.getAt(Calendar.YEAR);  
    }
    
    @Test
    void getStingTest() {
        String dateStr = formatter.print(expected.getTime(), Locale.default);
        def format = /\d\d\d\d-\d\d-\d\d/;
        assert dateStr ==~ format;
    }
    
    @Test
    void getEmptyStringTest() {
        assert formatter.parse('', Locale.default) == null;
    }
}
