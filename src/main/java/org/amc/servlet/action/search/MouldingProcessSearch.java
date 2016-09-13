package org.amc.servlet.action.search;

import java.util.Date;

public class MouldingProcessSearch extends WebFormSearch {
    public enum ProcessSearchFields implements SearchFields {
        PART_NAME("partId.name"), MACHINE_NO("machineNo"), MATERIAL("material"), MASTERBATCH(
                        "masterbatchNo"), START_DATE("dateOfIssue"), END_DATE("dateOfIssue"), SIGNED_OFF_BY(
                        "signOffBy");

        private final String name;

        private ProcessSearchFields(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public MouldingProcessSearch() {
        super();
    }

    public void setPartId(String partId) {
        getFieldMap().put(ProcessSearchFields.PART_NAME, partId);
    }

    public void setMachineNo(String machineNo) {
        getFieldMap().put(ProcessSearchFields.MACHINE_NO, machineNo);
    }

    public void setMaterial(int materialId) {
        getFieldMap().put(ProcessSearchFields.MATERIAL, materialId);
    }

    public void setMasterBatchNo(String masterbatchNo) {
        getFieldMap().put(ProcessSearchFields.MASTERBATCH, masterbatchNo);
    }

    public void setSignedOffBy(String signee) {
        getFieldMap().put(ProcessSearchFields.SIGNED_OFF_BY, signee);
    }

    public void setStartDate(Date startDate) {
        getFieldMap().put(ProcessSearchFields.START_DATE, startDate);
    }

    public void setEndDate(Date endDate) {
        getFieldMap().put(ProcessSearchFields.END_DATE, endDate);
    }

    public String getPartId() {
        return (getFieldMap().get(ProcessSearchFields.PART_NAME) == null) ? null : String
                        .valueOf(getFieldMap().get(ProcessSearchFields.PART_NAME));
    }

    public String getMachineNo() {
        return (getFieldMap().get(ProcessSearchFields.MACHINE_NO) == null) ? null : String
                        .valueOf(getFieldMap().get(ProcessSearchFields.MACHINE_NO));
    }

    public Integer getMaterial() {
        return (getFieldMap().get(ProcessSearchFields.MATERIAL) == null) ? null
                        : (Integer) getFieldMap().get(ProcessSearchFields.MATERIAL);
    }

    public String getMasterBatchNo() {
        return (getFieldMap().get(ProcessSearchFields.MASTERBATCH) == null) ? null : String
                        .valueOf(getFieldMap().get(ProcessSearchFields.MASTERBATCH));
    }

    public String getSignedOffBy() {
        return (getFieldMap().get(ProcessSearchFields.SIGNED_OFF_BY) == null) ? null : String
                        .valueOf(getFieldMap().get(ProcessSearchFields.SIGNED_OFF_BY));
    }

    public Date getStartDate() {
        return (getFieldMap().get(ProcessSearchFields.START_DATE) == null) ? null
                        : (Date) getFieldMap().get(ProcessSearchFields.START_DATE);
    }

    public Date getEndDate() {
        return (getFieldMap().get(ProcessSearchFields.END_DATE) == null) ? null
                        : (Date) getFieldMap().get(ProcessSearchFields.END_DATE);
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
