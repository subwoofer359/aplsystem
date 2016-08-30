package org.amc.servlet.action.search;

import java.util.Map;

/**
 * Class that store information of a User's search parameters for a
 * {@link org.model.Part}
 * 
 * @author Adrian McLaughlin
 *
 */
public class PartSearch extends WebFormSearch {
    public enum PartSearchFields implements SearchFields {
        PART_NAME("name"), QSS_NUMBER("qss_no"), COMPANY("company");

        /**
         * Database column name
         */
        private String name;

        private PartSearchFields(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public PartSearch() {
        super();
    }

    /**
     * @return the part
     */
    public String getPartName() {
        return (getFieldMap().get(PartSearchFields.PART_NAME) == null) ? null : String
                        .valueOf(getFieldMap().get(PartSearchFields.PART_NAME));
    }

    /**
     * @return the qSSNumber
     */
    public String getQSSNumber() {
        return (getFieldMap().get(PartSearchFields.QSS_NUMBER) == null) ? null : String
                        .valueOf(getFieldMap().get(PartSearchFields.QSS_NUMBER));
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return (getFieldMap().get(PartSearchFields.COMPANY) == null) ? null : String
                        .valueOf(getFieldMap().get(PartSearchFields.COMPANY));
    }

    private boolean isNotWhiteSpace(String value) {
        return !"".equals(value);
    }
    /**
     * The empty string or null are ignored as values
     * @param partName String String can't be an empty String or null
     */
    public void setPartName(String partName) {
        if(isNotWhiteSpace(partName)) {
            getFieldMap().put(PartSearchFields.PART_NAME, partName);
        }
    }

    /**
     * The empty string or null are ignored as values
     * @param qSSNumber String String can't be an empty String or null
     */
    public void setQSSNumber(String qSSNumber) {
        if(isNotWhiteSpace(qSSNumber)) {
            getFieldMap().put(PartSearchFields.QSS_NUMBER, qSSNumber);
        }
    }

    /**
     * The empty string or null are ignored as values
     * @param company String String can't be an empty String or null
     */
    public void setCompany(String company) {
        if(isNotWhiteSpace(company)) {
            getFieldMap().put(PartSearchFields.COMPANY, company);
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("PartSearch:");
        text.append("(Company=");
        text.append(getCompany());
        text.append(')');
        text.append("(PartName=");
        text.append(getPartName());
        text.append(')');
        text.append("(QSSNumber=");
        text.append(getQSSNumber());
        text.append(')');
        return text.toString();
    }
}
