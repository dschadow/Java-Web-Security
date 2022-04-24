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

import de.dominikschadow.webappsecurity.token.CSRFTokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Basic protected servlet for GET and POST requests. Checks the CSRF-Token value to identify
 * CSRF attacks. Prints out all information to standard out and returns the received parameter
 * as response.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "ProtectedServlet", urlPatterns = {"/ProtectedServlet"})
public class ProtectedServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtectedServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.info("Processing protected GET request");

        response.setContentType("text/html");

        try {
            if (!CSRFTokenHandler.isValid(request)) {
                LOGGER.warn("CSRF token is invalid");
                response.setStatus(401);

                try (PrintWriter out = response.getWriter()) {
                    out.println("CSRF token is invalid");
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex);
                }

                return;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        LOGGER.info("CSRF token is valid");

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        LOGGER.info("Received {} and {} as GET parameter.", newPassword, confirmPassword);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Chapter 08 - CSRF</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Chapter 08 - CSRF</h1>");
            out.println("<p>Received <b>" + newPassword + "</b> and <b>" + confirmPassword + "</b> as GET parameter.</p>");
            out.println("<p><a href=\"requests-protected.html\">Back</a></p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.info("Processing protected POST request");

        response.setContentType("text/html");

        try {
            if (!CSRFTokenHandler.isValid(request)) {
                LOGGER.warn("CSRF token is invalid");
                response.setStatus(401);

                return;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        LOGGER.info("CSRF token is valid");

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        LOGGER.info("Received {} and {} as POST parameter.", newPassword, confirmPassword);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Chapter 08 - CSRF</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/styles.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Chapter 08 - CSRF</h1>");
            out.println("<p>Received <b>" + newPassword + "</b> and <b>" + confirmPassword + "</b> as POST parameter.</p>");
            out.println("<p><a href=\"requests-protected.html\">Back</a></p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
