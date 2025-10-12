package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;

public class GUIs {
    private JPanel panelRoot;
    private JPanel panelLoginPage;
    private JPanel panelAddAdminPahge;
    private JPanel PanelHomePage;
    private JPanel panelCateringPage;
    private JPanel panelVenuePage;
    private JPanel loginPanel;
    private JPanel panelNorth;
    private JPanel panelWest;
    private JPanel panelEast;
    private JPanel panelSouth;
    private JPanel panelCenter;
    private JLabel labelTitle;
    private JLabel labelUsername;
    private JTextField tboxUsername;
    private JTextField tboxPassword;
    private JLabel labelPassword;
    private JButton buttonLogin;
    private JButton buttonClear;
    private JPanel panelHome;
    private JPanel panelHomeNorth;
    private JPanel panelHomeSouth;
    private JPanel panelNavButton;
    private JPanel panelTitle;
    private JButton buttonCatering;
    private JButton venueButton;
    private JButton logoutButton;
    private JScrollPane jscrollCenter;
    private JPanel panelEventList;
    private JPanel paneleditvenue;
    private JPanel panelEditlogNorth;
    private JPanel panelEditvenEast;
    private JPanel panelEditvenSouth;
    private JPanel panelEditvenWest;
    private JPanel panelEditlogCenter;
    private JPanel panelEdutVenTitle;
    private JLabel panelEditvenTitle;
    private JPanel panelEditvenNavButtons;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JLabel labelEventnameEdit;
    private JLabel labelVenueeditDescriptions;
    private JTextArea textArea1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button4;
    private JPanel panelEditvenbuttons;
    private JButton editButton;
    private JButton saveButton;
    private JButton clearButton;


    public GUIs() {
        panelRoot.setLayout(new CardLayout());

        panelRoot.add(panelLoginPage, "login");
        panelRoot.add(panelAddAdminPahge, "addAdmin");
        panelRoot.add(PanelHomePage, "home");
        panelRoot.add(panelCateringPage, "catering");
        panelRoot.add(panelVenuePage, "venue");

    }
}
