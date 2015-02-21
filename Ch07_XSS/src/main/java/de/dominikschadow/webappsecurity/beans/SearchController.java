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
import org.apache.commons.lang3.StringUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Searches customers by the given customer name. The search string can be passed via
 * <code>customerName</code> setter method or as a <code>customerName</code> parameter.
 *
 * @author Dominik Schadow
 */
@ManagedBean(name = "search")
@RequestScoped
public class SearchController {
    private String customerName;
    private CustomerDAO customerDAO;
    private List<Customer> customers;

    public SearchController() {
        customerDAO = new CustomerDAO();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public String search() {
        if (StringUtils.isEmpty(customerName)) {
            Map<String, String> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            customerName = requestMap.get("customerName");
        }

        Customer search = new Customer();
        search.setName(customerName);

        customers = customerDAO.findCustomers(search);

        return "searchCustomer";
    }
}
