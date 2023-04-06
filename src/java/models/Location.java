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
 * Location is an entity class representing a location record in the location
 * table. This class provides mapping and persistence for the location data in
 * the database and includes associated properties, constructors, and methods
 * for accessing and manipulating the location data.
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

    /**
     * Default no-argument constructor. Initializes a new instance of the
     * Location class.
     */
    public Location() {
    }

     /**
     * Initializes a new instance of the Location class with the specified
     * locationCode.
     *
     * @param locationCode The unique identifier for the location object.
     */
    public Location(String locationCode) {
        this.locationCode = locationCode;
    }

    public Location(String locationCode, String country, String region) {
        this.locationCode = locationCode;
        this.country = country;
        this.region = region;
    }

    /**
     * Gets the location code.
     *
     * @return The location code as a String.
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the location code.
     *
     * @param locationCode The location code as a String.
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * Gets the country.
     *
     * @return The country as a String.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country The country as a String.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the region.
     *
     * @return The region as a String.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region.
     *
     * @param region The region as a String.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the list of CanadaTaxRate objects associated with this Location.
     *
     * @return A List of CanadaTaxRate objects.
     */
    @XmlTransient
    public List<CanadaTaxRate> getCanadaTaxRateList() {
        return canadaTaxRateList;
    }

    /**
     * Sets the list of CanadaTaxRate objects associated with this Location.
     *
     * @param canadaTaxRateList A List of CanadaTaxRate objects.
     */
    public void setCanadaTaxRateList(List<CanadaTaxRate> canadaTaxRateList) {
        this.canadaTaxRateList = canadaTaxRateList;
    }

    /**
     * Gets the list of UsTaxRate objects associated with this Location.
     *
     * @return A List of UsTaxRate objects.
     */
    @XmlTransient
    public List<UsTaxRate> getUsTaxRateList() {
        return usTaxRateList;
    }

    /**
     * Sets the list of UsTaxRate objects associated with this Location.
     *
     * @param usTaxRateList A List of UsTaxRate objects.
     */
    public void setUsTaxRateList(List<UsTaxRate> usTaxRateList) {
        this.usTaxRateList = usTaxRateList;
    }

    /**
     * Generates a hash code based on the location code.
     *
     * @return An int value representing the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationCode != null ? locationCode.hashCode() : 0);
        return hash;
    }

    /**
     * Compares this Location object with the specified object for equality.
     *
     * @param object The object to be compared with this Location object.
     * @return true if the objects are equal, false otherwise.
     */
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

    /**
     * Generates a String representation of the Location object.
     *
     * @return A String representation of the Location object.
     */
    @Override
    public String toString() {
        return "models.Location[ locationCode=" + locationCode + " ]";
    }

}
