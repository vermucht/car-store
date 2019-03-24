package carstore.servlet;

import carstore.constants.ConstContext;
import carstore.store.NewUserStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet to add and edit users.
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
@WebServlet({"/addUser", "/editUser"})
public class CreateUserServlet extends HttpServlet {
    /**
     * Logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(CreateUserServlet.class);
    /**
     * Hibernate session factory.
     */
    private SessionFactory hbFactory;
    /**
     * Utils to perform database transactions.
     */
    private NewUserStore userStore;

    /**
     * Initiates fields.
     */
    @Override
    public void init() {
        var ctx = this.getServletContext();
        this.hbFactory = (SessionFactory) ctx.getAttribute(ConstContext.SESSION_FACTORY.v());
        this.userStore = (NewUserStore) ctx.getAttribute(ConstContext.USER_STORE.v());
    }

    /**
     * Shows form to add or edit user.
     *
     * @param req  Request object.
     * @param resp Response object.
     * @throws ServletException In case of problems.
     * @throws IOException      In case of problems.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view" + "/editUser.jsp").forward(req, resp);
    }

    /**
     * Adds new user by given data from form.
     *
     * @param req  Request object.
     * @param resp Response object.
     * @throws ServletException In case of problems.
     * @throws IOException      In case of problems.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        var params = this.getParametersMap(req);
//        var login = params.get("login");
//        try (var session = this.hbFactory.openSession()) {
//            if (this.userStore.contains(session, login)) {
//                req.setAttribute("error", String.format("Login (%s) already exists", login));
//                this.doGet(req, resp);
//            } else {
//                var changed = this.userStore.createAndStore(session, params);
//                var resultMsg = String.format("User (%s) created/updated", changed.getLogin());
//                resp.sendRedirect(String.format(
//                        "%s?success=%s", this.getServletContext().getContextPath(), resultMsg));
//            }
//        }
    }

    /**
     * Returns map with parameters for user creation.
     *
     * @param req Request object to take parameters from.
     * @return Map of parameters.
     */
    private HashMap<String, String> getParametersMap(HttpServletRequest req) {
        var params = new HashMap<String, String>();
        params.put("login", req.getParameter("user_login"));
        params.put("password", req.getParameter("user_password"));
        params.put("phone", req.getParameter("user_phone"));
        return params;
    }
}
