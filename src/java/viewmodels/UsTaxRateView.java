package viewmodels;

import models.UsTaxRate;

public class UsTaxRateView {

    private int taxRateId;
    private double stateTax;
    private String state;
    private String zipCode;
    private String country;

    public UsTaxRateView() {
    }

    public UsTaxRateView(UsTaxRate taxRate) {
        taxRateId = taxRate.getTaxrateId();
        stateTax = taxRate.getStateTax();
        zipCode = taxRate.getLocation().getLocationCode();
        state = taxRate.getLocation().getRegion();
        country = "US";
    }

    public int getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(int taxRateId) {
        this.taxRateId = taxRateId;
    }

    public double getStateTax() {
        return stateTax;
    }

    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    @Override
    public String toString() {
        return "UsTaxRateView{" + "taxRateId=" + taxRateId 
                + ", stateTax=" + stateTax + ", state=" 
                + state + ", zipCode=" + zipCode + ", country=" + country
                +'}';
    }

}
