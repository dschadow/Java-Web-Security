/*
 * Copyright (C) 2013 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of JavaWebAppSecurity.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dominikschadow.webappsecurity.servlets;

import de.dominikschadow.webappsecurity.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "PreparedStatementServlet", urlPatterns = {"/PreparedStatementServlet"})
public class PreparedStatementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection con = null;

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public PreparedStatementServlet() {
        super();

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/customerDB; shutdown=true", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String name = request.getParameter("name");
        System.out.println("Received " + name + " as POST parameter");

        String query = "SELECT * FROM customer WHERE name = ? ORDER BY CUST_ID";
        List<Customer> customers = new ArrayList<>();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setStatus(rs.getString(3));
                customer.setOrderLimit(rs.getInt(4));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" /></head>");
            out.println("<body>");
            out.println("<h1>Ch06_SQLInjection - Prepared Statement</h1>");
            out.println("<p><strong>Input was </strong> " + name + "</p>");
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
            ex.printStackTrace();
        }
    }
}
