package hostal.application;

import javax.swing.*;                 // For JFrame, JButton, JTable, etc.
import java.awt.*;                   // For Layouts, Color, etc.
import java.awt.event.*;             // For ActionEvent and ActionListener
import java.sql.*;                   // For JDBC and database connections
import javax.swing.table.*;          // For DefaultTableModel


public class ViewAttendance extends JFrame {
    JTable attendanceTable;

    public ViewAttendance() {
        setTitle("Attendance Records");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Attendance ID", "Username", "Timestamp"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            Conn conn = new Conn();
            String query = "SELECT attendance_id, username, timestamp FROM attendance";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("attendance_id"),
                    rs.getString("username"),
                    rs.getString("timestamp")
                });
            }
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        attendanceTable = new JTable(model);
        add(new JScrollPane(attendanceTable));
    }
}
