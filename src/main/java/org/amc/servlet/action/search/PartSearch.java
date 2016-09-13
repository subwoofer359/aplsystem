package org.amc.servlet.action.search;

/**
 * Class that store information of a User's search parameters for a
 * {@link org.model.Part}
 * 
 * @author Adrian McLaughlin
 *
 */
public class PartSearch implements WebFormSearch {
    
    private String partName;
    private String company;
    private String qssNumber;

    
    public String getPartName() {
        return partName;
    }
    
    /**
     * @return the qSSNumber
     */
    public String getQSSNumber() {
        return qssNumber;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
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
            this.partName = partName;
        }
    }

    /**
     * The empty string or null are ignored as values
     * @param qSSNumber String String can't be an empty String or null
     */
    public void setQSSNumber(String qSSNumber) {
        if(isNotWhiteSpace(qSSNumber)) {
            this.qssNumber = qSSNumber;
        }
    }

    /**
     * The empty string or null are ignored as values
     * @param company String String can't be an empty String or null
     */
    public void setCompany(String company) {
        if(isNotWhiteSpace(company)) {
            this.company = company;
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

    @Override
    public boolean isEmpty() {
        return partName == null && qssNumber == null && company == null;
    }
}
