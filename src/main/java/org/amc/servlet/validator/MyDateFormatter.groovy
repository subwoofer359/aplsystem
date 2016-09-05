package org.amc.servlet.validator

import org.springframework.format.datetime.DateFormatter

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

class MyDateFormatter extends DateFormatter{
    static final String pattern = 'yyyy-MM-dd';
    MyDateFormatter() {
        super(pattern);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if(''.equals(text)) {
            return new Date();
        } else {
            return super.parse(text, locale);
        }
    }
    
    @Override
    public String print(Date date, Locale locale) {
        return super.getDateFormat(locale).format(date);
    }
}