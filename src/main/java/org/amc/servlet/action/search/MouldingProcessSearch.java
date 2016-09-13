package org.amc.servlet.action.search;

import java.util.Date;

public class MouldingProcessSearch implements WebFormSearch {
    private String partName;
    private String machineNo;
    private Integer material;
    private String masterbatchNo;
    private Date startDate;
    private Date endDate;
    private String signedOffBy;

    public void setPartId(String partName) {
        if(isNotWhiteSpace(partName)) {
            this.partName = partName;
        }
    }

    public void setMachineNo(String machineNo) {
        if(isNotWhiteSpace(machineNo)) {
            this.machineNo = machineNo;
        }
    }

    public void setMaterial(Integer materialId) {
        this.material = materialId;
    }

    public void setMasterBatchNo(String masterbatchNo) {
        if(isNotWhiteSpace(masterbatchNo)) {
            this.masterbatchNo = masterbatchNo;
        }
    }

    public void setSignedOffBy(String signee) {
        if(isNotWhiteSpace(signee)) {
            this.signedOffBy = signee;
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPartId() {
        return partName;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public Integer getMaterial() {
        return material;
    }

    public String getMasterBatchNo() {
        return masterbatchNo;
    }

    public String getSignedOffBy() {
        return signedOffBy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    private boolean isNotWhiteSpace(String value) {
        return !"".equals(value);
    }
    
    @Override
    public boolean isEmpty() {
        return partName == null
                        && machineNo == null
                        && (material == null || material == 0)
                        && masterbatchNo == null
                        && startDate == null
                        && endDate == null
                        && signedOffBy == null;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("MouldingProcessSearch:");
        text.append("(PartId=");
        text.append(getPartId());
        text.append(')');
        text.append("(MachineNo=");
        text.append(getMachineNo());
        text.append(')');
        text.append("(Masterbactch=");
        text.append(getMasterBatchNo());
        text.append(')');
        text.append("(SignedOffBy=");
        text.append(getSignedOffBy());
        text.append(')');
        text.append("(Start Date=");
        text.append(getStartDate());
        text.append(')');
        text.append("(End Date=");
        text.append(getEndDate());
        text.append(')');
        return text.toString();
    }
}
