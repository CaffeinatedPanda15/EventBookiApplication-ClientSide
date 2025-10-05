package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;

public class CreateEventGui {
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
}
