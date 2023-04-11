package viewmodels;

import models.UsTaxRate;

/**
 * The UsTaxRateView class is a view model for displaying US tax rates as a JSON
 * in a more pleasant format.
 *
 * It contains properties that represent the values of a CanadaTaxRate object.
 */
public class UsTaxRateView {

    private int taxRateId;
    private double stateTax;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Default constructor for the UsTaxRateView class.
     */
    public UsTaxRateView() {
    }

    /**
     * Constructs a UsTaxRateView object from a USTaxRate object.
     *
     * @param taxRate the UsTaxRate object to convert to a view model.
     */
    public UsTaxRateView(UsTaxRate taxRate) {
        taxRateId = taxRate.getTaxrateId();
        stateTax = taxRate.getStateTax();
        zipCode = taxRate.getLocation().getLocationCode();
        state = taxRate.getLocation().getRegion();
        country = "US";
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
     * Gets the state tax rate.
     *
     * @return the state tax rate.
     */
    public double getStateTax() {
        return stateTax;
    }

    /**
     * Sets the stateTax rate.
     *
     * @param stateTax the state tax rate to set.
     */
    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    /**
     * Gets the state attribute.
     *
     * @return the state of the US Tax Rate View Model.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state attribute.
     *
     * @param state the state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the zip code to set.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the country.
     *
     * @return the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns a string representing a UsTaxRateView object.
     *
     * @return the string representing a UsTaxRateView object.
     */
    @Override
    public String toString() {
        return "UsTaxRateView{" + "taxRateId=" + taxRateId
                + ", stateTax=" + stateTax + ", state="
                + state + ", zipCode=" + zipCode + ", country=" + country
                + '}';
    }

}
