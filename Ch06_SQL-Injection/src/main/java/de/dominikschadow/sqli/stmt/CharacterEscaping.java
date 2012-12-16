package de.dominikschadow.sqli.stmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.OracleCodec;

import de.dominikschadow.sqli.domain.Customer;

public class CharacterEscaping {
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        CharacterEscaping sample = new CharacterEscaping();
        List<Customer> customers = sample.findCustomer("Maier");

        for (Customer customer : customers) {
            System.out.println("Customer data:");
            System.out.println(customer.toString());
        }
    }

    public List<Customer> findCustomer(String custName) {
        String safeCustName = ESAPI.encoder().encodeForSQL(new OracleCodec(), custName);
        
        String query = "SELECT * FROM customer WHERE name = '" + safeCustName + "'";
        List<Customer> customers = new ArrayList<Customer>();

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
}
