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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Loads customers from the in-memory-database for the managed beans.
 *
 * @author Dominik Schadow
 * @see de.dominikschadow.webappsecurity.beans.CustomerController
 */
public class CustomerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);

    public List<Customer> getAllCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Customer");
        @SuppressWarnings("unchecked")
        List<Customer> customers = query.list();

        LOGGER.info("Found {} customers", customers.size());

        session.close();

        return customers;
    }

    public void createCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(customer);
        tx.commit();
        session.close();
    }

    public List<Customer> findCustomers(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "%" + customer.getName()+ "%"));

        @SuppressWarnings("unchecked")
        List<Customer> customers = criteria.list();

        LOGGER.info("Found {} customers", customers.size());

        session.close();

        return customers;
    }
}
