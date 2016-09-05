package org.amc.model.mouldingprocess

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class Ejectors {
    /**
     * Ejectors
     */
    @Column
    String ejectMode;
    @Column
    float ejectPulse;
    @Column
    float ejectDelay;
    @Column
    float ejectorsFwdPos;
    @Column
    float ejectorsFwdSpeed;
    @Column
    float ejectorsFwdTime;
    @Column
    float ejectorsStopPos;
    @Column
    float ejectorsStopSpeed;
    @Column
    float ejectorsStopTime;
    @Column
    float ejectorsRevPos;
    @Column
    float ejectorsRevSpeed;
    @Column
    float ejectorsRevTime;
}
