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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.reference.IntegerAccessReferenceMap;

/**
 * 
 * @author Dominik Schadow
 */
public class IntegerAccessReferenceMapSample {
    private IntegerAccessReferenceMap accounts = new IntegerAccessReferenceMap();

    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            return;
        }

        IntegerAccessReferenceMapSample sample = new IntegerAccessReferenceMapSample();
        
        User userA = new User();
        userA.setAccountId(42);
        userA.setName("Marvin");
        
        List<String> accountReferences = sample.loadAccountsForUser(userA);
        sample.retrieveAccounts(accountReferences);
    }
    
    public void retrieveAccounts(List<String> accountReferences) {
       
        try {
            for (String accountReference : accountReferences) {
                Account account = accounts.getDirectReference(accountReference);
                System.out.println("Indirect reference " + accountReference + " --> Account " + account.getName());
            }
            
            // access not existing account
            Account failure = accounts.getDirectReference("3");
            System.out.println("Indirect reference 3 --> Account " + failure.getName());
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
        
    }

    public List<String> loadAccountsForUser(User user) {
        List<String> accountReferences = queryAccounts(user);
        
        System.out.println("User " + user.getName() + " has " + accountReferences.size() + " accounts");
        
        return accountReferences;
    }

    private List<String> queryAccounts(User user) {
        String query = "SELECT * FROM accounts WHERE owner_id = ?";
        List<String> accountReferences = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/accountsDB; shutdown=true", "sa", "");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getAccountId());
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setName(rs.getString(2));
                account.setType(rs.getString(3));
                account.setOwnerId(rs.getInt(4));
                
                accounts.addDirectReference(account);
                accountReferences.add(accounts.getIndirectReference(account));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accountReferences;
    }
}
