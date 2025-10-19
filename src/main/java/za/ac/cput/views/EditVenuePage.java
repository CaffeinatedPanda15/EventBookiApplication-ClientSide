package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class EditVenuePage extends JFrame implements ActionListener {
    private JComboBox<String> cbVenues;
    private JTextField txtName, txtAddress, txtCapacity, txtPrice;
    private JTextArea txtDescription;
    private JButton btnLoad, btnUpdate;

    private int selectedVenueId = -1;

    public EditVenuePage() {
        setTitle("Edit Venue");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("Edit Venue Details", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Venue dropdown
        centerPanel.add(new JLabel("Select Venue:"));
        cbVenues = new JComboBox<>();
        centerPanel.add(cbVenues);

        // Fields
        centerPanel.add(new JLabel("Venue Name:"));
        txtName = new JTextField();
        centerPanel.add(txtName);

        centerPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        centerPanel.add(txtAddress);

        centerPanel.add(new JLabel("Description:"));
        txtDescription = new JTextArea(3, 20);
        centerPanel.add(new JScrollPane(txtDescription));

        centerPanel.add(new JLabel("Capacity:"));
        txtCapacity = new JTextField();
        centerPanel.add(txtCapacity);

        centerPanel.add(new JLabel("Price (R):"));
        txtPrice = new JTextField();
        centerPanel.add(txtPrice);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        btnLoad = new JButton("Load");
        btnUpdate = new JButton("Update");

        JButton[] buttons = {btnLoad, btnUpdate};
        for (JButton b : buttons) {
            b.setBackground(new Color(0, 102, 204));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setFocusPainted(false);
            b.addActionListener(this);
            btnPanel.add(b);
        }

        add(btnPanel, BorderLayout.SOUTH);

        loadVenuesDropdown();
        setVisible(true);
    }

    private void loadVenuesDropdown() {
        try {
            URL url = new URL("http://localhost:8080/api/venue/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes());
            JSONArray venues = new JSONArray(response);

            for (int i = 0; i < venues.length(); i++) {
                JSONObject v = venues.getJSONObject(i);
                cbVenues.addItem(v.getInt("venueId") + " - " + v.getString("venueName"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load venues: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLoad) {
            String selected = (String) cbVenues.getSelectedItem();
            if (selected == null) return;

            selectedVenueId = Integer.parseInt(selected.split(" - ")[0]);
            loadVenueDetails(selectedVenueId);
        }
        else if (e.getSource() == btnUpdate) {
            if (selectedVenueId == -1) {
                JOptionPane.showMessageDialog(this, "Please load a venue first.");
                return;
            }
            updateVenue();
        }
    }

    private void loadVenueDetails(int id) {
        try {
            URL url = new URL("http://localhost:8080/api/venue/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes());
            JSONObject venue = new JSONObject(response);

            txtName.setText(venue.getString("venueName"));
            txtAddress.setText(venue.getString("venueAddress"));
            txtDescription.setText(venue.optString("venueDescription", ""));
            txtCapacity.setText(String.valueOf(venue.getInt("venueCapacity")));
            txtPrice.setText(String.valueOf(venue.getDouble("venuePrice")));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading venue details: " + e.getMessage());
        }
    }

    private void updateVenue() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("venueName", txtName.getText());
            obj.put("venueAddress", txtAddress.getText());
            obj.put("venueDescription", txtDescription.getText());
            obj.put("venueCapacity", Integer.parseInt(txtCapacity.getText()));
            obj.put("venuePrice", Double.parseDouble(txtPrice.getText()));

            URL url = new URL("http://localhost:8080/api/venue/" + selectedVenueId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(obj.toString().getBytes());
            }

            if (conn.getResponseCode() == 200) {
                JOptionPane.showMessageDialog(this, "Venue updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update venue. Response: " + conn.getResponseCode());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating venue: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EditVenuePage::new);
    }
}
