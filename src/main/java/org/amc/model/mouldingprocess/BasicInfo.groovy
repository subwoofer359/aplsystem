package org.amc.model.mouldingprocess


import org.amc.model.Material;
import org.amc.model.Part;
import java.util.Date

import javax.persistence.CascadeType;
import javax.persistence.Column
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import groovy.transform.ToString;

@ToString
@Embeddable
class BasicInfo {
    /**
     * Part ID
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, updatable = true)
    Part partId;

    /**
     * size of machine in tonnes
     */
    @Column(nullable = false)
    int machineSize;

    /**
     * Identifer of the machine
     */
    @Column(nullable = false)
    String machineNo;

    /**
     * Material
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materialId",updatable = true)
    @Column(nullable = false)
    Material material;

    /**
     * Masterbatch number
     */
    @Column
    String masterbatchNo;

    /**
     * Date of the Process setup
     */
    @Column(nullable = false)
    Date dateOfIssue;
    
    /**
     * Author of the setup
     */
    @Column(nullable = false)
    String signOffBy;
    
    @Column
    String processNotes;
}
