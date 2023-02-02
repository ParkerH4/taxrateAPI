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
@Table(name = "uslocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uslocation.findAll", query = "SELECT u FROM Uslocation u")
    , @NamedQuery(name = "Uslocation.findByZipCode", query = "SELECT u FROM Uslocation u WHERE u.zipCode = :zipCode")
    , @NamedQuery(name = "Uslocation.findByCountry", query = "SELECT u FROM Uslocation u WHERE u.country = :country")
    , @NamedQuery(name = "Uslocation.findByState", query = "SELECT u FROM Uslocation u WHERE u.state = :state")})
public class Uslocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "zip_code")
    private String zipCode;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "state")
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zipCode", fetch = FetchType.EAGER)
    private List<Ustaxrate> ustaxrateList;

    public Uslocation() {
    }

    public Uslocation(String zipCode) {
        this.zipCode = zipCode;
    }

    public Uslocation(String zipCode, String country, String state) {
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public List<Ustaxrate> getUstaxrateList() {
        return ustaxrateList;
    }

    public void setUstaxrateList(List<Ustaxrate> ustaxrateList) {
        this.ustaxrateList = ustaxrateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zipCode != null ? zipCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uslocation)) {
            return false;
        }
        Uslocation other = (Uslocation) object;
        if ((this.zipCode == null && other.zipCode != null) || (this.zipCode != null && !this.zipCode.equals(other.zipCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Uslocation[ zipCode=" + zipCode + " ]";
    }
    
}
