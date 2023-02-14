/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dataaccess.LocationDB;

import java.util.List;
import models.Location;
/**
 *
 * @author Kyle Helmer
 * 
 */
public class LocationService {
    
    private LocationDB locDB = new LocationDB();
    
    
    
    public List<Location> getAllCan() throws Exception{
        
        return locDB.getAllCan();
    }
    
    public Location getCan(String locationCode){
        return locDB.getCan(locationCode);
    }
    
     public List<Location> getAllCanRegion(String region) throws Exception{
        return locDB.getAllCanRegion(region);
    }
    
    public void insertCan(String locationCode, String country, String region){
        Location canLoc = new Location(locationCode, country, region);
        locDB.insertCan(canLoc);
    }
    
    public void updateCan(String locationCode, String country, String region){
        Location canLoc = locDB.getCan(locationCode);
        canLoc.setCountry(country);
        canLoc.setRegion(region);
        locDB.updateCan(canLoc);   
    }
    
    public void deleteCan(String locationCode){
        Location canLoc = locDB.getCan(locationCode);
        locDB.deleteCan(canLoc);
    }
    
    //Us related methods 
    
    
    public List<Uslocation> getAllUs() throws Exception{
        
        return locDB.getAllUs();
    }
    
    public Uslocation getUs(String zipCode){
       return locDB.getUs(zipCode);
    }
    
     public List<Uslocation> getAllUsRegion(String state) throws Exception{
        return locDB.getAllUsState(state);
    }
    
    public void insertUs(String zipCode, String country, String state){
        Uslocation usLoc = new Uslocation(zipCode, country, state);
        locDB.insertUs(usLoc);
    }
    
    public void updateUs(String zipCode, String country, String state){
       Uslocation usLoc = locDB.getUs(zipCode);
        usLoc.setCountry(country);
        usLoc.setState(state);
        locDB.updateUs(usLoc);   
    }
    
    public void deleteUs(String zipCode){
        Uslocation usLoc = locDB.getUs(zipCode);
        locDB.deleteUs(usLoc);
    }
    
    
}
