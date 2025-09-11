package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import za.ac.cput.views.VenuesPage;

public class HomePage extends JPanel {

    // North components
    private JPanel panelNorth;
    private JLabel labelLogo;
    private JLabel labelTitle;
    private JButton buttonHome;
    private JButton buttonEvents;
    private JButton buttonVenues;
    private JButton buttonOther;

    // West panel
    private JPanel panelWest;

    // Center content (VenuesPage)
    private JPanel panelCenter;

    public HomePage() {
        super(new BorderLayout());

        // ---------- NORTH ----------
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        labelLogo = new JLabel(logoIcon, JLabel.CENTER);

        // Title
        labelTitle = new JLabel("Homepage", JLabel.CENTER);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitle.setForeground(Color.WHITE);

        // Navigation buttons
        buttonHome = createNavButton("Home");
        buttonEvents = createNavButton("Current Events");
        buttonVenues = createNavButton("Venues");
        buttonOther = createNavButton("Other");

        // ---------- WEST ----------
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.add(Box.createVerticalGlue()); // spacing only

        // ---------- CENTER ----------
        panelCenter = new JPanel(new BorderLayout());
        VenuesPage venuesPage = new VenuesPage(); // show venues by default
        panelCenter.add(venuesPage, BorderLayout.CENTER);
    }

    // Helper for navigation buttons
    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Simple action for now
        button.addActionListener(e ->
                JOptionPane.showMessageDialog(this, text + " clicked!"));

        return button;
    }

    public void setGUI() {
        // North layout setup
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
        navigationRow.add(buttonOther);
        panelNorth.add(navigationRow);

        // Add everything to HomePage
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelCenter, BorderLayout.CENTER);
    }
}
