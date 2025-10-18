package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class AddVenuePage extends JFrame implements ActionListener {

    private JTextField txtName, txtAddress, txtCapacity, txtPrice;
    private JTextArea txtDescription;
    private JButton btnSubmit;

    public AddVenuePage() {
        setTitle("Add New Venue");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Venue Name:"));
        txtName = new JTextField(); add(txtName);

        add(new JLabel("Address:"));
        txtAddress = new JTextField(); add(txtAddress);

        add(new JLabel("Description:"));
        txtDescription = new JTextArea(3, 20);
        add(new JScrollPane(txtDescription));

        add(new JLabel("Capacity:"));
        txtCapacity = new JTextField(); add(txtCapacity);

        add(new JLabel("Price (R):"));
        txtPrice = new JTextField(); add(txtPrice);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(0, 153, 0));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        add(new JLabel()); // empty cell
        add(btnSubmit);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("venueName", txtName.getText());
            obj.put("venueAddress", txtAddress.getText());
            obj.put("venueDescription", txtDescription.getText());
            obj.put("venueCapacity", Integer.parseInt(txtCapacity.getText()));
            obj.put("venuePrice", Double.parseDouble(txtPrice.getText()));

            URL url = new URL("http://localhost:8080/api/venue");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(obj.toString().getBytes());
            }

            if (conn.getResponseCode() == 201) {
                JOptionPane.showMessageDialog(this, "Venue created successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create venue. Response: " + conn.getResponseCode());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
