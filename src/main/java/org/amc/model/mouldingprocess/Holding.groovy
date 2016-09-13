package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class Holding {
    /**
     * Holding pressure
     */
    @Column
    float holdingPressure1;
    @Column
    float holdingPressure2;
    @Column
    float holdingPressure3;
    @Column
    float holdingPressure4;
    @Column
    float holdingPressure5;
    @Column
    float holdingPressure6;

    /**
     * Holding Time
     */
    @Column
    float holdingTime1;
    @Column
    float holdingTime2;
    @Column
    float holdingTime3;
    @Column
    float holdingTime4;
    @Column
    float holdingTime5;
    @Column
    float holdingTime6;

}
