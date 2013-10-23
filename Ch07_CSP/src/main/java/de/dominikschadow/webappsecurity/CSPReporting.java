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

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public CSPReporting() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("CSP-Reporting-Servlet");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject json = new JSONObject(responseStrBuilder.toString());
            System.out.println("JSON " + json.toString());

            JSONObject cspReport = json.getJSONObject("csp-report");
            System.out.println("document-uri: " + cspReport.getString("document-uri"));
            System.out.println("referrer: " + cspReport.getString("referrer"));
            System.out.println("blocked-uri: " + cspReport.getString("blocked-uri"));
            System.out.println("violated-directive: " + cspReport.getString("violated-directive"));
            System.out.println("source-file: " + cspReport.getString("source-file"));
            System.out.println("script-sample: " + cspReport.getString("script-sample"));
            System.out.println("line-number: " + cspReport.getString("line-number"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
