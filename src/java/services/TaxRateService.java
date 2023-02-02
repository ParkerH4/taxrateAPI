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
    
    public Canadataxrate getCan(int taxrateId) throws Exception {
        
        return taxDB.getCan(taxrateId);
    }
    
    public List<Canadataxrate> getAllCan() throws Exception {
        
        return taxDB.getAllCan();
    }
    
    public void insertCan (int taxrateId, double gst, double pst, double hst) throws Exception {
        
        Canadataxrate canTaxRates = new Canadataxrate(taxrateId, gst, pst, hst);
        taxDB.insertCan(canTaxRates);
    }
    
    public void deleteCan(int taxrateId) throws Exception {
        
        Canadataxrate canTaxRates = taxDB.getCan(taxrateId);
        taxDB.deleteCan(canTaxRates);
    }
    
    public void updateCan(int taxrateId, double gst, double pst, double hst) throws Exception {
        
        Canadataxrate canTaxRates = taxDB.getCan(taxrateId);
        canTaxRates.setGst(gst);
        canTaxRates.setPst(pst);
        canTaxRates.setHst(hst);
        taxDB.updateCan(canTaxRates);
    }
    
}
