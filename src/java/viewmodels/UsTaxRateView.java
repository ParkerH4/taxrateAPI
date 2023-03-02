/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;

import models.UsTaxRate;

/**
 *
 * @author Kyle Helmer
 */
public class UsTaxRateView {

    private int taxRateId;
    private double stateTax;
    private String state;
    private String zipCode;

    public UsTaxRateView() {
    }

    public UsTaxRateView(UsTaxRate taxRate) {
        taxRateId = taxRate.getTaxrateId();
        stateTax = taxRate.getStateTax();
        zipCode = taxRate.getLocation().getLocationCode();
        state = taxRate.getLocation().getRegion();
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

    @Override
    public String toString() {
        return "UsTaxRateView{" + "taxRateId=" + taxRateId + ", stateTax=" + stateTax + ", state=" + state + ", zipCode=" + zipCode + '}';
    }

}
