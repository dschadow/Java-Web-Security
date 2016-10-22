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

import de.dominikschadow.webappsecurity.domain.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static de.dominikschadow.webappsecurity.HibernateUtil.getSessionFactory;

/**
 * Loads accounts from the in-memory-database for the unprotected managed bean.
 *
 * @author Dominik Schadow
 * @see AccountController
 */
public class AccountsDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsDAO.class);

    public List<String> getAccountsForUser(int userId) {
        return queryAccounts(userId);
    }

    public Account loadAccount(int id) {
        return queryAccount(id);
    }

    private Account queryAccount(int id) {
        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Account WHERE accountId = :id");
            query.setParameter("id", id);

            return (Account) query.uniqueResult();
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return null;
    }

    private List<String> queryAccounts(int userId) {
        List<String> accountReferences = new ArrayList<>();

        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createNativeQuery("SELECT accountId FROM account WHERE ownerId = :id");
            query.setParameter("id", userId);

            accountReferences = query.list();

            LOGGER.info("Found {} account references", accountReferences.size());
        } catch (HibernateException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return accountReferences;
    }
}
