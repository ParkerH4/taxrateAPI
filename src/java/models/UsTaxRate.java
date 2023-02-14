/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author super
 */
@Entity
@Table(name = "ustaxrate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsTaxRate.findAll", query = "SELECT u FROM UsTaxRate u")
    , @NamedQuery(name = "UsTaxRate.findByTaxrateId", query = "SELECT u FROM UsTaxRate u WHERE u.taxRateId = :taxRateId")
    , @NamedQuery(name = "UsTaxRate.findByStateTax", query = "SELECT u FROM UsTaxRate u WHERE u.stateTax = :stateTax")})
public class UsTaxRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxrate_id")
    private Integer taxRateId;
    @Basic(optional = false)
    @Column(name = "state_tax")
    private double stateTax;
    @JoinColumn(name = "location_code", referencedColumnName = "location_code")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Location location;

    public UsTaxRate() {
    }

    public UsTaxRate(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public UsTaxRate(Integer taxRateId, double stateTax) {
        this.taxRateId = taxRateId;
        this.stateTax = stateTax;
    }

    public Integer getTaxrateId() {
        return taxRateId;
    }

    public void setTaxrateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public double getStateTax() {
        return stateTax;
    }

    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxRateId != null ? taxRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsTaxRate)) {
            return false;
        }
        UsTaxRate other = (UsTaxRate) object;
        if ((this.taxRateId == null && other.taxRateId != null) || (this.taxRateId != null && !this.taxRateId.equals(other.taxRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.UsTaxRate[ taxRateId=" + taxRateId + " ]";
    }
    
}
