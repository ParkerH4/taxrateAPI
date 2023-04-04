package servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AuthService;
import services.UserService;

public class AuthServlet extends HttpServlet {

    //1 day
    private static final long EXPIRATION_TIME = 86400000;
    private static final PrivateKey PRIVATE_KEY = AuthService.getInstance().getPrivateKey();



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

        // Validate username and password
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService us = new UserService();
            User user = us.login(username, password);

            // user that is null, is not logged in
            if (user != null) {
                String jwt = Jwts.builder()
                        .setSubject(username)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(PRIVATE_KEY, SignatureAlgorithm.RS256)
                        .compact();

                response.setContentType("application/json");
                response.getWriter().write("{ \"token\": \"" + jwt + "\" }");

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid credentials.");
            }

        } catch (Exception ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
