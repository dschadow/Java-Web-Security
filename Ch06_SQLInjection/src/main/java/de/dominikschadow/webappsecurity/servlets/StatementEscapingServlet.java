/*
 * Copyright (C) 2015 Dominik Schadow, info@dominikschadow.de
 *
 * This file is part of the Java-Web-Security project.
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

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.OracleCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import static de.dominikschadow.webappsecurity.servlets.CustomerTable.extractCustomers;
import static de.dominikschadow.webappsecurity.servlets.CustomerTable.writeCustomers;

/**
 * Servlet using a normal Statement to query the in-memory-database.
 * User input is escaped with ESAPI and used in the SQL query afterwards.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "StatementEscapingServlet", urlPatterns = {"/StatementEscapingServlet"})
public class StatementEscapingServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementEscapingServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        LOGGER.info("Received {} as POST parameter", name);

        String safeName = ESAPI.encoder().encodeForSQL(new OracleCodec(), name);
        LOGGER.info("Escaped name is {}", safeName);

        String query = "SELECT * FROM customer WHERE name = '" + safeName + "' ORDER BY CUST_ID";

        LOGGER.info("Final SQL query {}", query);

        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:sqli", "sa", "sa"); Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery(query);

            writeCustomers(response, name, extractCustomers(rs));
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
