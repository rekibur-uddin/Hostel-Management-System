package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame implements ActionListener {
    JButton viewStudentsButton, viewAttendanceButton, viewPaymentsButton, viewHelpRequestsButton, logoutButton;
    JLabel welcomeLabel;

    public AdminDashboard(String username) {
        setTitle("Admin Dashboard - Hostel Management System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Opens the window maximized
        setLayout(new BorderLayout());

        // Fetch the full name from the database using the username
        String fullName = getAdminFullName(username);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(54, 54, 54));
        welcomeLabel = new JLabel("Welcome, Mr. " + fullName + " Sir! To Hostal Managment Of BBK Collage, Nagaon", JLabel.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Sidebar
        JPanel sidebarPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        viewStudentsButton = new JButton("View Students");
        viewStudentsButton.addActionListener(this);
        viewAttendanceButton = new JButton("View Attendance");
        viewAttendanceButton.addActionListener(this);
        viewPaymentsButton = new JButton("View Payments");
        viewPaymentsButton.addActionListener(this);
        viewHelpRequestsButton = new JButton("View Help Requests");
        viewHelpRequestsButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        sidebarPanel.add(viewStudentsButton);
        sidebarPanel.add(viewAttendanceButton);
        sidebarPanel.add(viewPaymentsButton);
        sidebarPanel.add(viewHelpRequestsButton);
        sidebarPanel.add(logoutButton);

        add(sidebarPanel, BorderLayout.WEST);

        // Content Area
        JLabel contentLabel = new JLabel("Please select an option from the sidebar.", JLabel.CENTER);
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(contentLabel, BorderLayout.CENTER);
    }

    // Method to fetch the full name based on username
    private String getAdminFullName(String username) {
        String fullName = "";
        try {
            // Create a connection to the database
            Conn conn = new Conn();
            // Query to get the full name of the admin based on the username
            String query = "SELECT full_name FROM admins WHERE username = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                fullName = rs.getString("full_name");
            }

            conn.c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return fullName;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == viewStudentsButton) {
            new ViewStudents().setVisible(true);
        } else if (ae.getSource() == viewAttendanceButton) {
            new ViewAttendance().setVisible(true);
        } else if (ae.getSource() == viewPaymentsButton) {
            new ViewPayments().setVisible(true);
        } else if (ae.getSource() == viewHelpRequestsButton) {
            new ViewHelpRequests().setVisible(true);
        } else if (ae.getSource() == logoutButton) {
            setVisible(false);
            new Login().setVisible(true); // Assuming Login class exists
        }
    }

    public static void main(String[] args) {
        new AdminDashboard("fullName").setVisible(true); // Replace with a valid admin username
    }
}
