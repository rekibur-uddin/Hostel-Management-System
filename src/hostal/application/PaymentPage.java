package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PaymentPage extends JFrame implements ActionListener {
    String username;
    JTextField amountField;
    JButton payButton, backButton;

    public PaymentPage(String username) {
        this.username = username;
        setTitle("Pay Bill");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pay Your Bill");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Amount: ₹3000.00"));
        amountField = new JTextField();
        formPanel.add(amountField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        payButton = new JButton("Pay");
        payButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        buttonPanel.add(payButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == payButton) {
            String amount = amountField.getText();
            try {
                Conn conn = new Conn();
                String query = "INSERT INTO payments (username, amount, timestamp) VALUES (?, ?, NOW())";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, username);
                pst.setDouble(2, Double.parseDouble(amount));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Payment successful!");
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
