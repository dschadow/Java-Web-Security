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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean to access an account by account reference (RandomAccessReferenceMap). Only accounts belonging to the
 * current user are contained in this map.
 *
 * @author Dominik Schadow
 */
@ManagedBean(name = "accountRandom")
@SessionScoped
public class AccountRandomController implements Serializable {
    private List<String> accountReferences = new ArrayList<>();
    private String raAccountId = "";
    private int userId = 42;
    private transient AccountsRandomDAO dao;

    public String getRaAccountId() {
        return raAccountId;
    }

    public void setRaAccountId(String raAccountId) {
        this.raAccountId = raAccountId;
    }

    public int getUserId() {
        return userId;
    }

    public Account getAccount() {
        return dao.retrieveAccount(raAccountId);
    }

    public List<String> getAccountReferences() {
        return accountReferences;
    }

    public AccountRandomController() {
        User currentUser = new User();
        currentUser.setUserId(userId);

        dao = new AccountsRandomDAO();
        accountReferences = dao.loadAccountsForUser(currentUser);
    }

    public String show() {
        return "accountRandom";
    }
}
