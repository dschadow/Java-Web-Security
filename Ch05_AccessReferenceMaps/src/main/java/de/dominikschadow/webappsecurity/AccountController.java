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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean to access an account by account id. Does not verify the given account id and therefore does not
 * provide any protection.
 *
 * @author Dominik Schadow
 */
@ManagedBean(name = "account")
@SessionScoped
public class AccountController implements Serializable {
    private Account account;
    private int userId = 42;
    private int accountId = 1;
    private transient AccountsDAO dao;
    private List<String> accountReferences = new ArrayList<>();

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
    }

    public List<String> getAccountReferences() {
        return accountReferences;
    }

    public AccountController() {
        dao = new AccountsDAO();

        accountReferences = dao.getAccountsForUser(userId);
    }

    public String show() {
        account = dao.loadAccount(accountId);

        return "account";
    }
}
