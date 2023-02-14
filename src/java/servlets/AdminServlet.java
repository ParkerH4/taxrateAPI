package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.CanadaTaxRate;
import services.LocationService;
import services.TaxRateService;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String searchField = request.getParameter("searchfield");

        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();
        CanadaTaxRate canTax = null;

        try {
            canTax = trs.getCan(searchField);
            request.setAttribute("taxRate", canTax);

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
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locationCode = request.getParameter("locationCode");
        String taxRate1 = request.getParameter("taxRate1");
        String taxRate2 = request.getParameter("taxRate2");
        String taxRate3 = request.getParameter("taxRate3");

        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();
        CanadaTaxRate canTax = new CanadaTaxRate();

        if (action != null) {

            switch (action) {

                case "search":
                    if (searchString != null) {
                        try {
                            canTax = trs.getCan(searchString);

                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                case "add":
                    if (country == null || region == null || locationCode == null || taxRate1 == null || taxRate2 == null || taxRate3 == null
                            || country.equals("") || region.equals("") || locationCode.equals("") || taxRate1.equals("") || taxRate2.equals("") || taxRate3.equals("")) {
                        session.setAttribute("message", "Error adding TaxRate. Null or empty fields in the adding form.");
                        response.sendRedirect("admin");
                        return;
                    }

                     {
                        try {
                            trs.insertCan(country, region, locationCode, taxRate1, taxRate2, taxRate3);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    response.sendRedirect("admin");
                    return;

                case "update":
                    if (country == null || region == null || locationCode == null || taxRate1 == null || taxRate2 == null || taxRate3 == null
                            || country.equals("") || region.equals("") || locationCode.equals("") || taxRate1.equals("") || taxRate2.equals("") || taxRate3.equals("")) {
                        session.setAttribute("message", "Error updating tax rate");
                        response.sendRedirect("admin");
                        return;
                    }

                     {
                        try {
                            trs.updateCan(country, region, locationCode, taxRate1, taxRate2, taxRate3);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    session.setAttribute("message", "Updated Tax Rate!");
                    response.sendRedirect("admin");
                    return;

                case "delete": {
                    try {
                        if (locationCode.equals("") || locationCode == null) {
                            session.setAttribute("message", "Fuck bitch.");
                        } else {
                            trs.deleteCan(searchString);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        session.setAttribute("message", "Error deleting tax rate");
                    }
                }
                response.sendRedirect("admin");
                return;

                case "cancel":
                    session.setAttribute("message", "Cancelled. Changes were not saved.");
                    response.sendRedirect("admin");
                    return;

            }

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
    }
}
