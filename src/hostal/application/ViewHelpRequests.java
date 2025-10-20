package hostal.application;

import javax.swing.*;                 // For JFrame, JButton, JTable, etc.
import java.awt.*;                   // For Layouts, Color, etc.
import java.awt.event.*;             // For ActionEvent and ActionListener
import java.sql.*;                   // For JDBC and database connections
import javax.swing.table.*;          // For DefaultTableModel

public class ViewHelpRequests extends JFrame {
    JTable helpRequestsTable;

    public ViewHelpRequests() {
        setTitle("Help Requests");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Request ID", "Username", "Description", "Timestamp"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            Conn conn = new Conn();
            String query = "SELECT request_id, username, description, timestamp FROM help_requests";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("request_id"),
                    rs.getString("username"),
                    rs.getString("description"),
                    rs.getString("timestamp")
                });
            }
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        helpRequestsTable = new JTable(model);
        add(new JScrollPane(helpRequestsTable));
    }
}
