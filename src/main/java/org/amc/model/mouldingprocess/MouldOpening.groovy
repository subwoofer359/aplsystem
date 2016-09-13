package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class MouldOpening {
    /**
     * mould opening
     */
    @Column
    float mouldOpenBreakAwaySpeed;
    @Column
    float mouldOpenStepPos1;
    @Column
    float mouldOpenStepSpeed1;
    @Column
    float mouldOpenStepPos2;
    @Column
    float mouldOpenStepSpeed2;
    @Column
    float mouldOpenStepPos3;
    @Column
    float mouldOpenStepSpeed3;

    @Column
    float mouldOpenTime;
    @Column
    String ejectStart;

}
