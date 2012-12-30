package de.dominikschadow.sqli.stmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.dominikschadow.sqli.domain.Customer;

public class StatementSample {
    public static void main(String[] args) {
        StatementSample sample = new StatementSample();
        
        // normal sample for customer "Maier"
        List<Customer> customers = sample.findCustomer("Maier");
        sample.printCustomer(customers);
        
        // SQL injection sample with "' OR '1' = '1"
        customers = sample.findCustomer("' OR '1' = '1");
        sample.printCustomer(customers);
    }

    private List<Customer> findCustomer(String custName) {
        String query = "SELECT * FROM customer WHERE name = '" + custName + "'";
        List<Customer> customers = new ArrayList<Customer>();
        
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
            return customers;
        }

        Connection con = null;
        Statement stmt = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/customerDB; shutdown=true", "sa", "");

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setStatus(rs.getString(3));
                customer.setOrderLimit(rs.getInt(4));
                
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }

    private void printCustomer(List<Customer> customers) {
        System.out.println("Customer data:");
        
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }
}
