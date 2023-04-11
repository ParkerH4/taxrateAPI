package viewmodels;

import models.CanadaTaxRate;

/**
 * The CanadaTaxRateView class is a view model for displaying Canadian tax rates
 * as a JSON in a more pleasant format.
 *
 * It contains properties that represent the values of a CanadaTaxRate object.
 */
public class CanadaTaxRateView {

    private int taxRateId;
    private double gst;
    private double pst;
    private double hst;
    private String province;
    private String country;
    private String locationCode;

    /**
     * Default constructor for the CanadaTaxRateView class.
     */
    public CanadaTaxRateView() {
    }

    /**
     * Constructs a CanadaTaxRateView object from a CanadaTaxRate object.
     *
     * @param taxRate the CanadaTaxRate object to convert to a view model.
     */
    public CanadaTaxRateView(CanadaTaxRate taxRate) {
        taxRateId = taxRate.getTaxrateId();
        gst = taxRate.getGst();
        pst = taxRate.getPst();
        hst = taxRate.getHst();
        province = taxRate.getLocation().getRegion();
        country = "Canada";
        locationCode = taxRate.getLocation().getLocationCode();
    }

    /**
     * Gets the tax rate ID.
     *
     * @return the tax rate ID.
     */
    public int getTaxRateId() {
        return taxRateId;
    }

    /**
     * Sets the tax rate ID.
     *
     * @param taxRateId the tax rate ID to set.
     */
    public void setTaxRateId(int taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * Gets the GST rate.
     *
     * @return the GST rate.
     */
    public double getGst() {
        return gst;
    }

    /**
     * Sets the GST rate.
     *
     * @param gst the GST rate to set.
     */
    public void setGst(double gst) {
        this.gst = gst;
    }

    /**
     * Gets the PST rate.
     *
     * @return the PST rate.
     */
    public double getPst() {
        return pst;
    }

    /**
     * Sets the PST rate.
     *
     * @param pst the PST rate to set.
     */
    public void setPst(double pst) {
        this.pst = pst;
    }

    /**
     * Gets the HST rate.
     *
     * @return the HST rate.
     */
    public double getHst() {
        return hst;
    }

    /**
     * Sets the HST rate.
     *
     * @param hst the HST rate to set.
     */
    public void setHst(double hst) {
        this.hst = hst;
    }

    /**
     * Gets the province name.
     *
     * @return the province name.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province name.
     *
     * @param province the province name to set.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Gets the country name.
     *
     * @return the country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the location code.
     *
     * @return the location code.
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the location code.
     *
     * @param locationCode the location code to set.
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * Returns a string representing a CanadaTaxRateView object.
     *
     * @return the string representing a CanadaTaxRateView object.
     */
    @Override
    public String toString() {
        return "CanadaTaxRateView{" + "taxRateId=" + taxRateId
                + ", gst=" + gst + ", pst=" + pst + ", hst=" + hst
                + ", province=" + province + ", country=" + country
                + ", locationCode=" + locationCode + '}';
    }
}
