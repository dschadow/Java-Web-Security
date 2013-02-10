package de.dominikschadow.sqli.hql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import de.dominikschadow.sqli.domain.Customer;

public class HQLStatementSample {

    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        HQLStatementSample sample = new HQLStatementSample();
        List<Customer> customers = sample.findCustomer("Maier");

        for (Customer customer : customers) {
            System.out.println("Customer data:");
            System.out.println(customer.toString());
        }
    }

    @SuppressWarnings({"null", "unused"})
    public List<Customer> findCustomer(String custName) {
        String statement = "SELECT * FROM customer WHERE name = '" + custName + "'";
        List<Customer> customers = new ArrayList<Customer>();

        Session session = null;
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM customer WHERE name = '" + custName + "'");
//        Query query = session.createQuery("FROM customer WHERE name = '" + custName + "'");
        
//        Query query = session.createQuery("FROM customer WHERE name = :name ");
//        query.setParameter("name", "Maier");
//        customers = query.list();
        
        return customers;
    }

}
