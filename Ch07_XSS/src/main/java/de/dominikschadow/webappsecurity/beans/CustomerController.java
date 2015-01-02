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
package de.dominikschadow.webappsecurity.beans;

import de.dominikschadow.webappsecurity.daos.CustomerDAO;
import de.dominikschadow.webappsecurity.domain.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 *
 * @author Dominik Schadow
 */
@ManagedBean(name = "customer")
@RequestScoped
public class CustomerController {
    private Customer customer;
    private CustomerDAO customerDAO;

    public CustomerController() {
        customer = new Customer();
        customerDAO = new CustomerDAO();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomers() {
        return customerDAO.getAllCustomers();
    }

    public String save() {
        customerDAO.createCustomer(customer);

        return "showCustomers";
    }
}
