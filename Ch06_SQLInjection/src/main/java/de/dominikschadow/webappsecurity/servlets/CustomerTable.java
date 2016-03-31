package de.dominikschadow.webappsecurity.servlets;

import de.dominikschadow.webappsecurity.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class to create the customers list and to create the HTML table with all queried customers.
 *
 * @author Dominik Schadow
 */
public class CustomerTable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTable.class);

    public static List<Customer> extractCustomers(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustId(rs.getInt(1));
            customer.setName(rs.getString(2));
            customer.setStatus(rs.getString(3));
            customer.setOrderLimit(rs.getInt(4));

            customers.add(customer);
        }

        return customers;
    }

    public static void writeCustomers(HttpServletResponse response, String name, List<Customer> customers) {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" /></head>");
            out.println("<body>");
            out.println("<h1>Chapter 06 - SQL Injection</h1>");
            out.println("<p><strong>Input</strong> " + name + "</p>");
            out.println("<h2>Customer Data</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Status</th>");
            out.println("<th>Order Limit</th>");
            out.println("</tr>");

            for (Customer customer : customers) {
                out.println("<tr>");
                out.println("<td>" + customer.getCustId() + "</td>");
                out.println("<td>" + customer.getName() + "</td>");
                out.println("<td>" + customer.getStatus() + "</td>");
                out.println("<td>" + customer.getOrderLimit() + "</td>");
                out.println("</tr>");
            }

            out.println("<table>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
