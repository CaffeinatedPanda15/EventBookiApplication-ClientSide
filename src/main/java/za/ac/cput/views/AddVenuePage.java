package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVenuePage extends JPanel implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblTitle, lblVenueName, lblVenueAddress, lblVenueCapacity;
    private JTextField txtVenueName, txtVenueAddress, txtVenueCapacity;
    private JButton btnSave;

    public AddVenuePage() {
        super();
        panelNorth = new JPanel();
        panelCenter = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSouth = new JPanel();

        lblTitle = new JLabel("Add Venue");
        lblVenueName = new JLabel("Venue Name:");
        lblVenueAddress = new JLabel("Venue Address:");
        lblVenueCapacity = new JLabel("Capacity:");

        txtVenueName = new JTextField(15);
        txtVenueAddress = new JTextField(15);
        txtVenueCapacity = new JTextField(15);

        btnSave = new JButton("Save Venue");
    }

    public void setGUI() {
        panelNorth.add(lblTitle);

        panelCenter.add(lblVenueName);
        panelCenter.add(txtVenueName);

        panelCenter.add(lblVenueAddress);
        panelCenter.add(txtVenueAddress);

        panelCenter.add(lblVenueCapacity);
        panelCenter.add(txtVenueCapacity);

        panelSouth.add(btnSave);

        setLayout(new BorderLayout(10, 10));
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String venueName = txtVenueName.getText();
        String venueAddress = txtVenueAddress.getText();
        int venueCapacity = 0;

        try {
            venueCapacity = Integer.parseInt(txtVenueCapacity.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for capacity.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Venue Saved:\n" +
                        "Name: " + venueName + "\n" +
                        "Address: " + venueAddress + "\n" +
                        "Capacity: " + venueCapacity);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Venue");
        AddVenuePage venuePage = new AddVenuePage();
        venuePage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.add(venuePage);
        frame.setVisible(true);
    }
}
