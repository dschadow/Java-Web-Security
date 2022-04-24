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
package de.dominikschadow.webappsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

/**
 * Servlet which sets the <code>Content-Security-Policy-Report-Only</code> response header and reports
 * any JavaScript code that would have been stopped by the policy.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "WithCSPReportingServlet", urlPatterns = {"/WithCSPReportingServlet"})
public class WithCSPReportingServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(WithCSPReportingServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Processing POST request with Content Security Policy Reporting");

        String name = request.getParameter("reporting");
        LOGGER.info("Received {} as POST parameter", name);

        response.setContentType("text/html");
        // the following line only reports violations and does not block anything
        response.setHeader("Content-Security-Policy-Report-Only", "default-src 'self'; report-uri CSPReporting");

        // use the following line to activate the policy and still report all violations
        //response.setHeader("Content-Security-Policy", "default-src 'self'; report-uri CSPReporting");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" /></head>");
            out.println("<body>");
            out.println("<h1>Ch07_CSP</h1>");
            out.println("<h2>With Content Security Policy Reporting</h2>");
            out.println("<p><strong>Hello</strong> " + name + "</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
