/*
 * Copyright (C) 2013 Dominik Schadow, dominikschadow@gmail.com This file is part of JavaWebAppSecurity. Licensed under
 * the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or
 * agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.dominikschadow.webappsecurity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dominik Schadow
 */
@WebServlet(name = "CSRFServlet", urlPatterns = {"/CSRFServlet"})
public class CSRFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSRFServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("Processing unprotected GET request");

        String name = request.getParameter("name");
        System.out.println("Unprotected: Received " + name + " as GET parameter");

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("Received " + name + " as GET parameter");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("Processing unprotected POST request");

        String name = request.getParameter("name");
        System.out.println("Unprotected: Received " + name + " as POST parameter");

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("Received " + name + " as GET parameter");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
