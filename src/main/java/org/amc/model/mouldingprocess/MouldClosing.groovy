package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class MouldClosing {
    /**
     * mouldClosing
     */
    @Column
    float mouldClosingOpenLimitPos;
    @Column
    float mouldClosingOpenLimitSpeed;
    @Column
    float mouldClosedLimitPos;
    @Column
    float mouldClosedLimitSpeed;

    @Column
    float clsSlowPos;
    @Column
    float clsSlowSpeed;
    @Column
    float clsSPPos;
    @Column
    float clsSPSpeed;
}
