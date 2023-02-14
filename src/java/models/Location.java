/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author super
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
    , @NamedQuery(name = "Location.findByLocationCode", query = "SELECT l FROM Location l WHERE l.locationCode = :locationCode")
    , @NamedQuery(name = "Location.findByCountry", query = "SELECT l FROM Location l WHERE l.country = :country")
    , @NamedQuery(name = "Location.findByRegion", query = "SELECT l FROM Location l WHERE l.region = :region")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "location_code")
    private String locationCode;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "region")
    private String region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.EAGER)
    private List<CanadaTaxRate> canadaTaxRateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.EAGER)
    private List<UsTaxRate> usTaxRateList;

    public Location() {
    }

    public Location(String locationCode) {
        this.locationCode = locationCode;
    }

    public Location(String locationCode, String country, String region) {
        this.locationCode = locationCode;
        this.country = country;
        this.region = region;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlTransient
    public List<CanadaTaxRate> getCanadaTaxRateList() {
        return canadaTaxRateList;
    }

    public void setCanadaTaxRateList(List<CanadaTaxRate> canadaTaxRateList) {
        this.canadaTaxRateList = canadaTaxRateList;
    }

    @XmlTransient
    public List<UsTaxRate> getUsTaxRateList() {
        return usTaxRateList;
    }

    public void setUsTaxRateList(List<UsTaxRate> usTaxRateList) {
        this.usTaxRateList = usTaxRateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationCode != null ? locationCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationCode == null && other.locationCode != null) || (this.locationCode != null && !this.locationCode.equals(other.locationCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Location[ locationCode=" + locationCode + " ]";
    }
    
}
