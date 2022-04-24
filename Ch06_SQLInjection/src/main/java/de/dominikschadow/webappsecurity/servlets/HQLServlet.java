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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static de.dominikschadow.webappsecurity.servlets.CustomerTable.writeCustomers;
import static de.dominikschadow.webappsecurity.servlets.HibernateUtil.getSessionFactory;

/**
 * Servlet using Hibernate Query Language (HQL) to query the in-memory-database.
 * User input is not modified and used directly in the HQL query.
 *
 * @author Dominik Schadow
 */
@WebServlet(name = "HQLServlet", urlPatterns = {"/HQLServlet"})
public class HQLServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(HQLServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        LOGGER.info("Received {} as POST parameter", name);

        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Customer WHERE name = :name ORDER BY custId");
            query.setParameter("name", name);

            writeCustomers(response, name, query.list());
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
