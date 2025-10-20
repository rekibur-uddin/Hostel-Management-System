package hostal.application;

import javax.swing.*;                 // For JFrame, JButton, JTable, etc.
import java.awt.*;                   // For Layouts, Color, etc.
import java.awt.event.*;             // For ActionEvent and ActionListener
import java.sql.*;                   // For JDBC and database connections
import javax.swing.table.*;          // For DefaultTableModel


public class ViewPayments extends JFrame {
    JTable paymentsTable;

    public ViewPayments() {
        setTitle("Payment Records");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Payment ID", "Username", "Amount", "Timestamp"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            Conn conn = new Conn();
            String query = "SELECT payment_id, username, amount, timestamp FROM payments";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("payment_id"),
                    rs.getString("username"),
                    rs.getDouble("amount"),
                    rs.getString("timestamp")
                });
            }
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        paymentsTable = new JTable(model);
        add(new JScrollPane(paymentsTable));
    }
}
