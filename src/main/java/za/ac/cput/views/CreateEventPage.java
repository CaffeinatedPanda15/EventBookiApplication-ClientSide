package za.ac.cput.views;

import za.ac.cput.dao.EventsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateEventPage extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblTitle, lblName, lblDescription, lblLocation,
            lblDate, lblTime, lblCategory, lblStatus;
    private JTextField txtName, txtDate, txtTime, txtCategory;
    private JTextArea txtDescription;
    private JComboBox<String> cbxLocation;
    private JComboBox<String> cbxStatus;
    private JButton btnSave;

    public CreateEventPage(CardLayout cardLayout, JPanel mainPanel) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        panelNorth = new JPanel();
        panelCenter = new JPanel(new GridLayout(7, 2, 5, 5));
        panelSouth = new JPanel();

        lblTitle = new JLabel("Edit / Create Event");
        lblName = new JLabel("Event Name:");
        lblDescription = new JLabel("Description:");
        lblLocation = new JLabel("Location:");
        lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblTime = new JLabel("Time (HH:MM):");
        lblCategory = new JLabel("Category:");
        lblStatus = new JLabel("Status:");

        txtName = new JTextField(15);
        txtDescription = new JTextArea(3, 15);
        txtDate = new JTextField(15);
        txtTime = new JTextField(15);
        txtCategory = new JTextField(15);

        cbxLocation = new JComboBox<>(new String[]{"Venue A", "Venue B", "Venue C"});
        cbxStatus = new JComboBox<>(new String[]{"PLANNED", "ONGOING", "COMPLETED"});

        btnSave = new JButton("Save Event");

        setGUI();
    }

    private void setGUI() {
        panelNorth.add(lblTitle);

        panelCenter.add(lblName);
        panelCenter.add(txtName);

        panelCenter.add(lblDescription);
        panelCenter.add(new JScrollPane(txtDescription));

        panelCenter.add(lblLocation);
        panelCenter.add(cbxLocation);

        panelCenter.add(lblDate);
        panelCenter.add(txtDate);

        panelCenter.add(lblTime);
        panelCenter.add(txtTime);

        panelCenter.add(lblCategory);
        panelCenter.add(txtCategory);

        panelCenter.add(lblStatus);
        panelCenter.add(cbxStatus);

        panelSouth.add(btnSave);

        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get all the fields
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();
        String location = (String) cbxLocation.getSelectedItem();
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String category = txtCategory.getText().trim();
        String status = (String) cbxStatus.getSelectedItem();

        // validate fields
        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all mandatory fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // save event using dao so long
        EventsDAO.saveEvent(name, description, location, date, time, category, status);

        JOptionPane.showMessageDialog(this, "Event saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // return to HomePage
        if (cardLayout != null && mainPanel != null) {
            cardLayout.show(mainPanel, "HomePage");
        }
    }

    // populate fields when an event is selected from HomePage
    public void loadEvent(String eventData) {
        // eventData format
        String[] parts = eventData.split("\\|");
        if (parts.length >= 6) {
            txtName.setText(parts[0].split("-",2)[1].trim());
            txtDescription.setText(parts[1].trim());
            cbxLocation.setSelectedItem(parts[2].trim());
            String[] dateTime = parts[3].trim().split(" ");
            txtDate.setText(dateTime[0]);
            txtTime.setText(dateTime.length > 1 ? dateTime[1] : "");
            txtCategory.setText(parts[4].trim());
            cbxStatus.setSelectedItem(parts[5].trim());
        }
    }
}

