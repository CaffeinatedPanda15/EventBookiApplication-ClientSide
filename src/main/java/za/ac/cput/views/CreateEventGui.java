package za.ac.cput.views;

import za.ac.cput.util.EventClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showConfirmDialog;

public class CreateEventGui extends JFrame implements ActionListener {
    private JPanel panelAddeventFoundation;
    private JPanel panelTitle;
    private JLabel LabelTitle;
    private JPanel panelCenter;
    private JTextField tboxEventname;
    private JTextArea tareaDescription;
    private JTextField tboxLocation;
    private JTextField tboxDate;
    private JTextField tboxTime;
    private JTextField tboxCustomername;
    private JTextField tboxCustomerEmail;
    private JTextField textField9;
    private JLabel labelEventName;
    private JLabel LabelDescription;
    private JLabel labelLocation;
    private JButton buttonSelect;
    private JLabel labelEmpty1;
    private JLabel labelDate;
    private JLabel labelTime;
    private JLabel labelCategory;
    private JComboBox cboxCategory;
    private JLabel labelStatus;
    private JComboBox cboxStatus;
    private JLabel labelCustomerName;
    private JLabel labelCustomeremail;
    private JLabel labelContactNumber;
    private JLabel labelCatering;
    private JTextField tboxCatering;
    private JPanel PanelSouth;
    private JButton buttonSave;
    private JButton buttonClear;
    private JButton editButton;
    private JButton cancelButton;

    public Container getPanelAddEventFoundation() {
        return panelAddeventFoundation;
    }

    public CreateEventGui() {
        // Set up frame
        setTitle("Create Event");
        setContentPane(panelAddeventFoundation);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // 🔗 Connect buttons to this ActionListener
        buttonSave.addActionListener(this);
        buttonClear.addActionListener(this);
        editButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == buttonSave) {
            saveEvent();
        } else if (source == buttonClear) {
            clearFields();
        } else if (source == editButton) {
            JOptionPane.showMessageDialog(this, "Edit feature coming soon!");
        } else if (source == cancelButton) {
            dispose(); // closes the window
        }
    }

    private void saveEvent() {
        String eventName = tboxEventname.getText();
        String eventDescription = tareaDescription.getText();
        String eventLocation = tboxLocation.getText();
        String eventDate = tboxDate.getText();
        String eventTime = tboxTime.getText();
        String catering = tboxCatering.getText();
        String customerName = tboxCustomername.getText();
        String email = tboxCustomerEmail.getText();
        String category = (String) cboxCategory.getSelectedItem();
        String contact = textField9.getText();
        String status = (String) cboxStatus.getSelectedItem();

        try {
            EventClient eventClient = new EventClient();
            String message = eventClient.createEvent(
                    category.trim(),
                    eventTime.trim(),
                    eventDate.trim(),
                    eventDescription.trim(),
                    eventName.trim()
            );

            JOptionPane.showMessageDialog(this, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " +
                    ex.getMessage());
        }
    }


    private void clearFields() {
        int confirmation = JOptionPane.showConfirmDialog(this, "are you sure you want to clear all fields?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return; // User chose not to clear
        }
        else {
            tboxEventname.setText("");
            tareaDescription.setText("");
            tboxLocation.setText("");
            tboxDate.setText("");
            tboxTime.setText("");
            tboxCatering.setText("");
            tboxCustomername.setText("");
            tboxCustomerEmail.setText("");
            textField9.setText("");
            cboxCategory.setSelectedIndex(0);
            cboxStatus.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        new CreateEventGui();
    }
}


