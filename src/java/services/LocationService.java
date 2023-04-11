package services;

import dataaccess.LocationDB;
import java.util.List;
import models.Location;

/**
 * LocationService is a class responsible for managing location data and
 * providing methods to interact with the LocationDB database class.
 */
public class LocationService {

    /**
     * The instance of LocationDB used for accessing and modifying location
     * data.
     */
    private LocationDB locDB = new LocationDB();

    /**
     * Retrieves a list of all locations.
     *
     * @return A list of Location objects representing all locations.
     * @throws Exception if an error occurs during the database query.
     */
    public List<Location> getAll() throws Exception {

        return locDB.getAll();
    }

    /**
     * Retrieves a location by its location code.
     *
     * @param locationCode The location code to search for.
     * @return A Location object representing the location with the specified
     * location code.
     */
    public Location getLoc(String locationCode) {
        return locDB.getLoc(locationCode);
    }

    /**
     * Retrieves all locations for a specific region.
     *
     * @param region The region to filter locations by.
     * @return A list of Location objects representing all locations within the
     * specified region.
     * @throws Exception if an error occurs during the database query.
     */
    public List<Location> getAllRegion(String region) throws Exception {
        return locDB.getAllRegion(region);
    }

    /**
     * Inserts a new location into the database.
     *
     * @param locationCode The location code for the new location.
     * @param country The country of the new location.
     * @param region The region of the new location.
     */
    public void insertLoc(String locationCode, String country, String region) {
        Location loc = new Location(locationCode, country, region);
        locDB.insertLoc(loc);
    }

    /**
     * Updates an existing location with new data.
     *
     * @param locationCode The location code of the location to update.
     * @param country The new country for the location.
     * @param region The new region for the location.
     */
    public void updateLoc(String locationCode, String country, String region) {
        Location loc = locDB.getLoc(locationCode);
        loc.setCountry(country);
        loc.setRegion(region);
        locDB.updateLoc(loc);
    }

    /**
     * Deletes a location from the database.
     *
     * @param locationCode The location code of the location to delete.
     */
    public void deleteLoc(String locationCode) {
        Location loc = locDB.getLoc(locationCode);
        locDB.deleteLoc(loc);
    }

    /**
     * Validates if a location exists in the database.
     *
     * @param locationCode The location code to check for existence.
     * @return true if the location exists, false otherwise.
     */
    public boolean validLoc(String locationCode) {

        try {
            locDB.getLoc(locationCode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
