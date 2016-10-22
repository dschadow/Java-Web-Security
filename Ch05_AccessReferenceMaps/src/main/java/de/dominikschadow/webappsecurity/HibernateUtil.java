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
import de.dominikschadow.webappsecurity.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Dominik Schadow
 */
public class HibernateUtil {
    static SessionFactory sessionFactory;

    /**
     * Util class, no constructor required.
     */
    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }

    public static List<Account> queryUserAccounts(User user) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("FROM Account WHERE ownerId = :id");
        query.setParameter("id", user.getUserId());

        List<Account> accounts = query.list();

        session.close();

        return accounts;
    }
}
