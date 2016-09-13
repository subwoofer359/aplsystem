package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class BarrelTemperature {
    /**
     * Barrel Temperature
     */
    @Column
    float nozzelTemperature;
    @Column
    float barrelTemperature1;
    @Column
    float barrelTemperature2;
    @Column
    float barrelTemperature3;
    @Column
    float barrelTemperature4;
    @Column
    float throatTemperature;

}
