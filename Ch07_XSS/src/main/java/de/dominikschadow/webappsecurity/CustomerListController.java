package de.dominikschadow.webappsecurity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dominikschadow.webappsecurity.beans.CustomerBean;
import de.dominikschadow.webappsecurity.model.Customer;

@RequestScoped
public class CustomerListController {
    private List<Customer> customerList;
    @Inject
    private CustomerBean customerBean;

    @Produces
    @Named
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @PostConstruct
    public void loadAllCustomers() {
        customerList = customerBean.loadAllCustomers();
    }
}
