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
    @NamedQuery(name = "Ustaxrate.findAll", query = "SELECT u FROM Ustaxrate u")
    , @NamedQuery(name = "Ustaxrate.findByTaxrateId", query = "SELECT u FROM Ustaxrate u WHERE u.taxrateId = :taxrateId")
    , @NamedQuery(name = "Ustaxrate.findByStateTax", query = "SELECT u FROM Ustaxrate u WHERE u.stateTax = :stateTax")})
public class Ustaxrate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxrate_id")
    private Integer taxrateId;
    @Basic(optional = false)
    @Column(name = "state_tax")
    private double stateTax;
    @JoinColumn(name = "zip_code", referencedColumnName = "zip_code")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Uslocation zipCode;

    public Ustaxrate() {
    }

    public Ustaxrate(Integer taxrateId) {
        this.taxrateId = taxrateId;
    }

    public Ustaxrate(Integer taxrateId, double stateTax) {
        this.taxrateId = taxrateId;
        this.stateTax = stateTax;
    }

    public Integer getTaxrateId() {
        return taxrateId;
    }

    public void setTaxrateId(Integer taxrateId) {
        this.taxrateId = taxrateId;
    }

    public double getStateTax() {
        return stateTax;
    }

    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    public Uslocation getZipCode() {
        return zipCode;
    }

    public void setZipCode(Uslocation zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxrateId != null ? taxrateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ustaxrate)) {
            return false;
        }
        Ustaxrate other = (Ustaxrate) object;
        if ((this.taxrateId == null && other.taxrateId != null) || (this.taxrateId != null && !this.taxrateId.equals(other.taxrateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Ustaxrate[ taxrateId=" + taxrateId + " ]";
    }
    
}
