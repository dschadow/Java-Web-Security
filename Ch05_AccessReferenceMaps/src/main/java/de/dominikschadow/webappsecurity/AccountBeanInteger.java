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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AccountBeanInteger {
    private List<String> accountReferences = new ArrayList<>();
    private int accountId = 1;
    private int userId = 42;
    private AccountsIntegerDAO dao;

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
        return dao.retrieveAccount(accountId);
    }

    public List<String> getAccountReferences() {
        return accountReferences;
    }

    @PostConstruct
    public void loadData() {
        User currentUser = new User();
        currentUser.setUserId(userId);

        dao = new AccountsIntegerDAO();
        accountReferences = dao.loadAccountsForUser(currentUser);
    }

    public String show() {
        return "/accountInteger.xhtml";
    }
}
