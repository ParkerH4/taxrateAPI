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
 * CanadaTaxRate is an entity class representing a tax rate record in the
 * canadataxrate table. This class provides mapping and persistence for the
 * tax rate data in the database and includes associated properties,
 * constructors, and methods for accessing and manipulating the tax rate data.
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

    /**
     * Default no-argument constructor. Initializes a new instance of the
     * CanadaTaxRate class.
     */
    public CanadaTaxRate() {
    }

    /**
     * Initializes a new instance of the CanadaTaxRate class with the specified
     * tax rate ID.
     *
     * @param taxRateId The unique identifier for the tax rate.
     */
    public CanadaTaxRate(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * Initializes a new instance of the CanadaTaxRate class with the specified
     * tax rate ID, GST, PST, and HST.
     *
     * @param taxRateId The unique identifier for the tax rate.
     * @param gst The Goods and Services Tax rate.
     * @param pst The Provincial Sales Tax rate.
     * @param hst The Harmonized Sales Tax rate.
     */
    public CanadaTaxRate(Integer taxRateId, double gst, double pst, double hst) {
        this.taxRateId = taxRateId;
        this.gst = gst;
        this.pst = pst;
        this.hst = hst;
    }

    /**
     * Gets the tax rate ID.
     *
     * @return The tax rate ID as an Integer.
     */
    public Integer getTaxrateId() {
        return taxRateId;
    }

    /**
     * Sets the tax rate ID.
     *
     * @param taxRateId The tax rate ID as an Integer.
     */
    public void setTaxrateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * Gets the GST (Goods and Services Tax) value.
     *
     * @return The GST value as a double.
     */
    public double getGst() {
        return gst;
    }

    /**
     * Sets the GST (Goods and Services Tax) value.
     *
     * @param gst The GST value as a double.
     */
    public void setGst(double gst) {
        this.gst = gst;
    }

    /**
     * Gets the PST (Provincial Sales Tax) value.
     *
     * @return The PST value as a double.
     */
    public double getPst() {
        return pst;
    }

    /**
     * Sets the PST (Provincial Sales Tax) value.
     *
     * @param pst The PST value as a double.
     */
    public void setPst(double pst) {
        this.pst = pst;
    }

    /**
     * Gets the HST (Harmonized Sales Tax) value.
     *
     * @return The HST value as a double.
     */
    public double getHst() {
        return hst;
    }

    /**
     * Gets the associated Location object.
     *
     * @return The associated Location object.
     */
    public void setHst(double hst) {
        this.hst = hst;
    }

    /**
     * Sets the associated Location object.
     *
     * @param location The associated Location object.
     */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Generates a hash code based on the tax rate ID.
     *
     * @return An int value representing the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxRateId != null ? taxRateId.hashCode() : 0);
        return hash;
    }

    /**
     * Compares this CanadaTaxRate object with the specified object for
     * equality.
     *
     * @param object The object to be compared with this CanadaTaxRate object.
     * @return true if the objects are equal, false otherwise.
     */
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

    /**
     * Generates a String representation of the CanadaTaxRate object.
     *
     * @return A String representation of the CanadaTaxRate object.
     */
    @Override
    public String toString() {
        return "models.CanadaTaxRate[ taxRateId=" + taxRateId + " ]";
    }
}
