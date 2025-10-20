package hostal.application;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;

public class ViewStudents extends JFrame {
    JTable studentsTable;
    DefaultTableModel model;

    public ViewStudents() {
        setTitle("Student Details");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Student ID", "Full Name", "Username", "Class", "Email", "Mobile No", "Room", "Action"};
        model = new DefaultTableModel(columns, 0);

        try {
            Conn conn = new Conn();
            String query = "SELECT student_id, full_name, username, class, email, mobile_no, room FROM students";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("full_name"),
                    rs.getString("username"),
                    rs.getString("class"),
                    rs.getString("email"),
                    rs.getString("mobile_no"),
                    rs.getString("room"),
                    "Remove"
                });
            }
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentsTable = new JTable(model);
        studentsTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        studentsTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        add(new JScrollPane(studentsTable));
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Remove");
            setBackground(Color.RED); // Set background color to red
            setForeground(Color.WHITE); // Set text color to white
            setOpaque(true);  // Make sure button background is opaque
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private String username;
        private int studentId;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        @Override
        public Object getCellEditorValue() {
            return username;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            studentId = (Integer) table.getValueAt(row, 0);
            username = (String) table.getValueAt(row, 2); // Assuming the username is in the 3rd column

            // Confirmation before removal
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this student?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                removeStudent(studentId, username);
                model.removeRow(row);  // Remove the student from the table
            }

            return new JButton("Remove");
        }
    }

    private void removeStudent(int studentId, String username) {
        try {
            Conn conn = new Conn();

            // Delete attendance records for the student
            String deleteAttendanceQuery = "DELETE FROM attendance WHERE username = ?";
            PreparedStatement pstAttendance = conn.c.prepareStatement(deleteAttendanceQuery);
            pstAttendance.setString(1, username);
            pstAttendance.executeUpdate();

            // Delete payment records for the student
            String deletePaymentsQuery = "DELETE FROM payments WHERE username = ?";
            PreparedStatement pstPayments = conn.c.prepareStatement(deletePaymentsQuery);
            pstPayments.setString(1, username);
            pstPayments.executeUpdate();

            // Delete help requests for the student
            String deleteHelpRequestsQuery = "DELETE FROM help_requests WHERE username = ?";
            PreparedStatement pstHelpRequests = conn.c.prepareStatement(deleteHelpRequestsQuery);
            pstHelpRequests.setString(1, username);
            pstHelpRequests.executeUpdate();

            // Now delete the student from the students table
            String deleteStudentQuery = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pstStudent = conn.c.prepareStatement(deleteStudentQuery);
            pstStudent.setInt(1, studentId);
            int rowsAffected = pstStudent.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student removed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Error removing student.");
            }

            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewStudents().setVisible(true);
    }
}
