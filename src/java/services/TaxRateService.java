package services;

import dataaccess.LocationDB;
import dataaccess.TaxRateDB;
import java.util.List;
import models.Canadalocation;
import models.Canadataxrate;

/**
 *
 * @author William Lau
 */
public class TaxRateService {

    private TaxRateDB taxDB = new TaxRateDB();
    private LocationDB locDB = new LocationDB();

    public Canadataxrate getCan(String locationCode) throws Exception {

        return taxDB.getCan(locationCode);
    }

    public List<Canadataxrate> getAllCan() throws Exception {

        return taxDB.getAllCan();
    }

    public void insertCan(String country, String region, String locationCode, String gst, String pst, String hst) throws Exception {

        double parseGst = Double.parseDouble(gst);
        double parseHst = Double.parseDouble(hst);
        double parsePst = Double.parseDouble(pst);

        Canadalocation newLoc = new Canadalocation(locationCode, country, region);
        locDB.insertCan(newLoc);

        Canadataxrate canTaxRate = new Canadataxrate(0, parseGst, parsePst, parseHst);
        canTaxRate.setLocationCode(newLoc);

        taxDB.insertCan(canTaxRate);
    }

    public void deleteCan(String locationCode) throws Exception {
        
        Canadalocation canLoc = locDB.getCan(locationCode);
        Canadataxrate canTaxRate = taxDB.getCan(locationCode);
        
        canLoc.getCanadataxrateList().remove(canTaxRate);
        
        taxDB.deleteCan(canTaxRate);
        locDB.deleteCan(canLoc);
    }

    public void updateCan(String country, String region, String locationCode, String gst, String pst, String hst) throws Exception {

        double parseGst = Double.parseDouble(gst);
        double parseHst = Double.parseDouble(hst);
        double parsePst = Double.parseDouble(pst);
        
        Canadalocation canLoc = locDB.getCan(locationCode);
        canLoc.setCountry(country);
        canLoc.setRegion(region);
        
        Canadataxrate canTaxRate = (Canadataxrate) taxDB.getCan(locationCode);

        canTaxRate.setGst(parseGst);
        canTaxRate.setPst(parsePst);
        canTaxRate.setHst(parseHst);
        
        taxDB.updateCan(canTaxRate);
        locDB.updateCan(canLoc);
    }

}
