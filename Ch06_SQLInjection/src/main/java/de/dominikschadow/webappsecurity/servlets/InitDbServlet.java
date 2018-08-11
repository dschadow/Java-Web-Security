package de.dominikschadow.webappsecurity.servlets;

import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static de.dominikschadow.webappsecurity.servlets.HibernateUtil.getSessionFactory;

/**
 * Servlet to initialize the database with some sample data.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "InitDbServlet", urlPatterns = {"/"})
public class InitDbServlet extends HttpServlet {
    @Override
    public void init() {
        Session session = getSessionFactory().openSession();
        session.close();
    }
}
