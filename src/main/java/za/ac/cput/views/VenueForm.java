package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VenueForm extends JFrame implements ActionListener {

    private JButton btnCreate, btnEdit, btnDelete, btnView;
    private JPanel buttonPanel;

    public VenueForm() {
        setTitle("Venue Management Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title Label
        JLabel title = new JLabel("Venue Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Buttons
        btnCreate = new JButton("Create");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnView = new JButton("View All");

        JButton[] buttons = {btnCreate, btnEdit, btnDelete, btnView};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(0, 102, 204));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(this);
        }

        // Button Panel (CRUD buttons side by side)
        buttonPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        for (JButton btn : buttons) buttonPanel.add(btn);

        // Add components
        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnCreate) {
            new AddVenuePage();
        } else if (src == btnEdit) {
            new EditVenuePage();
        } else if (src == btnDelete) {
            JOptionPane.showMessageDialog(this, "Delete functionality to be added later.");
        } else if (src == btnView) {
            new ViewVenues();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VenueForm::new);
    }
}
