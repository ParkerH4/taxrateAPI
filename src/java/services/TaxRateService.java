package services;

import dataaccess.LocationDB;
import dataaccess.CanadaTaxRateDB;
import dataaccess.UsTaxRateDB;
import java.util.List;
import models.Location;
import models.CanadaTaxRate;
import models.UsTaxRate;

/**
 *
 * @author William Lau
 */
public class TaxRateService {

    private CanadaTaxRateDB canTaxDB = new CanadaTaxRateDB();
    private UsTaxRateDB usTaxDB = new UsTaxRateDB();
    private LocationDB locDB = new LocationDB();
    private LocationService  ls = new LocationService();

    //Canada tax rates
    public CanadaTaxRate getCan(String locationCode) throws Exception {

        return canTaxDB.getCan(locationCode);
    }

    public List<CanadaTaxRate> getAllCan() throws Exception {

        return canTaxDB.getAllCan();
    }

    public void insertCan(String country, String region, String locationCode, String gst, String pst, String hst) throws Exception {

        double parseGst = Double.parseDouble(gst);
        double parseHst = Double.parseDouble(hst);
        double parsePst = Double.parseDouble(pst);

        ls.insertLoc(locationCode, country, region);
        
        Location newLoc = ls.getLoc(locationCode);
        
        CanadaTaxRate canTaxRate = new CanadaTaxRate(0, parseGst, parsePst, parseHst);
        canTaxRate.setLocation(newLoc);
        
        newLoc.getCanadaTaxRateList().add(canTaxRate);
        
        locDB.updateLoc(newLoc);
        canTaxDB.insertCan(canTaxRate);
    }

    public void deleteCan(String locationCode) throws Exception {
        
        Location canLoc = locDB.getLoc(locationCode);
        CanadaTaxRate canTaxRate = canTaxDB.getCan(locationCode);
        
        canLoc.getCanadaTaxRateList().clear();
        
        canTaxDB.deleteCan(canTaxRate);
        locDB.deleteLoc(canLoc);
    }

    public void updateCan(String country, String region, String locationCode, String gst, String pst, String hst) throws Exception {

        double parseGst = Double.parseDouble(gst);
        double parseHst = Double.parseDouble(hst);
        double parsePst = Double.parseDouble(pst);
        
        Location canLoc = locDB.getLoc(locationCode);
        canLoc.setCountry(country);
        canLoc.setRegion(region);
        
        CanadaTaxRate canTaxRate = (CanadaTaxRate) canTaxDB.getCan(locationCode);
        
        canTaxRate.setGst(parseGst);
        canTaxRate.setPst(parsePst);
        canTaxRate.setHst(parseHst);
        
        canTaxDB.updateCan(canTaxRate);
        canLoc.getCanadaTaxRateList().clear();
        canLoc.getCanadaTaxRateList().add(canTaxRate);
        locDB.updateLoc(canLoc);
    }
    
    //Us tax rates
    public UsTaxRate getUs(String locationCode) throws Exception {

        return usTaxDB.getUs(locationCode);
    }

    public List<UsTaxRate> getAllUs() throws Exception {

        return usTaxDB.getAllUs();
    }

    public void insertUs(String country, String region, String locationCode, String stateTax) throws Exception {

        double parseStateTax = Double.parseDouble(stateTax);

        
        ls.insertLoc(locationCode, country, region);
        Location newLoc = ls.getLoc(locationCode);
       

        UsTaxRate usTaxRate = new UsTaxRate(0, parseStateTax);
        usTaxRate.setLocation(newLoc);
        
        locDB.updateLoc(newLoc);
        usTaxDB.insertUs(usTaxRate);
    }

    public void deleteUs(String locationCode) throws Exception {
        
        Location usLoc = locDB.getLoc(locationCode);
        UsTaxRate usTaxRate = usTaxDB.getUs(locationCode);
        
        usLoc.getUsTaxRateList().remove(usTaxRate);
        
        usTaxDB.deleteUs(usTaxRate);
        locDB.deleteLoc(usLoc);
    }

    public void updateUs(String country, String region, String locationCode, String stateTax) throws Exception {

        double parseStateTax = Double.parseDouble(stateTax);
        
        Location usLoc = locDB.getLoc(locationCode);
        usLoc.setCountry(country);
        usLoc.setRegion(region);
        
        UsTaxRate usTaxRate = (UsTaxRate) usTaxDB.getUs(locationCode);

        usTaxRate.setStateTax(parseStateTax);
        
        usTaxDB.updateUs(usTaxRate);
        usLoc.getUsTaxRateList().clear();
        usLoc.getUsTaxRateList().add(usTaxRate);
        locDB.updateLoc(usLoc);
    }
    
  

}
