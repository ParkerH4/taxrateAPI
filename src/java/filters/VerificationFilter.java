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

/**
 * The VerificationFilter class is an implementation of the Filter interface. It
 * is used to verify JWT tokens in the request header and to ensure that the
 * user is authorized to access the TaxRateServlet.
 */
public class VerificationFilter implements Filter {

    private static final PublicKey PUBLIC_KEY = AuthService.getInstance().getPublicKey();

    public VerificationFilter() {
    }

    /**
     * Executes the filtering process. If the user is authorized (logged in and
     * received the public key), they may access the TaxRateServlet, which
     * contains methods to interact with the TaxRate API.
     *
     * @param request The servlet request we are processing.
     * @param response The servlet response we are creating.
     * @param chain The filter chain we are processing.
     * @exception IOException If an input/output error occurs.
     * @exception ServletException If a servlet error occurs.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                // Verify the JWT using the public key
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(PUBLIC_KEY)
                        .build()
                        .parseClaimsJws(jwt);
                // If the JWT is valid, proceed
                chain.doFilter(request, response);
            } catch (JwtException e) {
                // Invalid JWT, return unauthorized status
                httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpRes.getWriter().write("Invalid token.");
            }
        } else {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    /**
     * Destroy method for this filter. Can be used to release any resources held
     * by the filter.
     */
    public void destroy() {
    }

    /**
     * Init method for this filter. Can be used to initialize any resources
     * required by the filter.
     *
     * @param filterConfig The filter configuration object used to initialize
     * the filter.
     */
    public void init(FilterConfig filterConfig) {
    }
}
