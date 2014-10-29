package org.amc.servlet.validator;

import org.amc.servlet.action.search.MouldingProcessSearch;
import org.amc.servlet.model.MouldingProcessSearchForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class MouldingProcessSearchFormValidator extends WebPageFormValidator {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat();
    private static final String DATE_PATTERN1 = "yyyy-MM-dd";
    private static final String DATE_PATTERN2 = "dd-MM-yyyy";

    public final List<String> validate(MouldingProcessSearchForm form) {
        List<String> errors = getErrors();

        try {
            if (form.getMaterial() != null && !"".trim().equals(form.getMaterial())) {
                Integer.parseInt(form.getMaterial());
            }
        } catch (NumberFormatException nfe) {
            errors.add("Value for Material:" + form.getMaterial() + " can't be parsed to a number");
        }

        try {
            if (form.getStartDate() != null && !"".trim().equals(form.getStartDate())) {
                parseDate(form.getStartDate());
            }
        } catch (ParseException pe) {
            errors.add("Value for Start Date:" + form.getStartDate() + " is not a valid date");
        }

        try {
            if (form.getEndDate() != null && !"".trim().equals(form.getEndDate())) {
                parseDate(form.getEndDate());
            }
        } catch (ParseException pe) {
            errors.add("Value for End Date:" + form.getEndDate() + " is not a valid date");
        }
        return errors;
    }

    /**
     * 
     * @param dateString
     * @return java.util.Date
     * @throws ParseException
     *             if the date spring doesn't macth the defined date format
     */
    private static final java.util.Date parseDate(String dateString) throws ParseException {
        Date result = null;
        try {
            DATE_FORMAT.applyPattern(DATE_PATTERN1);
            result = DATE_FORMAT.parse(dateString);
        } catch (ParseException pe) {
            DATE_FORMAT.applyPattern(DATE_PATTERN2);
            result = DATE_FORMAT.parse(dateString);
        }
        return result;
    }

    public static final class MouldingProcessSearchBinder {
        public static final MouldingProcessSearch getMouldingProcessSearch(
                        MouldingProcessSearchForm form) throws ParseException {
            MouldingProcessSearch mpSearch = new MouldingProcessSearch();

            if (form.getPartId() != null && !"".trim().equals(form.getPartId())) {
                mpSearch.setPartId(String.valueOf(form.getPartId()));
            }

            if (form.getMachineNo() != null && !"".trim().equals(form.getMachineNo())) {
                mpSearch.setMachineNo(form.getMachineNo());
            }

            if (form.getMaterial() != null && !"".trim().equals(form.getMaterial())) {
                mpSearch.setMaterial(Integer.parseInt(form.getMaterial()));
            }

            if (form.getMasterbatchNo() != null && !"".trim().equals(form.getMasterbatchNo())) {
                mpSearch.setMasterBatchNo(form.getMasterbatchNo());
            }

            if (form.getSignedOffBy() != null && !"".trim().equals(form.getSignedOffBy())) {
                mpSearch.setSignedOffBy(form.getSignedOffBy());
            }

            if (form.getStartDate() != null && !"".trim().equals(form.getStartDate())) {
                mpSearch.setStartDate(new java.sql.Date(parseDate(form.getStartDate())
                                .getTime()));
            }

            if (form.getEndDate() != null && !"".trim().equals(form.getEndDate())) {
                mpSearch.setEndDate(new java.sql.Date(parseDate(form.getEndDate())
                                .getTime()));
            }

            return mpSearch;
        }
    }
}
