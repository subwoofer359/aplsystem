package org.amc.servlet.action.search;

import java.util.Map;

/**
 * Class that store information of a User's search parameters for a
 * org.amc.model.Part
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

    /**
     * @param part
     *            the part to set
     */
    public void setPartName(String partName) {
        getFieldMap().put(PartSearchFields.PART_NAME, partName);
    }

    /**
     * @param qSSNumber
     *            the qSSNumber to set
     */
    public void setQSSNumber(String qSSNumber) {
        getFieldMap().put(PartSearchFields.QSS_NUMBER, qSSNumber);
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
        getFieldMap().put(PartSearchFields.COMPANY, company);
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
    
    public boolean isEmpty() {
       Map<?,?> fields = getFieldMap();
       return fields.isEmpty();
    }
}
