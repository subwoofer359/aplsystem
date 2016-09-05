package org.amc.model;

import org.amc.model.mouldingprocess.BarrelTemperature;
import org.amc.model.mouldingprocess.BasicInfo;
import org.amc.model.mouldingprocess.DMESettings;
import org.amc.model.mouldingprocess.Ejectors;
import org.amc.model.mouldingprocess.Extrusion;
import org.amc.model.mouldingprocess.Holding;
import org.amc.model.mouldingprocess.Injection;
import org.amc.model.mouldingprocess.InjectionOptions;
import org.amc.model.mouldingprocess.MouldOpening;
import org.amc.model.mouldingprocess.MouldClosing;
import org.amc.model.mouldingprocess.WaterOptions;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
/**
 * @author adrian
 *
 */
@Entity
@Table(name = "processSheets")
public class MouldingProcess implements Serializable, WorkEntity {
   /**
     * Serializable
     */
    private static final long serialVersionUID = -2782845506401132766L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// Database ID
    
    @Embedded
    private BasicInfo basicInfo;
    
    @Embedded
    private Injection injection;

    @Embedded
    private Holding holding; 
    
    @Embedded
    private Extrusion extrusion;
    
    @Embedded
    private InjectionOptions injectionOptions;
    
    @Embedded
    private BarrelTemperature barrelTemperature;
    
    @Embedded
    private DMESettings dmeSettings;
    
    @Embedded
    private Ejectors ejectors;
    
    @Embedded
    private MouldOpening mouldOpening;
    
    @Embedded
    private MouldClosing mouldingClosing;
    
    @Embedded
    private WaterOptions waterOptions;
    
    public MouldingProcess() {
        basicInfo = new BasicInfo();
        injection = new Injection();
        holding = new Holding();
        extrusion = new Extrusion();
        injection = new Injection();
        injectionOptions = new InjectionOptions();
        barrelTemperature = new BarrelTemperature();
        dmeSettings = new DMESettings();
        ejectors = new Ejectors();
        mouldOpening = new MouldOpening();
        mouldingClosing = new MouldClosing();
        waterOptions = new WaterOptions();
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public Injection getInjection() {
        return injection;
    }

    public Holding getHolding() {
        return holding;
    }

    public Extrusion getExtrusion() {
        return extrusion;
    }

    public InjectionOptions getInjectionOptions() {
        return injectionOptions;
    }

    public BarrelTemperature getBarrelTemperature() {
        return barrelTemperature;
    }

    public DMESettings getDmeSettings() {
        return dmeSettings;
    }

    public Ejectors getEjectors() {
        return ejectors;
    }

    public MouldOpening getMouldOpening() {
        return mouldOpening;
    }

    public MouldClosing getMouldingClosing() {
        return mouldingClosing;
    }

    public WaterOptions getWaterOptions() {
        return waterOptions;
    }
    
    
}
