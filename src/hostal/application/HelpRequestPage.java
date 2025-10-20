package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HelpRequestPage extends JFrame implements ActionListener {
    String username;
    JTextArea descriptionArea;
    JButton submitButton, backButton;

    public HelpRequestPage(String username) {
        this.username = username;
        setTitle("Request Help");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Describe Your Problem");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String description = descriptionArea.getText();
            try {
                Conn conn = new Conn();
                String query = "INSERT INTO help_requests (username, description, timestamp) VALUES (?, ?, NOW())";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, description);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Help request submitted successfully!");
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
