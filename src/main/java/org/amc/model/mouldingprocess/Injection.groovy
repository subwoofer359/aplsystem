package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class Injection {
    @Column
    float injectionSpeed1;
    @Column
    float injectionSpeed2;
    @Column
    float injectionSpeed3;
    @Column
    float injectionSpeed4;
    @Column
    float injectionSpeed5;
    @Column
    float injectionSpeed6;
    @Column
    float injSpeedPosition1;
    @Column
    float injSpeedPosition2;
    @Column
    float injSpeedPosition3;
    @Column
    float injSpeedPosition4;
    @Column
    float injSpeedPosition5;
    @Column
    float injSpeedPosition6;
}
