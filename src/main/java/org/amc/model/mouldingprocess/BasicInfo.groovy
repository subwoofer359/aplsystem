package org.amc.model.mouldingprocess

import org.amc.model.Material;

import java.util.Date;

import javax.persistence.Column
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
class BasicInfo {
    /**
     * Part ID
     */
    @Column(nullable = false)
    String partId;

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
    @OneToOne
    @JoinColumn(name = "materialId",updatable=true)
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
