package de.dominikschadow.webappsecurity.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.dominikschadow.webappsecurity.model.Customer;

@Stateless
public class CustomerBean {

    @Inject
    private EntityManager em;

    public void saveCustomer(Customer customer) {
        em.persist(customer);
    }

    public List<Customer> loadAllCustomers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
        Root<Customer> member = criteria.from(Customer.class);
        criteria.select(member).orderBy(cb.asc(member.get("id")));

        return em.createQuery(criteria).getResultList();
    }

}
