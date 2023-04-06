/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kyle Helmer
 */
public class Utilites {

    public Utilites() {
    }
    
     /**
     * This method will check the argument if it is in postal code or zip code
     * format. If it is one of those, will remove all whitespace from the string
     * and return it. If it is not in a valid format, will return the original
     * location code parameter passed into it.
     *
     * @param locationCode The string representation of a US Zip Code or Canada
     * Postal Code
     * @return the formatted zipcode, postalcode, if they are valid. Returns the
     * locationCode if it is neither us or canada.
     *
     */
    public String formatLocationCode(String locationCode) {
        String parsedCode = "";

        if (isUSCode(locationCode)) {
            //return formatted zip code
            parsedCode = locationCode.trim()
                    // get rid of whitespace
                    .replaceAll("\\s+", "")
                    // first 5 digits only
                    .substring(0, 5);
        } else if (isCanCode(locationCode)) {
            // return formatted postal code
            parsedCode = locationCode.trim()
                    .toUpperCase()
                    // get rid of whitespace
                    .replaceAll("\\s+", "")
                    // keep only first 3 characters
                    .substring(0, 3);
        } else {
            return locationCode;
        }
        return parsedCode;
    }

    /**
     * Checks to see if the parameter passed into it matches a regular
     * expression for a US zip code.
     *
     * @param locationCode The string representation of a US Zip Code or Canada
     * Postal Code
     * @return true if it is in US Zip Code format
     */
    public boolean isUSCode(String locationCode) {
        // sanitize input

        if (locationCode == null) {
            return false;
        }
        locationCode = locationCode.trim()
                // get rid of all whitespace
                .replaceAll("\\s+", "");
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(locationCode);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the parameter passed into it matches a regular
     * expression for the first 3 characters of a Canada postal code.
     *
     * @param locationCode The string representation of a US Zip Code or Canada
     * Postal Code
     * @return true if it is in Canada Postal Code format
     */
    public boolean isCanCode(String locationCode) {
        // sanitize input
        if (locationCode == null) {
            return false;
        }
        locationCode = locationCode.toUpperCase()
                .trim()
                // get rid of all whitespace
                .replaceAll("\\s+", "")
                // keep only first 3 characters
                .substring(0, 3);

        String regex = "^(?:[a-zA-Z]\\d[a-zA-Z])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(locationCode);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    
}
