package de.dominikschadow.webappsecurity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.dominikschadow.webappsecurity.model.Customer;

public class CustomerListController {
    private List<Customer> customerList;
    @Inject
    private EntityManager em;

    @Produces
    @Named
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomer(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @PostConstruct
    public void loadAllCustomers() {
        customerList = getAllCustomers();
    }

    private List<Customer> getAllCustomers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> member = criteria.from(Customer.class);
        criteria.select(member).orderBy(cb.asc(member.get("id")));

        return em.createQuery(criteria).getResultList();
    }
}
