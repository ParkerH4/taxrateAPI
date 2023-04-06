package services;

import dataaccess.LocationDB;
import dataaccess.CanadaTaxRateDB;
import dataaccess.UsTaxRateDB;
import java.util.List;
import models.Location;
import models.CanadaTaxRate;
import models.UsTaxRate;

/**
 * TaxRateService is a class responsible for managing tax rate data and
 * providing methods to interact with the CanadaTaxRateDB, UsTaxRateDB, and
 * LocationDB database classes.
 */
public class TaxRateService {

    private CanadaTaxRateDB canTaxDB = new CanadaTaxRateDB();
    private UsTaxRateDB usTaxDB = new UsTaxRateDB();
    private LocationDB locDB = new LocationDB();
    private LocationService ls = new LocationService();

    //CanadaTaxRate methods
    /**
     * Retrieves the CanadaTaxRate for a given location code.
     *
     * @param locationCode The location code to search for.
     * @return The CanadaTaxRate associated with the specified location code.
     * @throws Exception if an error occurs during the database query.
     */
    public CanadaTaxRate getCan(String locationCode) throws Exception {

        return canTaxDB.getCan(locationCode);
    }

    /**
     * Retrieves a list of all CanadaTaxRate records.
     *
     * @return A List of CanadaTaxRate objects.
     * @throws Exception if an error occurs during the database query.
     */
    public List<CanadaTaxRate> getAllCan() throws Exception {

        return canTaxDB.getAllCan();
    }

    /**
     * Inserts a new CanadaTaxRate record into the database via LocationDB and
     * CanadaTaxrateDB.
     *
     * @param country The country of the new tax rate.
     * @param region The region of the new tax rate.
     * @param locationCode The location code of the new tax rate.
     * @param gst The GST tax rate value.
     * @param pst The PST tax rate value.
     * @param hst The HST tax rate value.
     * @throws Exception if an error occurs during the database operation.
     */
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

    /**
     * Deletes a CanadaTaxRate record from the database.
     *
     * @param locationCode The location code of the tax rate to delete.
     * @throws Exception if an error occurs during the database operation.
     */
    public void deleteCan(String locationCode) throws Exception {

        Location canLoc = locDB.getLoc(locationCode);
        CanadaTaxRate canTaxRate = canTaxDB.getCan(locationCode);

        canLoc.getCanadaTaxRateList().clear();

        canTaxDB.deleteCan(canTaxRate);
        locDB.deleteLoc(canLoc);
    }

    /**
     * Updates an existing CanadaTaxRate record in the database.
     *
     * @param country The updated country value.
     * @param region The updated region value.
     * @param locationCode The location code of the tax rate to update.
     * @param gst The updated GST tax rate value.
     * @param pst The updated PST tax rate value.
     * @param hst The updated HST tax rate value.
     * @throws Exception if an error occurs during the database operation.
     */
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

    //USTaxRate methods
    /**
     * Retrieves the UsTaxRate for a given location code.
     *
     * @param locationCode The location code to search for.
     * @return The UsTaxRate associated with the specified location code.
     * @throws Exception if an error occurs during the database query.
     */
    public UsTaxRate getUs(String locationCode) throws Exception {

        return usTaxDB.getUs(locationCode);
    }

    /**
     * Retrieves a list of all UsTaxRate records.
     *
     * @return A List of UsTaxRate objects.
     * @throws Exception if an error occurs during the database query.
     */
    public List<UsTaxRate> getAllUs() throws Exception {

        return usTaxDB.getAllUs();
    }

    /**
     * Inserts a new UsTaxRate record into the database.
     *
     * @param country The country of the new tax rate.
     * @param region The region of the new tax rate.
     * @param locationCode The location code of the new tax rate.
     * @param stateTax The state tax rate value.
     * @throws Exception if an error occurs during the database operation.
     */
    public void insertUs(String country, String region, String locationCode, String stateTax) throws Exception {

        double parseStateTax = Double.parseDouble(stateTax);

        ls.insertLoc(locationCode, country, region);
        Location newLoc = ls.getLoc(locationCode);

        UsTaxRate usTaxRate = new UsTaxRate(0, parseStateTax);
        usTaxRate.setLocation(newLoc);
        
        newLoc.getUsTaxRateList().add(usTaxRate);
        
        locDB.updateLoc(newLoc);
        usTaxDB.insertUs(usTaxRate);
    }

    /**
     * Deletes a UsTaxRate record from the database.
     *
     * @param locationCode The location code of the tax rate to delete.
     * @throws Exception if an error occurs during the database operation.
     */
    public void deleteUs(String locationCode) throws Exception {

        Location usLoc = locDB.getLoc(locationCode);
        UsTaxRate usTaxRate = usTaxDB.getUs(locationCode);

        usLoc.getUsTaxRateList().remove(usTaxRate);

        usTaxDB.deleteUs(usTaxRate);
        locDB.deleteLoc(usLoc);
    }

    /**
     * Updates an existing UsTaxRate record in the database.
     *
     * @param country The updated country value.
     * @param region The updated region value.
     * @param locationCode The location code of the tax rate to update.
     * @param stateTax The updated state tax rate value.
     * @throws Exception if an error occurs during the database operation.
     */
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
