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
@Table(name = "canadataxrate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanadaTaxRate.findAll", query = "SELECT c FROM CanadaTaxRate c")
    , @NamedQuery(name = "CanadaTaxRate.findByTaxrateId", query = "SELECT c FROM CanadaTaxRate c WHERE c.taxRateId = :taxRateId")
    , @NamedQuery(name = "CanadaTaxRate.findByGst", query = "SELECT c FROM CanadaTaxRate c WHERE c.gst = :gst")
    , @NamedQuery(name = "CanadaTaxRate.findByPst", query = "SELECT c FROM CanadaTaxRate c WHERE c.pst = :pst")
    , @NamedQuery(name = "CanadaTaxRate.findByHst", query = "SELECT c FROM CanadaTaxRate c WHERE c.hst = :hst")})
public class CanadaTaxRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxrate_id")
    private Integer taxRateId;
    @Basic(optional = false)
    @Column(name = "gst")
    private double gst;
    @Basic(optional = false)
    @Column(name = "pst")
    private double pst;
    @Basic(optional = false)
    @Column(name = "hst")
    private double hst;
    @JoinColumn(name = "location_code", referencedColumnName = "location_code")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Location location;

    public CanadaTaxRate() {
    }

    public CanadaTaxRate(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public CanadaTaxRate(Integer taxRateId, double gst, double pst, double hst) {
        this.taxRateId = taxRateId;
        this.gst = gst;
        this.pst = pst;
        this.hst = hst;
    }

    public Integer getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getPst() {
        return pst;
    }

    public void setPst(double pst) {
        this.pst = pst;
    }

    public double getHst() {
        return hst;
    }

    public void setHst(double hst) {
        this.hst = hst;
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
        if (!(object instanceof CanadaTaxRate)) {
            return false;
        }
        CanadaTaxRate other = (CanadaTaxRate) object;
        if ((this.taxRateId == null && other.taxRateId != null) || (this.taxRateId != null && !this.taxRateId.equals(other.taxRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.CanadaTaxRate[ taxRateId=" + taxRateId + " ]";
    }
    
}
