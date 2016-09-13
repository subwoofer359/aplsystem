package org.amc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Project: APL_Problem_Database
 * </p>
 * <p>
 * file: $URL:
 * file:///home/adrian/Documents/SVNRepository/Java/APL_Problem_Database
 * /trunk/APL_Problem_Database/src/apl_problem_database/JobTemplate.java $
 * <p>
 * <p>
 * Created on May 4, 2006
 * </p>
 * 
 * @author adrian
 * @version $Revision: 888 $ Represents a job running in the factory
 */
@Entity
@Table(name = "jobtemplate")
public class Part implements Serializable, WorkEntity {

    private static final long serialVersionUID = -8498315270583017514L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String colour;
    @Column(nullable = false, length = 50)
    private String company;
    @Column(nullable = false)
    private boolean external;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String part_id;
    @Column(nullable = false, length = 10)
    private String qss_no;
    @Column(length = 20)
    private String revision;
    @Column(length = 20)
    private String version;

    /**
     * 
     * Constructor for JobTemplate.java
     */
    public Part() {
        // empty constructor
    }

    /**
     * 
     * Constructor for JobTemplate.java
     * 
     * @param name
     * @param part_id
     * @param company
     * @param version
     * @param revision
     * @param colour
     * @param external
     */
    public Part(String name, String part_id, String company, String version, String revision,
                    String colour, boolean external, String qss_no) {

        this.name = name;
        this.part_id = part_id;
        this.company = company;
        this.version = version;
        this.revision = revision;
        this.colour = colour;
        this.external = external;
        this.qss_no = qss_no;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(' ');
        sb.append(getVersion());
        sb.append(' ');
        sb.append(getRevision());
        sb.append(' ');
        sb.append(getColour());
        return sb.toString();
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getQss_no() {
        return qss_no;
    }

    public void setQss_no(String qss_no) {
        this.qss_no = qss_no;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((part_id == null) ? 0 : part_id.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Part other = (Part) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (part_id == null) {
            if (other.part_id != null)
                return false;
        } else if (!part_id.equals(other.part_id))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    
}
