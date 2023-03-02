/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;

import models.CanadaTaxRate;

/**
 *
 * @author Kyle Helmer
 */
public class CanadaTaxRateView {

    private int taxRateId;
    private double gst;
    private double pst;
    private double hst;
    private String province;
    private String locationCode; 

    public CanadaTaxRateView() {
    }

    public CanadaTaxRateView(CanadaTaxRate taxRate) {
        taxRateId = taxRate.getTaxrateId();
        gst = taxRate.getGst();
        pst = taxRate.getPst();
        hst = taxRate.getHst();
        province = taxRate.getLocation().getRegion();
        locationCode = taxRate.getLocation().getLocationCode();
    }

    public int getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(int taxRateId) {
        this.taxRateId = taxRateId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    @Override
    public String toString() {
        return "CanadaTaxRateView{" + "taxRateId=" + taxRateId + ", gst=" + gst + ", pst=" + pst + ", hst=" + hst + ", province=" + province + ", locationCode=" + locationCode + '}';
    }

  

}
