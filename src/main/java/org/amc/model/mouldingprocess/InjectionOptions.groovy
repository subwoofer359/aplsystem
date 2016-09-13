package org.amc.model.mouldingprocess

import javax.persistence.Column
import javax.persistence.Embeddable;;

@Embeddable
class InjectionOptions {
    /**
     * Injection and Holding variables
     */
    @Column
    float maxPackVel;
    @Column
    float posTran;
    @Column
    float maxInjPre;
    @Column
    float maxInjTime;
    @Column
    float shotSize;
    @Column
    float decompressionDist;
    @Column
    float decompressionVel;
    @Column
    float coolTime;
}
