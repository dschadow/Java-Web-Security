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
 * Servlet which sets the <code>Content-Security-Policy</code> response header and stops any JavaScript code entered
 * in the textfield.
 * Any entered script-tag will not be rendered any more in the result page.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "WithCSPServlet", urlPatterns = {"/WithCSPServlet"})
public class WithCSPServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(WithCSPServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Processing POST request with Content Security Policy");

        String name = request.getParameter("protected");
        LOGGER.info("Received {} as POST parameter", name);

        response.setContentType("text/html");
        response.setHeader("Content-Security-Policy", "default-src 'self'");
        // following line enables unsafe inline JavaScript
//        response.setHeader("Content-Security-Policy", "default-src 'self' 'unsafe-inline'");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" /></head>");
            out.println("<body>");
            out.println("<h1>Ch07_CSP</h1>");
            out.println("<h2>With Content Security Policy</h2>");
            out.println("<p><strong>Hello</strong> " + name + "</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
