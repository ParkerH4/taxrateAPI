package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CanadaTaxRate;
import models.UsTaxRate;
import services.TaxRateService;
import services.Utilities;
import viewmodels.CanadaTaxRateView;
import viewmodels.UsTaxRateView;

/**
 * TaxRateServlet is a servlet class that extends HttpServlet to handle
 * API calls for retrieving tax rates for both Canadian and US
 * locations.
 */
public class TaxRateServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.">
    /**
     * Handles the HTTP <code>GET</code> method for API calls made to the TaxRate API.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Gson gson = new Gson();
            TaxRateService trs = new TaxRateService();
            Utilities util = new Utilities();
            String locationCode = request.getParameter("locationCode");
            locationCode = util.formatLocationCode(locationCode);

            //Canada tax rate
            if (util.isCanCode(locationCode)) {
                CanadaTaxRate canRate = trs.getCan(locationCode);
                CanadaTaxRateView canView = new CanadaTaxRateView(canRate);
                String str = gson.toJson(canView);
                response.getWriter().write(str);
            //US tax rate
            } else if (util.isUSCode(locationCode)) {
                UsTaxRate usRate = trs.getUs(locationCode);
                UsTaxRateView usView = new UsTaxRateView(usRate);
                String str = gson.toJson(usView);
                response.getWriter().write(str);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Provided locationCode is neither a US Zip Code or Canada Postal Code.");
            }
        } catch (Exception ex) {
            Logger.getLogger(TaxRateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
