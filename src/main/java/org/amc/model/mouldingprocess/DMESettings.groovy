package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class DMESettings {
    /**
     * DMEs
     */
    @Column
    float dme1;
    @Column
    float dme2;
    @Column
    float dme3;
    @Column
    float dme4;
    @Column
    float dme5;
    @Column
    float dme6;
    @Column
    float dme7;
    @Column
    float dme8;
}
