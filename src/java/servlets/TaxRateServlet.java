/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CanadaTaxRate;
import services.TaxRateService;
import viewmodels.CanadaTaxRateView;

public class TaxRateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Roughly implemented. Works as intended with 0 validation 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        TaxRateService trs = new TaxRateService();
        String rate = request.getParameter("rate");

        try {
            CanadaTaxRate can = trs.getCan(rate);

            CanadaTaxRateView canView = new CanadaTaxRateView(can);

            String str = gson.toJson(canView);
            response.getWriter().write(str);

        } catch (Exception ex) {
            Logger.getLogger(TaxRateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //grab the content from request
    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Utility methods.">
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
    // </editor-fold>
}
