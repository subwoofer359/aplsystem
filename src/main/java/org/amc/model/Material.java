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
    private float linear_expansion;
    @Column
    private float water_absorption;
    @Column
    private float material_drying;
    @Column
    private float melting_temp_lower;
    @Column
    private float melting_temp_upper;
    @Column
    private float mould_shrinkage;
    @Column
    private float mould_temp_low;
    @Column
    private float mould_temp_upper;

    public int getId() {
        return id;
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

    public float getLinear_expansion() {
        return linear_expansion;
    }

    public float getWater_absorption() {
        return water_absorption;
    }

    public float getMaterial_drying() {
        return material_drying;
    }

    public float getMelting_temp_lower() {
        return melting_temp_lower;
    }

    public float getMelting_temp_upper() {
        return melting_temp_upper;
    }

    public float getMould_shrinkage() {
        return mould_shrinkage;
    }

    public float getMould_temp_low() {
        return mould_temp_low;
    }

    public float getMould_temp_upper() {
        return mould_temp_upper;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setLinear_expansion(float linear_expansion) {
        this.linear_expansion = linear_expansion;
    }

    public void setWater_absorption(float water_absorption) {
        this.water_absorption = water_absorption;
    }

    public void setMaterial_drying(float material_drying) {
        this.material_drying = material_drying;
    }

    public void setMelting_temp_lower(float melting_temp_lower) {
        this.melting_temp_lower = melting_temp_lower;
    }

    public void setMelting_temp_upper(float melting_temp_upper) {
        this.melting_temp_upper = melting_temp_upper;
    }

    public void setMould_shrinkage(float mould_shrinkage) {
        this.mould_shrinkage = mould_shrinkage;
    }

    public void setMould_temp_low(float mould_temp_low) {
        this.mould_temp_low = mould_temp_low;
    }

    public void setMould_temp_upper(float mould_temp_upper) {
        this.mould_temp_upper = mould_temp_upper;
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
