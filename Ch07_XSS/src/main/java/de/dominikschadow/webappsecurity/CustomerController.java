package de.dominikschadow.webappsecurity;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dominikschadow.webappsecurity.beans.CustomerBean;
import de.dominikschadow.webappsecurity.model.Customer;

@Model
public class CustomerController {
    private Customer currentCustomer;
    
    @Inject
    private CustomerBean customerBean;

    @Named
    @Produces
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    @PostConstruct
    private void createNewCustomer() {
        currentCustomer = new Customer();
    }

    public void saveNewCustomer() {
        customerBean.saveCustomer(currentCustomer);
    }
}
