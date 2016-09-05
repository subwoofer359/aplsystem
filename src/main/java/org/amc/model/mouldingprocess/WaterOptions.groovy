package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class WaterOptions {
    /**
     * Water
     */
    @Column
    float waterTempFixedHalf;
    @Column
    float waterTempMovingHalf;
    @Column
    String waterTempNotes;
}
