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
    @NamedQuery(name = "Canadataxrate.findAll", query = "SELECT c FROM Canadataxrate c")
    , @NamedQuery(name = "Canadataxrate.findByTaxrateId", query = "SELECT c FROM Canadataxrate c WHERE c.taxrateId = :taxrateId")
    , @NamedQuery(name = "Canadataxrate.findByGst", query = "SELECT c FROM Canadataxrate c WHERE c.gst = :gst")
    , @NamedQuery(name = "Canadataxrate.findByPst", query = "SELECT c FROM Canadataxrate c WHERE c.pst = :pst")
    , @NamedQuery(name = "Canadataxrate.findByHst", query = "SELECT c FROM Canadataxrate c WHERE c.hst = :hst")})
public class Canadataxrate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxrate_id")
    private Integer taxrateId;
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
    private Canadalocation locationCode;

    public Canadataxrate() {
    }

    public Canadataxrate(Integer taxrateId) {
        this.taxrateId = taxrateId;
    }

    public Canadataxrate(Integer taxrateId, double gst, double pst, double hst) {
        this.taxrateId = taxrateId;
        this.gst = gst;
        this.pst = pst;
        this.hst = hst;
    }

    public Integer getTaxrateId() {
        return taxrateId;
    }

    public void setTaxrateId(Integer taxrateId) {
        this.taxrateId = taxrateId;
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

    public Canadalocation getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Canadalocation locationCode) {
        this.locationCode = locationCode;
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
        if (!(object instanceof Canadataxrate)) {
            return false;
        }
        Canadataxrate other = (Canadataxrate) object;
        if ((this.taxrateId == null && other.taxrateId != null) || (this.taxrateId != null && !this.taxrateId.equals(other.taxrateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Canadataxrate[ taxrateId=" + taxrateId + " ]";
    }
    
}
