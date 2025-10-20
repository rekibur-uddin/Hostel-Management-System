package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendancePage extends JFrame implements ActionListener {
    String username;
    JButton markAttendanceButton, backButton;

    public AttendancePage(String username) {
        this.username = username;
        setTitle("Attendance");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Mark Attendance");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        buttonPanel.add(markAttendanceButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == markAttendanceButton) {
            try {
                Conn conn = new Conn();
                String query = "INSERT INTO attendance (username, timestamp) VALUES (?, ?)";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Attendance marked successfully!");
                conn.c.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
        }
    }
}
