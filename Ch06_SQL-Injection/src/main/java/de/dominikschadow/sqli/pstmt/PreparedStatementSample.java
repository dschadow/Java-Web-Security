package de.dominikschadow.sqli.pstmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.dominikschadow.sqli.domain.Customer;

public class PreparedStatementSample {
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
            return;
        }
        
        PreparedStatementSample sample = new PreparedStatementSample();
        
        // normal sample for customer "Maier"
        List<Customer> customers = sample.findCustomer("Maier");
        sample.printCustomer(customers);
        
        // failing SQL injection sample with "' OR '1' = '1"
        customers = sample.findCustomer("' OR '1' = '1");
        sample.printCustomer(customers);
        
        // normal sample for customer "Maier"
        List<Integer> customerIds = sample.findCustomerIds(100000, "A");
        sample.printCustomerIds(customerIds);
        
        // failing SQL injection sample with "' OR '1' = '1"
        customerIds = sample.findCustomerIds(100000, "' OR '1' = '1");
        sample.printCustomerIds(customerIds);
        
        sample.insertCustomer(6, "Test", 1, "F");
    }

    private List<Customer> findCustomer(String custName) {
        System.out.println("Kundenname " + custName);
        
        String query = "SELECT * FROM customer WHERE name = ?";
        List<Customer> customers = new ArrayList<Customer>();
        
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, custName);

            ResultSet rs = pstmt.executeQuery();

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
                if (pstmt != null) {
                    pstmt.close();
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

    private List<Integer> findCustomerIds(int orderLimit, String status) {
        System.out.println("Order Limit " + orderLimit + ", Status " + status);
        
        String query = "SELECT cust_id FROM customer WHERE order_limit < ? AND status = ?";
        List<Integer> customerIds = new ArrayList<Integer>();

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, orderLimit);
            pstmt.setString(2, status);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);

                customerIds.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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

        return customerIds;
    }

    private void insertCustomer(int id, String name, int orderLimit, String status) {
        System.out.println("Kundenname "+ name + ", Order Limit " + orderLimit + ", Status " + status);
        
        String insert = "INSERT INTO customer (cust_id, name, status, order_limit) VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            pstmt = con.prepareStatement(insert);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, status);
            pstmt.setInt(4, orderLimit);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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
    }

    private void printCustomer(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Keine Kundendaten gefunden");
            return;
        }
        
        System.out.println("Kundendaten:");
        
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    private void printCustomerIds(List<Integer> ids) {
        if (ids.isEmpty()) {
            System.out.println("Keine Kundendaten gefunden");
            return;
        }
        
        System.out.println("Kundendaten:");
        
        for (Integer id : ids) {
            System.out.println(id);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/customerDB; shutdown=true", "sa", "");
    }
}
