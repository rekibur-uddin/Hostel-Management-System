package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JComboBox<String> roleBox;
    JButton loginButton, signupAsStudentButton, signupAsAdminButton;

    public Login() {
        // Frame Configuration
        setTitle("Welcome to Hostel of BBK College - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Adding background color
        getContentPane().setBackground(new Color(240, 240, 240));
        JLabel titleLabel = new JLabel("Welcome to Hostel of BBK College");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        titleLabel.setBounds(360, 50, 600, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Central Panel
        JPanel panel = new JPanel();
        panel.setBounds(400, 150, 500, 400);
        panel.setLayout(null);
        panel.setBackground(new Color(43, 47, 51));
        panel.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 3, true)); // Stylish border
        add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(100, 80, 100, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 80, 200, 35);
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordLabel.setBounds(100, 130, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 130, 200, 35);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        roleLabel.setBounds(100, 180, 100, 30);
        roleLabel.setForeground(Color.WHITE);
        panel.add(roleLabel);

        String[] roles = {"Admin", "Student"};
        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(200, 180, 200, 35);
        roleBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(roleBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 250, 200, 40);
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        panel.add(loginButton);

        signupAsStudentButton = new JButton("Signup as Student");
        signupAsStudentButton.setBounds(80, 320, 150, 35);
        signupAsStudentButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        signupAsStudentButton.setBackground(new Color(34, 139, 34));
        signupAsStudentButton.setForeground(Color.WHITE);
        signupAsStudentButton.setFocusPainted(false);
        signupAsStudentButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupAsStudentButton.setBorder(BorderFactory.createEmptyBorder());
        signupAsStudentButton.addActionListener(this);
        panel.add(signupAsStudentButton);

        signupAsAdminButton = new JButton("Signup as Admin");
        signupAsAdminButton.setBounds(270, 320, 150, 35);
        signupAsAdminButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        signupAsAdminButton.setBackground(new Color(220, 20, 60));
        signupAsAdminButton.setForeground(Color.WHITE);
        signupAsAdminButton.setFocusPainted(false);
        signupAsAdminButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupAsAdminButton.setBorder(BorderFactory.createEmptyBorder());
        signupAsAdminButton.addActionListener(this);
        panel.add(signupAsAdminButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "";
                if (role.equals("Admin")) {
                    query = "SELECT * FROM admins WHERE username = ? AND password = ?";
                } else if (role.equals("Student")) {
                    query = "SELECT * FROM students WHERE username = ? AND password = ?";
                }

                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);

                    if (role.equals("Admin")) {
                        new AdminDashboard(rs.getString("username")).setVisible(true); // Sending to admin dashboard
                    } else {
                        new StudentDashboard(rs.getString("username")).setVisible(true); // Sending to student dashboard
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!");
                }

                conn.c.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (ae.getSource() == signupAsStudentButton) {
            setVisible(false);
            new StudentSignup().setVisible(true);
        } else if (ae.getSource() == signupAsAdminButton) {
            setVisible(false);
            new AdminSignup().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
