package org.amc.servlet.action.search;

/**
 * 
 * @author Adrian McLaughlin
 *
 */
public class MaterialSearch implements WebFormSearch {
  
    private String company;
    private String name;
    private String type;

    /**
     * @return the company
     */
    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
        if(isNotWhiteSpace(company)) {
            this.company = company;
        }
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        if(isNotWhiteSpace(name)) {
            this.name = name;
        }
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        if(isNotWhiteSpace(type)) {
            this.type = type;
        }
    }
    
    private boolean isNotWhiteSpace(String value) {
        return !"".equals(value);
    }
    
    @Override
    public boolean isEmpty() {
        return name == null && type == null && company == null;
    }

}
