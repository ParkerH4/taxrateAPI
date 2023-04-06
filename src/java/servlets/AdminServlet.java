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
import models.UsTaxRate;
import services.LocationService;
import services.TaxRateService;
import services.Utilites;

/**
 * AdminServlet servlet that services the /admin endpoint containing GET and POST methods.
 * 
 */
public class AdminServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchField = request.getParameter("searchField");
        Utilites util = new Utilites();
        TaxRateService trs = new TaxRateService();
        LocationService ls = new LocationService();
        CanadaTaxRate canTax = null;
        UsTaxRate usTax = null;
        try {
            if (util.isCanCode(searchField)) {
                canTax = trs.getCan(searchField);
                request.setAttribute("taxRate", canTax);
                request.setAttribute("searchedLoc", "canada");
            } else if (util.isUSCode(searchField)) {
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
        Utilites util = new Utilites();
        
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
                        } else if (util.isCanCode(editLocationCode)) {
                            trs.updateCan(editCountry, editRegion, editLocationCode, editTaxRate1, editTaxRate2, editTaxRate3);
                            session.setAttribute("message", "Updated Tax Rate!");
                        } else if (util.isUSCode(editLocationCode) && editTaxRate2 == null && editTaxRate3 == null) {
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
                        } else if (util.isCanCode(deleteField)) {
                            trs.deleteCan(deleteField);
                            session.setAttribute("message", "Tax rate " + deleteField + " has been deleted.");
                        } else if (util.isUSCode(deleteField)) {
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
