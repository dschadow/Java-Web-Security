/*
 * Copyright (C) 2013 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of Java-Web-Security
.
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

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simple CSP-Reporting servlet to receive and print out any JSON style CSP report with violations.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "CSPReporting", urlPatterns = {"/CSPReporting"})
public class CSPReporting extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CSPReporting.class);

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.info("CSP-Reporting-Servlet");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            StringBuilder responseBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = reader.readLine()) != null) {
                responseBuilder.append(inputStr);
            }

            LOGGER.info("REPORT " + responseBuilder.toString());

            JSONObject json = new JSONObject(responseBuilder.toString());
            JSONObject cspReport = json.getJSONObject("csp-report");
            LOGGER.info("document-uri: " + cspReport.getString("document-uri"));
            LOGGER.info("referrer: " + cspReport.getString("referrer"));
            LOGGER.info("blocked-uri: " + cspReport.getString("blocked-uri"));
            LOGGER.info("violated-directive: " + cspReport.getString("violated-directive"));
            LOGGER.info("source-file: " + cspReport.getString("source-file"));
            LOGGER.info("script-sample: " + cspReport.getString("script-sample"));
            LOGGER.info("line-number: " + cspReport.getString("line-number"));
        } catch (IOException | JSONException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
