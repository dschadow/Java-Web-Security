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
package de.dominikschadow.webappsecurity.daos;

import de.dominikschadow.webappsecurity.domain.Customer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static de.dominikschadow.webappsecurity.daos.HibernateUtil.getSessionFactory;

/**
 * Loads customers from the in-memory-database for the managed beans.
 *
 * @author Dominik Schadow
 * @see de.dominikschadow.webappsecurity.beans.CustomerController
 */
public class CustomerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Customer");
            customers = query.list();

            LOGGER.info("Found {} customers", customers.size());
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return customers;
    }

    public void createCustomer(Customer customer) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(customer);
            tx.commit();
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    public List<Customer> findCustomers(Customer customer) {
        List<Customer> customers = new ArrayList<>();

        try (Session session = getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.like("name", "%" + customer.getName() + "%"));

            customers = criteria.list();

            LOGGER.info("Found {} customers", customers.size());
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return customers;
    }
}
