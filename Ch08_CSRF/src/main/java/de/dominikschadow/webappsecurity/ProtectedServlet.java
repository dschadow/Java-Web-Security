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
package de.dominikschadow.webappsecurity;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dominikschadow.webappsecurity.csrf.CSRFTokenHandler;

/**
 * @author Dominik Schadow
 */
@WebServlet(name = "ProtectedServlet", urlPatterns = {"/ProtectedServlet"})
public class ProtectedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProtectedServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("Processing protected POST request");

        response.setContentType("text/html");
        
        try {
            if (!CSRFTokenHandler.isValid(request)) {
                System.out.println("CSRF token is invalid");
                response.setStatus(401);

                try (PrintWriter out = response.getWriter()) {
                    out.println("CSRF token is invalid");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                return;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("CSRF token is valid");

        String name = request.getParameter("name");
        System.out.println("Protected: Received " + name + " as POST parameter");

        try (PrintWriter out = response.getWriter()) {
            out.println("Received " + name + " as POST parameter");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
