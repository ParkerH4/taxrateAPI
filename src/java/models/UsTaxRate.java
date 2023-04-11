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
 * UsTaxRate is an entity class representing a tax rate record in the ustaxrate
 * table. This class provides mapping and persistence for the tax rate data in
 * the database and includes associated properties, constructors, and methods
 * for accessing and manipulating the tax rate data.
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

    /**
     * Initializes a new instance of the UsTaxRate class.
     */
    public UsTaxRate() {
    }

    /**
     * Initializes a new instance of the UsTaxRate class with the specified tax
     * rate ID.
     *
     * @param taxRateId The unique identifier for the tax rate.
     */
    public UsTaxRate(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * Initializes a new instance of the UsTaxRate class with the specified tax
     * rate ID and state tax rate.
     *
     * @param taxRateId The unique identifier for the tax rate.
     * @param stateTax The state tax rate.
     */
    public UsTaxRate(Integer taxRateId, double stateTax) {
        this.taxRateId = taxRateId;
        this.stateTax = stateTax;
    }

    /**
     * Gets the tax rate ID.
     *
     * @return The unique identifier for the tax rate.
     */
    public Integer getTaxrateId() {
        return taxRateId;
    }

    /**
     * Sets the tax rate ID.
     *
     * @param taxRateId The unique identifier for the tax rate.
     */
    public void setTaxrateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * Gets the state tax rate.
     *
     * @return The state tax rate.
     */
    public double getStateTax() {
        return stateTax;
    }

    /**
     * Sets the state tax rate.
     *
     * @param stateTax The state tax rate.
     */
    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    /**
     * Gets the location associated with the tax rate.
     *
     * @return The location associated with the tax rate.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location associated with the tax rate.
     *
     * @param location The location associated with the tax rate.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Calculates the hash code for the UsTaxRate object using the tax rate ID.
     *
     * @return An integer representing the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxRateId != null ? taxRateId.hashCode() : 0);
        return hash;
    }

    /**
     * Compares this UsTaxRate object to the specified object for equality. The
     * comparison is based on the tax rate ID.
     *
     * @param object The object to compare this UsTaxRate with.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Returns a string representation of the UsTaxRate object.
     *
     * @return A string that includes the tax rate ID.
     */
    @Override
    public String toString() {
        return "models.UsTaxRate[ taxRateId=" + taxRateId + " ]";
    }

}
