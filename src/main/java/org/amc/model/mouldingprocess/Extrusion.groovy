package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class Extrusion {
    @Column
    float backPressure1;
    @Column
    float backPressure2;
    @Column
    float backPressure3;
    @Column
    float backPressure4;
    @Column
    float backPressure5;
    @Column
    float backPressure6;

    @Column
    float screwExtSpeed1;
    @Column
    float screwExtSpeed2;
    @Column
    float screwExtSpeed3;
    @Column
    float screwExtSpeed4;
    @Column
    float screwExtSpeed5;
    @Column
    float screwExtSpeed6;

    @Column
    float extProfilePos1;
    @Column
    float extProfilePos2;
    @Column
    float extProfilePos3;
    @Column
    float extProfilePos4;
    @Column
    float extProfilePos5;
    @Column
    float extProfilePos6;
}
