package org.amc.servlet.action.search;

import org.amc.model.User;
import org.amc.model.spc.SPCMeasurement;

import java.util.Date;
import java.util.Set;

public class SPCDataSearch implements WebFormSearch {
    private Date startDate;
    private Date endDate;
    private User user;
    private SPCMeasurement SpcMeasurement;
    private float lowerBoundMeasuremenet;
    private float upperBoundMeasurement;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the spcMeasurement
     */
    public SPCMeasurement getSpcMeasurement() {
        return SpcMeasurement;
    }

    /**
     * @return the lowerBoundMeasuremenet
     */
    public float getLowerBoundMeasuremenet() {
        return lowerBoundMeasuremenet;
    }

    /**
     * @return the upperBoundMeasurement
     */
    public float getUpperBoundMeasurement() {
        return upperBoundMeasurement;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @param spcMeasurement
     *            the spcMeasurement to set
     */
    public void setSpcMeasurement(SPCMeasurement spcMeasurement) {
        SpcMeasurement = spcMeasurement;
    }

    /**
     * @param lowerBoundMeasuremenet
     *            the lowerBoundMeasuremenet to set
     */
    public void setLowerBoundMeasuremenet(float lowerBoundMeasuremenet) {
        this.lowerBoundMeasuremenet = lowerBoundMeasuremenet;
    }

    /**
     * @param upperBoundMeasurement
     *            the upperBoundMeasurement to set
     */
    public void setUpperBoundMeasurement(float upperBoundMeasurement) {
        this.upperBoundMeasurement = upperBoundMeasurement;
    }

    public Set<SearchFields> getFields() {
        return null;
    }

    public Object getField(SearchFields field) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return startDate == null
                        && endDate == null
                        && user == null
                        && SpcMeasurement== null
                        && lowerBoundMeasuremenet == 0.0f
                        && upperBoundMeasurement == 0.0f;
    }
    
    
}
