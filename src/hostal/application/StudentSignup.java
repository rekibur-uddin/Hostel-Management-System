package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentSignup extends JFrame implements ActionListener {
    JTextField fullNameField, mobileField, classField, fatherNameField, motherNameField, usernameField, emailField;
    JPasswordField passwordField;
    JComboBox<String> roomBox;
    JButton signupButton, backButton;

    public StudentSignup() {
        setTitle("Student Signup - Hostel Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Student Signup");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        Font labelFont = new Font("Tahoma", Font.BOLD, 20);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 18);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(labelFont);
        formPanel.add(fullNameLabel, gbc);

        gbc.gridx = 1;
        fullNameField = new JTextField(20);
        fullNameField.setFont(fieldFont);
        fullNameField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel mobileLabel = new JLabel("Mobile No:");
        mobileLabel.setFont(labelFont);
        formPanel.add(mobileLabel, gbc);

        gbc.gridx = 1;
        mobileField = new JTextField(20);
        mobileField.setFont(fieldFont);
        mobileField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);
        formPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        classField = new JTextField(20);
        classField.setFont(fieldFont);
        classField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(classField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fatherNameLabel = new JLabel("Father's Name:");
        fatherNameLabel.setFont(labelFont);
        formPanel.add(fatherNameLabel, gbc);

        gbc.gridx = 1;
        fatherNameField = new JTextField(20);
        fatherNameField.setFont(fieldFont);
        fatherNameField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(fatherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel motherNameLabel = new JLabel("Mother's Name:");
        motherNameLabel.setFont(labelFont);
        formPanel.add(motherNameLabel, gbc);

        gbc.gridx = 1;
        motherNameField = new JTextField(20);
        motherNameField.setFont(fieldFont);
        motherNameField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(motherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        usernameField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(new Dimension(300, 35));
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel roomLabel = new JLabel("Room Selection:");
        roomLabel.setFont(labelFont);
        formPanel.add(roomLabel, gbc);

        gbc.gridx = 1;
        String[] rooms = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        roomBox = new JComboBox<>(rooms);
        roomBox.setFont(fieldFont);
        roomBox.setPreferredSize(new Dimension(200, 35));
        formPanel.add(roomBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        signupButton.addActionListener(this);
        signupButton.setPreferredSize(new Dimension(200, 50));
        formPanel.add(signupButton, gbc);

        gbc.gridy++;
        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(200, 50));
        formPanel.add(backButton, gbc);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signupButton) {
            String fullName = fullNameField.getText();
            String mobile = mobileField.getText();
            String studentClass = classField.getText();
            String fatherName = fatherNameField.getText();
            String motherName = motherNameField.getText();
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String email = emailField.getText();
            String room = (String) roomBox.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO students (full_name, mobile_no, class, father_name, mother_name, username, password, email, room) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, fullName);
                pst.setString(2, mobile);
                pst.setString(3, studentClass);
                pst.setString(4, fatherName);
                pst.setString(5, motherName);
                pst.setString(6, username);
                pst.setString(7, password);
                pst.setString(8, email);
                pst.setString(9, room);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Student Signup Successful!");
                conn.c.close();

                setVisible(false);
                new StudentDashboard(username).setVisible(true); // Redirect to StudentDashboard
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Login().setVisible(true); // Redirect back to Login
        }
    }

    public static void main(String[] args) {
        new StudentSignup().setVisible(true);
    }
}
