package services;

import dataaccess.TaxRateDB;
import java.util.List;
import models.Canadataxrate;

/**
 *
 * @author William Lau
 */
public class TaxRateService {
    
    private TaxRateDB taxDB = new TaxRateDB();
    
    public Canadataxrate getCan(String locationCode) throws Exception {
        
        return taxDB.getCan(locationCode);
    }
    
    public List<Canadataxrate> getAllCan() throws Exception {
        
        return taxDB.getAllCan();
    }
    
    public void insertCan (int taxrateId, double gst, double pst, double hst) throws Exception {
        
        Canadataxrate canTaxRates = new Canadataxrate(taxrateId, gst, pst, hst);
        taxDB.insertCan(canTaxRates);
    }
    
    public void deleteCan(String locationCode) throws Exception {
        
        Canadataxrate canTaxRates = taxDB.getCan(locationCode);
        taxDB.deleteCan(canTaxRates);
    }
    
    public void updateCan(String locationCode, double gst, double pst, double hst) throws Exception {
        
        Canadataxrate canTaxRates = taxDB.getCan(locationCode);
        canTaxRates.setGst(gst);
        canTaxRates.setPst(pst);
        canTaxRates.setHst(hst);
        taxDB.updateCan(canTaxRates);
    }
    
}
