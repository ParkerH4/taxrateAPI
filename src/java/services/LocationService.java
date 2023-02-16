/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dataaccess.LocationDB;
import java.util.ArrayList;

import java.util.List;
import models.CanadaTaxRate;
import models.Location;
/**
 *
 * @author Kyle Helmer
 * 
 */
public class LocationService {
    
    private LocationDB locDB = new LocationDB();
    
    
    
    public List<Location> getAll() throws Exception{
        
        return locDB.getAll();
    }
    
    public Location getLoc(String locationCode){
        return locDB.getLoc(locationCode);
    }
    
     public List<Location> getAllRegion(String region) throws Exception{
        return locDB.getAllRegion(region);
    }
    
    public void insertLoc(String locationCode, String country, String region){
        Location loc = new Location(locationCode, country, region);
        ArrayList<CanadaTaxRate> list = new ArrayList<>();
        loc.setCanadaTaxRateList(list);
        locDB.insertLoc(loc);
    }
    
    public void updateLoc(String locationCode, String country, String region){
        Location loc = locDB.getLoc(locationCode);
        loc.setCountry(country);
        loc.setRegion(region);
        locDB.updateLoc(loc);   
    }
    
    public void deleteLoc(String locationCode){
        Location loc = locDB.getLoc(locationCode);
        locDB.deleteLoc(loc);
    }
    
   
    
    
}
