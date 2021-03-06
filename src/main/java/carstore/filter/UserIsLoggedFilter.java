package carstore.filter;

import carstore.constants.Attributes;
import carstore.constants.WebApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Checks if session contains logged user.
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
@WebFilter(filterName = "UserIsLoggedFilter")
public class UserIsLoggedFilter implements Filter {
    /**
     * Logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(UserIsLoggedFilter.class);

    /**
     * Initializes on filter creation.
     *
     * @param filterConfig Filter config object.
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Creates Hibernate session and saves it as request parameter.
     * When request object comes back from servlets, closes the session.
     *
     * @param request  Request object
     * @param response Response object.
     * @param chain    Filter chein.
     * @throws IOException      In case of problems working with servlets.
     * @throws ServletException In case of problems working with servlets.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        var loggedId = req.getSession(false).getAttribute(Attributes.ATR_LOGGED_USER_ID.v());
        if (loggedId != null) {
            this.checkIsLong(loggedId);
            chain.doFilter(request, response);
        } else {
            var resultMsg = "Please log in to add or edit cars";
            var codedMsg = URLEncoder.encode(resultMsg, StandardCharsets.UTF_8);
            var redirectPath = new StringBuilder()
                    .append((String) req.getServletContext().getAttribute(Attributes.ATR_CONTEXT_PATH.v()))
                    .append("?")
                    .append(WebApp.MSG_ERROR.v()).append("=").append(codedMsg)
                    .toString();
            resp.sendRedirect(redirectPath);
        }
    }

    /**
     * Checks if given object is of type long.
     *
     * @param userId User id object to check.
     * @throws ServletException If Object is not of type long.
     */
    private void checkIsLong(Object userId) throws ServletException {
        if (!(userId instanceof Long)) {
            throw new ServletException(String.format(
                    "Logged user id in session (%s) is not of type Long", userId));
        }
    }

    /**
     * Is called on filter destroy.
     */
    @Override
    public void destroy() {

    }
}
