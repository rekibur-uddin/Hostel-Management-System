package hostal.application;

import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish Connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostalmanagementsystem", "root", "Rekib123");
            s = c.createStatement();
            System.out.println("Database Connection Successful");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to understand the issue
        }
    }
}
