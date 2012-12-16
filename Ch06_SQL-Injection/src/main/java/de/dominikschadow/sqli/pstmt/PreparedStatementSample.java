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
        List<Customer> customers = sample.findCustomer("Maier");

        for (Customer customer : customers) {
            System.out.println("Customer data:");
            System.out.println(customer.toString());
        }
    }

    public List<Customer> findCustomer(String custName) {
        String query = "SELECT * FROM customer WHERE name = '" + custName + "'";
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

    public List<Integer> getCustomerIds(int orderLimit, String status) {
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

    public void insertCustomer(String name, int orderLimit, String status) {
        String insert = "INSERT INTO customer (name, status, order_limit) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            pstmt = con.prepareStatement(insert);
            pstmt.setString(1, name);
            pstmt.setString(2, status);
            pstmt.setInt(3, orderLimit);

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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/customerDB; shutdown=true", "sa", "");
    }
}
