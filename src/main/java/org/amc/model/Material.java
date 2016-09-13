package org.amc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO to represent a material used for plastic injection moulding
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */

@Entity
@Table(name = "material")
public class Material implements Serializable, WorkEntity {
    private static final long serialVersionUID = 2611230247508857580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * The name of the company that produces the material
     */
    @Column(nullable = false)
    private String company;
    /**
     * The name of the material itself e.g. Moplen 540p
     */
    @Column(nullable = false)
    private String name;

    /**
     * The type of material e.g. PVC/ABS/PP/PE
     */
    @Column(nullable = false)
    private String type;

    @Column
    private float density;
    @Column
    private float linearExpansion;
    @Column
    private float waterAbsorption;
    @Column
    private float materialDrying;
    @Column
    private float meltingTempLower;
    @Column
    private float meltingTempUpper;
    @Column
    private float mouldShrinkage;
    @Column
    private float mouldTempLow;
    @Column
    private float mouldTempUpper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getDensity() {
        return density;
    }

    public float getLinearExpansion() {
        return linearExpansion;
    }

    public float getWaterAbsorption() {
        return waterAbsorption;
    }

    public float getMaterialDrying() {
        return materialDrying;
    }

    public float getMeltingTempLower() {
        return meltingTempLower;
    }

    public float getMeltingTempUpper() {
        return meltingTempUpper;
    }

    public float getMouldShrinkage() {
        return mouldShrinkage;
    }

    public float getMouldTempLow() {
        return mouldTempLow;
    }

    public float getMouldTempUpper() {
        return mouldTempUpper;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public void setLinearExpansion(float linearExpansion) {
        this.linearExpansion = linearExpansion;
    }

    public void setWaterAbsorption(float waterAbsorption) {
        this.waterAbsorption = waterAbsorption;
    }

    public void setMaterialDrying(float materialDrying) {
        this.materialDrying = materialDrying;
    }

    public void setMeltingTempLower(float meltingTempLower) {
        this.meltingTempLower = meltingTempLower;
    }

    public void setMeltingTempUpper(float meltingTempUpper) {
        this.meltingTempUpper = meltingTempUpper;
    }

    public void setMouldShrinkage(float mouldShrinkage) {
        this.mouldShrinkage = mouldShrinkage;
    }

    public void setMouldTempLow(float mouldTempLow) {
        this.mouldTempLow = mouldTempLow;
    }

    public void setMouldTempUpper(float mouldTempUpper) {
        this.mouldTempUpper = mouldTempUpper;
    }

    @Override
    /**
     * @returns String representation of this material
     */
    public String toString() {
        return this.getCompany() + " " + this.getName() + " " + this.getType();
    }

    @Override
    /**
     * @returns true if the this material and other material have the same values for instance variables 
     */
    public boolean equals(Object material) {
        if (this == material)
            return true;

        if (material == null || !material.getClass().equals(this.getClass())) {
            return false;
        }

        Material m2 = (Material) material;

        return this.getName().equals(m2.getName()) && this.getCompany().equals(m2.getCompany())
                        && getType().equals(m2.getType());

    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 33 + company.hashCode();
        hash = hash * 2 + type.hashCode();
        return hash;
    }
}
