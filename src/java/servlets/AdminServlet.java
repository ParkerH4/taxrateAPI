package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.CanadaTaxRate;
import models.UsTaxRate;
import services.LocationService;
import services.TaxRateService;

/**
 * AdminServlet servlet that services the /admin endpoint containing GET and POST methods.
 * 
 */
public class AdminServlet extends HttpServlet {

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

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchField = request.getParameter("searchField");

        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();
        CanadaTaxRate canTax = null;
        UsTaxRate usTax = null;
        try {
            if (isCanCode(searchField)) {
                canTax = trs.getCan(searchField);
                request.setAttribute("taxRate", canTax);
                request.setAttribute("searchedLoc", "canada");
            } else if (isUSCode(searchField)) {
                usTax = trs.getUs(searchField);
                request.setAttribute("usTaxRate", usTax);
                request.setAttribute("searchedLoc", "usa");
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String searchString = request.getParameter("searchField");

        // variables for a tax rate
        String country = request.getParameter("searchCountry");
        String region = request.getParameter("searchRegion");
        String locationCode = request.getParameter("searchLocationCode");
        String taxRate1 = request.getParameter("searchTaxRate1");
        String taxRate2 = request.getParameter("searchTaxRate2");
        String taxRate3 = request.getParameter("searchTaxRate3");

        String addCountry = request.getParameter("addCountry");
        String addRegion = request.getParameter("addRegion");
        String addLocationCode = request.getParameter("addLocationCode");
        String addTaxRate1 = request.getParameter("addTaxRate1");
        String addTaxRate2 = request.getParameter("addTaxRate2");
        String addTaxRate3 = request.getParameter("addTaxRate3");

        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();
        CanadaTaxRate canTax = new CanadaTaxRate();
        UsTaxRate usTax = new UsTaxRate();
        if (action != null) {
            switch (action) {

                case "add":
                    if (addCountry == null || addRegion == null || addLocationCode == null || addTaxRate1 == null || addTaxRate2 == null || addTaxRate3 == null
                            || addCountry.equals("") || addRegion.equals("") || addLocationCode.equals("") || addTaxRate1.equals("") || addTaxRate2.equals("") || addTaxRate3.equals("")) {
                        session.setAttribute("message", "Error adding TaxRate. Null or empty fields in the adding form.");
                        response.sendRedirect("admin");
                        return;
                    } else {
                        try {
                            trs.insertCan(addCountry, addRegion, addLocationCode, addTaxRate1, addTaxRate2, addTaxRate3);
                            session.setAttribute("message", "Added Tax Rate!");
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    response.sendRedirect("admin");
                    return;

                     case "addUs":
                    if (addCountry == null || addRegion == null || addLocationCode == null || addTaxRate1 == null
                            || addCountry.equals("") || addRegion.equals("") || addLocationCode.equals("") || addTaxRate1.equals("")) {
                        session.setAttribute("message", "Error adding TaxRate. Null or empty fields in the adding form.");
                        response.sendRedirect("admin");
                        return;
                    } else {
                        try {
                            trs.insertUs(addCountry, addRegion, addLocationCode, addTaxRate1);
                            session.setAttribute("message", "Added Tax Rate!");
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    response.sendRedirect("admin");
                    return;

                case "edit":
                    try {
                        String editCountry = request.getParameter("editCountry");
                        String editRegion = request.getParameter("editRegion");
                        String editLocationCode = request.getParameter("editLocationCode");
                        String editTaxRate1 = request.getParameter("editTaxRate1");
                        String editTaxRate2 = request.getParameter("editTaxRate2");
                        String editTaxRate3 = request.getParameter("editTaxRate3");

                        if (editCountry == null || editRegion == null || editLocationCode == null || editTaxRate1 == null || editTaxRate2 == null || editTaxRate3 == null
                                || editCountry.equals("") || editRegion.equals("") || editLocationCode.equals("") || editTaxRate1.equals("") || editTaxRate2.equals("") || editTaxRate3.equals("")) {
                            session.setAttribute("message", "Error updating tax rate");
                            response.sendRedirect("admin");
                            return;
                        } else if (isCanCode(editLocationCode)) {
                            trs.updateCan(editCountry, editRegion, editLocationCode, editTaxRate1, editTaxRate2, editTaxRate3);
                            session.setAttribute("message", "Updated Tax Rate!");
                        } else if (isUSCode(editLocationCode) && editTaxRate2 == null && editTaxRate3 == null) {
                            trs.updateUs(editCountry, editRegion, editLocationCode, editTaxRate1);
                            session.setAttribute("message", "Updated Tax Rate!");
                        } else {

                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    response.sendRedirect("admin");
                    return;

                case "editUs":
                    try {
                        String editCountry = request.getParameter("editCountry");
                        String editRegion = request.getParameter("editRegion");
                        String editLocationCode = request.getParameter("editLocationCode");
                        String editTaxRate1 = request.getParameter("editTaxRate1");

                        if (editCountry == null || editRegion == null || editLocationCode == null || editTaxRate1 == null
                                || editCountry.equals("") || editRegion.equals("") || editLocationCode.equals("") || editTaxRate1.equals("")) {
                            session.setAttribute("message", "Error updating tax rate");
                            response.sendRedirect("admin");
                            return;
                        } else{
                            trs.updateUs(editCountry, editRegion, editLocationCode, editTaxRate1);
                            session.setAttribute("message", "Updated Tax Rate!");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    response.sendRedirect("admin");
                    return;

                case "delete": {
                    try {
                        String deleteField = request.getParameter("deleteField");

                        if (deleteField.equals("")) {
                            session.setAttribute("message", "Null values or empty strings not permitted in the input for deleting a tax rate.");
                        } else if (isCanCode(deleteField)) {
                            trs.deleteCan(deleteField);
                            session.setAttribute("message", "Tax rate " + deleteField + " has been deleted.");
                        } else if (isUSCode(deleteField)) {
                            trs.deleteUs(deleteField);
                            session.setAttribute("message", "Tax rate " + deleteField + " has been deleted.");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        session.setAttribute("message", "Error deleting tax rate. Exception thrown.");
                    }
                    break;
                }

                case "cancel":
                    session.setAttribute("message", "Cancelled. Changes were not saved.");
                    response.sendRedirect("admin");
                    return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
    }
}
