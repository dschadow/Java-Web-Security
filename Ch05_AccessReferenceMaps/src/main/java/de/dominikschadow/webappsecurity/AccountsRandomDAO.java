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
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.RandomAccessReferenceMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static de.dominikschadow.webappsecurity.HibernateUtil.queryUserAccounts;

/**
 * Loads accounts from the in-memory-database for the protected managed bean.
 *
 * @author Dominik Schadow
 * @see AccountRandomController
 */
public class AccountsRandomDAO {
    private RandomAccessReferenceMap accounts = new RandomAccessReferenceMap();
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsRandomDAO.class);

    public Account retrieveAccount(String accountReference) {
        try {
            return accounts.getDirectReference(accountReference);
        } catch (AccessControlException ex) {
            LOGGER.error("Access to " + accountReference + " denied", ex);

            return null;
        }
    }

    public List<String> loadAccountsForUser(User user) {
        return queryAccounts(user);
    }

    private List<String> queryAccounts(User user) {
        List<Account> ownAccounts = queryUserAccounts(user);
        LOGGER.info("Found {} account references", ownAccounts.size());

        List<String> accountReferences = new ArrayList<>();
        for (Account account : ownAccounts) {
            accounts.addDirectReference(account);
            accountReferences.add(accounts.getIndirectReference(account));
        }

        return accountReferences;
    }
}
