package za.ac.cput.views;

import za.ac.cput.factory.eventfactories.EventFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


    public class CreateEventPage extends JPanel implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblTitle, lblName, lblDescription, lblLocation,
            lblDate, lblTime, lblCategory, lblStatus;
    private JTextField txtName, txtDate, txtTime, txtCategory;
    private JTextArea txtDescription;
    private JComboBox<String> cbxLocation; // Replace String with Venue
    private JComboBox<String> cbxStatus;   // Replace String with EventStatus
    private JButton btnCreate;

    public CreateEventPage() {
        super();
        panelNorth = new JPanel();
        panelCenter = new JPanel(new GridLayout(7, 2, 5, 5));
        panelSouth = new JPanel();

        lblTitle = new JLabel("Create Event");
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

        btnCreate = new JButton("Create Event");
    }

    public void setGUI() {
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

        panelSouth.add(btnCreate);

        setLayout(new BorderLayout(10, 10));
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        btnCreate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eventName = txtName.getText();
        String eventDescription = txtDescription.getText();
        String eventLocation = (String) cbxLocation.getSelectedItem(); // replace with Venue
        String eventDate = txtDate.getText();
        String eventTime = txtTime.getText();
        String category = txtCategory.getText();
        String status = (String) cbxStatus.getSelectedItem(); // replace with EventStatus

        try {
            EventFactory EventClient =  new EventFactory();
            String message = EventClient.createEvent(category.trim(), eventTime.trim(),
                    eventDate.trim(), eventDescription.trim(), eventName.trim()
            );
            JOptionPane.showMessageDialog(null, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, " Error: " + ex.getMessage());
        }

//
//
//
//        JOptionPane.showMessageDialog(this,
//                "Event Created:\n" +
//                        "Name: " + eventName + "\n" +
//                        "Description: " + eventDescription + "\n" +
//                        "Location: " + eventLocation + "\n" +
//                        "Date: " + eventDate + "\n" +
//                        "Time: " + eventTime + "\n" +
//                        "Category: " + category + "\n" +
//                        "Status: " + status);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Creation");
        CreateEventPage eventPage = new CreateEventPage();
        eventPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(eventPage);
        frame.setVisible(true);
    }
}

