package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminSignup extends JFrame implements ActionListener {
    JTextField fullNameField, mobileField, usernameField;
    JPasswordField passwordField;
    JComboBox<String> designationBox;
    JButton signupButton, backButton;

    public AdminSignup() {
        setTitle("Admin Signup - Hostel Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Admin Signup");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        titleLabel.setBounds(400, 50, 600, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JPanel panel = new JPanel();
        panel.setBounds(400, 150, 600, 400);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("Admin Details"));
        add(panel);

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(50, 50, 150, 30);
        panel.add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(200, 50, 300, 30);
        panel.add(fullNameField);

        JLabel mobileLabel = new JLabel("Mobile No:");
        mobileLabel.setBounds(50, 100, 150, 30);
        panel.add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(200, 100, 300, 30);
        panel.add(mobileField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 150, 150, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 150, 300, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 150, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 200, 300, 30);
        panel.add(passwordField);

        JLabel designationLabel = new JLabel("Designation:");
        designationLabel.setBounds(50, 250, 150, 30);
        panel.add(designationLabel);

        String[] designations = {"Teacher", "Hostel Manager", "Principal"};
        designationBox = new JComboBox<>(designations);
        designationBox.setBounds(200, 250, 200, 30);
        panel.add(designationBox);

        signupButton = new JButton("Signup");
        signupButton.setBounds(200, 300, 120, 40);
        signupButton.addActionListener(this);
        panel.add(signupButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 300, 120, 40);
        backButton.addActionListener(this);
        panel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signupButton) {
            String fullName = fullNameField.getText();
            String mobile = mobileField.getText();
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String designation = (String) designationBox.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO admins (full_name, mobile_no, username, password, designation) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, fullName);
                pst.setString(2, mobile);
                pst.setString(3, username);
                pst.setString(4, password);
                pst.setString(5, designation);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Admin Signup Successful!");
                conn.c.close();

                setVisible(false);
                new AdminDashboard(username).setVisible(true); // Redirect to AdminDashboard
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Login().setVisible(true); // Redirect back to Login
        }
    }

    public static void main(String[] args) {
        new AdminSignup().setVisible(true);
    }
}
