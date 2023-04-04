/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.security.PublicKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AuthService;

public class VerificationFilter implements Filter {

    private static final PublicKey PUBLIC_KEY = AuthService.getInstance().getPublicKey();
    
    public VerificationFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            System.out.println("VerificationFilter - JWT before verification: " + jwt);

            try {
                // Verify the JWT using the public key
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(PUBLIC_KEY)
                        .build()
                        .parseClaimsJws(jwt);

                // If the JWT is valid, proceed
                System.out.println("VerificationFilter - JWT is valid, proceed..");
                chain.doFilter(request, response);
            } catch (JwtException e) {
                // Invalid JWT, return unauthorized status
                httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpRes.getWriter().write("Invalid token");
                System.out.println("VerificationFilter - UNAUTHORIZED");

            }
        } else {
            chain.doFilter(request, response);
            System.out.println("VerificationFilter - In the else..");
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
    }
}
