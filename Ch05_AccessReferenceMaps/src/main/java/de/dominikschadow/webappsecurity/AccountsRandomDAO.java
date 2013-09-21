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

import org.apache.log4j.Logger;
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.IntegerAccessReferenceMap;
import org.owasp.esapi.reference.RandomAccessReferenceMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads accounts from the in-memory-database for the protected managed bean.
 *
 * @author Dominik Schadow
 * @see AccountBeanRandom
 */
public class AccountsRandomDAO {
    private RandomAccessReferenceMap accounts = new RandomAccessReferenceMap();
    private static final Logger LOGGER = Logger.getLogger(AccountsRandomDAO.class);

    public AccountsRandomDAO() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Failed to load db driver", ex);
        }
    }

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
        String query = "SELECT * FROM accounts WHERE owner_id = ?";
        List<String> accountReferences = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/accountsDB; shutdown=true", "sa", "");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getUserId());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setName(rs.getString(2));
                account.setType(rs.getString(3));
                account.setOwnerId(rs.getInt(4));

                accounts.addDirectReference(account);
                accountReferences.add(accounts.getIndirectReference(account));
            }
        } catch (SQLException ex) {
            LOGGER.error("SQL exception", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                LOGGER.error("Failed to close rs", ex);
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                LOGGER.error("Failed to close pstmt", ex);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                LOGGER.error("Failed to close con", ex);
            }
        }

        return accountReferences;
    }
}
