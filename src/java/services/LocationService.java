/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import dataaccess.LocationDB;
import models.Canadalocation;
import java.util.List;
/**
 *
 * @author Kyle Helmer
 * Connection between servlet and DB
 */
public class LocationService {
    
    private LocationDB locDB = new LocationDB();
    
    public List<Canadalocation> getAllCan() throws Exception{
        
        return locDB.getAllCan();
    }
    
    public Canadalocation getCan(String locationCode){
        return locDB.getCan(locationCode);
    }
    
     public List<Canadalocation> getAllCanRegion(String region) throws Exception{
        return locDB.getAllCanRegion(region);
    }
    
    public void insertCan(String locationCode, String country, String region){
        Canadalocation canLoc = new Canadalocation(locationCode, country, region);
        locDB.insertCan(canLoc);
    }
    
    public void updateCan(String locationCode, String country, String region){
        Canadalocation canLoc = locDB.getCan(locationCode);
        canLoc.setCountry(country);
        canLoc.setRegion(region);
        locDB.updateCan(canLoc);   
    }
    
    public void deleteCan(String locationCode){
        Canadalocation canLoc = locDB.getCan(locationCode);
        locDB.deleteCan(canLoc);
    }
    
    
}
