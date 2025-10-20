package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentDashboard extends JFrame implements ActionListener {
    private String username;
    private JLabel welcomeLabel, fullNameLabel, mobileLabel, classLabel, fatherNameLabel, motherNameLabel, emailLabel, roomLabel;
    private JButton attendanceButton, payBillButton, requestHelpButton, logoutButton;

    public StudentDashboard(String username) {
        this.username = username;

        // Frame settings
        setTitle("Hostel of BBK College - Student Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(60, 120, 180));
        JLabel titleLabel = new JLabel("Hostel of BBK College");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        Font labelFont = new Font("Tahoma", Font.BOLD, 20);
        Font detailFont = new Font("Tahoma", Font.PLAIN, 18);

        // Welcome Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        welcomeLabel = new JLabel("Welcome, " + username + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        mainPanel.add(welcomeLabel, gbc);

        // Load Student Details
        loadStudentDetails(mainPanel, gbc, labelFont, detailFont);

        // Action Buttons
        gbc.gridy++;
        gbc.gridwidth = 1;

        attendanceButton = new JButton("Mark Attendance");
        attendanceButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        attendanceButton.addActionListener(this);
        mainPanel.add(attendanceButton, gbc);

        gbc.gridx++;
        payBillButton = new JButton("Pay Hostel Bill");
        payBillButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        payBillButton.addActionListener(this);
        mainPanel.add(payBillButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        requestHelpButton = new JButton("Request Help");
        requestHelpButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        requestHelpButton.addActionListener(this);
        mainPanel.add(requestHelpButton, gbc);

        gbc.gridx++;
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        logoutButton.addActionListener(this);
        mainPanel.add(logoutButton, gbc);

        // Add main panel to the frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
    }

    private void loadStudentDetails(JPanel panel, GridBagConstraints gbc, Font labelFont, Font detailFont) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM students WHERE username = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                gbc.gridx = 0;
                gbc.gridy++;
                JLabel fullNameStaticLabel = new JLabel("Your Details here");
                fullNameStaticLabel.setFont(labelFont);
                panel.add(fullNameStaticLabel, gbc);

                gbc.gridx = 1;
                fullNameLabel = new JLabel(rs.getString("full_name"));
                fullNameLabel.setFont(detailFont);
                panel.add(fullNameLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel mobileStaticLabel = new JLabel("Mobile No:");
                mobileStaticLabel.setFont(labelFont);
                panel.add(mobileStaticLabel, gbc);

                gbc.gridx = 1;
                mobileLabel = new JLabel(rs.getString("mobile_no"));
                mobileLabel.setFont(detailFont);
                panel.add(mobileLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel classStaticLabel = new JLabel("Class:");
                classStaticLabel.setFont(labelFont);
                panel.add(classStaticLabel, gbc);

                gbc.gridx = 1;
                classLabel = new JLabel(rs.getString("class"));
                classLabel.setFont(detailFont);
                panel.add(classLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel fatherStaticLabel = new JLabel("Father's Name:");
                fatherStaticLabel.setFont(labelFont);
                panel.add(fatherStaticLabel, gbc);

                gbc.gridx = 1;
                fatherNameLabel = new JLabel(rs.getString("father_name"));
                fatherNameLabel.setFont(detailFont);
                panel.add(fatherNameLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel motherStaticLabel = new JLabel("Mother's Name:");
                motherStaticLabel.setFont(labelFont);
                panel.add(motherStaticLabel, gbc);

                gbc.gridx = 1;
                motherNameLabel = new JLabel(rs.getString("mother_name"));
                motherNameLabel.setFont(detailFont);
                panel.add(motherNameLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel emailStaticLabel = new JLabel("Email:");
                emailStaticLabel.setFont(labelFont);
                panel.add(emailStaticLabel, gbc);

                gbc.gridx = 1;
                emailLabel = new JLabel(rs.getString("email"));
                emailLabel.setFont(detailFont);
                panel.add(emailLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                JLabel roomStaticLabel = new JLabel("Room:");
                roomStaticLabel.setFont(labelFont);
                panel.add(roomStaticLabel, gbc);

                gbc.gridx = 1;
                roomLabel = new JLabel(rs.getString("room"));
                roomLabel.setFont(detailFont);
                panel.add(roomLabel, gbc);
            }

            conn.c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading student details: " + e.getMessage());
        }
    }

    @Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == attendanceButton) {
        new AttendancePage(username).setVisible(true); // Redirect to attendance page
    } else if (ae.getSource() == payBillButton) {
        new PaymentPage(username).setVisible(true); // Redirect to payment page
    } else if (ae.getSource() == requestHelpButton) {
        new HelpRequestPage(username).setVisible(true); // Redirect to help request page
    } else if (ae.getSource() == logoutButton) {
        setVisible(false);
        new Login().setVisible(true); // Redirect to Login page
    }
}


    public static void main(String[] args) {
        new StudentDashboard("testUsername").setVisible(true); // For testing
    }
}
