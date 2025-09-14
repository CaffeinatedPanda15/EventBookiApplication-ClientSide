package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    // North
    private JPanel panelNorth;
    private JLabel labelLogo;
    private JLabel labelTitle;
    private JButton buttonHome;
    private JButton buttonEvents;
    private JButton buttonVenues;
    private JButton buttonRegisterNewAdmin;

    // West
    private JPanel panelWest;

    // Empty panels
    private JLabel empty1;
    private JLabel empty2;
    private JLabel empty3;
    private JLabel empty4;
    private JLabel empty5;
    private JLabel empty6;
    private JLabel empty7;
    private JLabel empty8;
    private JLabel empty9;
    private JLabel empty10;
    private JLabel empty11;
    private JLabel empty12;

    // background image
    private BackgroundPanel panelRight;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HomePage(CardLayout cardLayout, JPanel mainPanel) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        // North
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        labelLogo = new JLabel(new ImageIcon(logoImg));
        labelLogo.setHorizontalAlignment(JLabel.CENTER);

        labelTitle = new JLabel("Homepage", JLabel.CENTER);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitle.setForeground(Color.WHITE);

        buttonHome = new JButton("Home");
        buttonHome.setBackground(new Color(50, 50, 50));
        buttonHome.setForeground(Color.WHITE);
        buttonHome.setFocusPainted(false);
        buttonHome.setBorderPainted(false);
        buttonHome.setFont(new Font("Arial", Font.BOLD, 14));
        buttonHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        buttonEvents = new JButton("Current Events");
        buttonEvents.setBackground(new Color(50, 50, 50));
        buttonEvents.setForeground(Color.WHITE);
        buttonEvents.setFocusPainted(false);
        buttonEvents.setBorderPainted(false);
        buttonEvents.setFont(new Font("Arial", Font.BOLD, 14));
        buttonEvents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        buttonVenues = new JButton("Venues");
        buttonVenues.setBackground(new Color(50, 50, 50));
        buttonVenues.setForeground(Color.WHITE);
        buttonVenues.setFocusPainted(false);
        buttonVenues.setBorderPainted(false);
        buttonVenues.setFont(new Font("Arial", Font.BOLD, 14));
        buttonVenues.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        buttonRegisterNewAdmin = new JButton("Add Admin");
        buttonRegisterNewAdmin.setBackground(new Color(50, 50, 50));
        buttonRegisterNewAdmin.setForeground(Color.WHITE);
        buttonRegisterNewAdmin.setFocusPainted(false);
        buttonRegisterNewAdmin.setBorderPainted(false);
        buttonRegisterNewAdmin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonRegisterNewAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


    buttonRegisterNewAdmin.addActionListener(e -> cardLayout.show(mainPanel, "registerAdminPage"));

        // West
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        // Empty labels
        empty1 = new JLabel();
        empty2 = new JLabel();
        empty3 = new JLabel();
        empty4 = new JLabel();
        empty5 = new JLabel();
        empty6 = new JLabel();
        empty7 = new JLabel();
        empty8 = new JLabel();
        empty9 = new JLabel();
        empty10 = new JLabel();
        empty11 = new JLabel();
        empty12 = new JLabel();

        //Right
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/bowtie.JPG"));
        panelRight = new BackgroundPanel(bgIcon.getImage());

        setGUI();
    }

    public void setGUI() {
        //North
        panelNorth.add(labelLogo);

        JPanel rowTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rowTitle.setOpaque(false);
        rowTitle.add(labelTitle);
        panelNorth.add(rowTitle);

        JPanel navigationRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navigationRow.setOpaque(false);
        navigationRow.add(buttonHome);
        navigationRow.add(buttonEvents);
        navigationRow.add(buttonVenues);
        navigationRow.add(buttonRegisterNewAdmin);
        panelNorth.add(navigationRow);

        //West
        panelWest.add(empty1);
        panelWest.add(empty2);
        panelWest.add(empty3);
        panelWest.add(empty4);
        panelWest.add(empty5);
        panelWest.add(empty6);
        panelWest.add(empty7);
        panelWest.add(empty8);
        panelWest.add(empty9);
        panelWest.add(empty10);
        panelWest.add(empty11);
        panelWest.add(empty12);

        //Add panels to main
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    // Background panel
    public static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }


  /*  public static void main(String[] args) {
        JFrame frame = new JFrame("Base Page");
        HomePage basePage = new HomePage();
        basePage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(basePage);
        frame.setVisible(true);
    }*/
}
