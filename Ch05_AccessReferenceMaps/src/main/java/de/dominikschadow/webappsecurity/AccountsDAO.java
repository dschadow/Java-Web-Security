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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM accounts WHERE account_id = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection("jdbc:hsqldb:res:/accountsDB; shutdown=true", "sa", "")) {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setName(rs.getString(2));
                account.setType(rs.getString(3));
                account.setOwnerId(rs.getInt(4));

                return account;
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
        }

        return null;
    }

    private List<String> queryAccounts(int userId) {
        String query = "SELECT account_id FROM accounts WHERE owner_id = ?";
        List<String> accountReferences = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection con = DriverManager.getConnection("jdbc:hsqldb:res:/accountsDB; shutdown=true", "sa", "")) {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                accountReferences.add(rs.getString(1));
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
        }

        return accountReferences;
    }
}
