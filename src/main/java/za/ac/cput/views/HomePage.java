package za.ac.cput.views;

import za.ac.cput.dao.EventsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomePage extends JPanel {

    private JPanel panelNorth;
    private JLabel labelLogo;
    private JLabel labelTitle;
    private JButton buttonHome;
    private JButton buttonEvents;
    private JButton buttonVenues;
    private JButton buttonOther;
    private JPanel panelWest;
    private JPanel panelCentre;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HomePage(CardLayout cardLayout, JPanel mainPanel) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setOpaque(false);
        setGUI();
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(50, 50, 50, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public void setGUI() {
        // north panel with gradient, logo, title
        panelNorth = new JPanel(new GridLayout(3, 1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 128, 128), 0, getHeight(), new Color(255, 111, 97));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelNorth.setOpaque(false);

        // Logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        int width = 100;
        int height = (originalIcon.getIconHeight() * width) / originalIcon.getIconWidth();
        ImageIcon logoIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        labelLogo = new JLabel(logoIcon);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        logoPanel.setOpaque(false);
        logoPanel.add(labelLogo);
        panelNorth.add(logoPanel);

        // Title
        labelTitle = new JLabel("Homepage", JLabel.CENTER);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitle.setForeground(Color.WHITE);
        JPanel rowTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rowTitle.setOpaque(false);
        rowTitle.add(labelTitle);
        panelNorth.add(rowTitle);

        // Navigation buttons
        buttonHome = createNavButton("Home");
        buttonEvents = createNavButton("Create Events");
        buttonVenues = createNavButton("Venues");
        buttonOther = createNavButton("Register new Admin");

        // Action listener to navigate to CreateEventPage
        buttonEvents.addActionListener(e -> cardLayout.show(mainPanel, "CreateEventPage"));
        buttonOther.addActionListener( e -> cardLayout.show(mainPanel, "registerAdminPage"));


        JPanel navigationRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navigationRow.setOpaque(false);
        navigationRow.add(buttonHome);
        navigationRow.add(buttonEvents);
        navigationRow.add(buttonVenues);
        navigationRow.add(buttonOther);
        panelNorth.add(navigationRow);

        add(panelNorth, BorderLayout.NORTH);

        // west panel
        panelWest = new JPanel();
        panelWest.setOpaque(false);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.add(Box.createVerticalGlue());
        add(panelWest, BorderLayout.WEST);

        // center panel with event list
        panelCentre = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 128, 128), 0, getHeight(), new Color(255, 111, 97));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelCentre.setOpaque(false);

        JLabel centerTitle = new JLabel("Available Events", JLabel.CENTER);
        centerTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        centerTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        centerTitle.setForeground(Color.WHITE);
        panelCentre.add(centerTitle, BorderLayout.NORTH);

        List<String> events = EventsDAO.getAllEvents();
        if (events.isEmpty()) {
            events = List.of("No events available");
        }

        JList<String> eventsList = new JList<>(events.toArray(new String[0]));
        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventsList.setOpaque(false);
        eventsList.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(eventsList);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCentre.add(scrollPane, BorderLayout.CENTER);
        add(panelCentre, BorderLayout.CENTER);
    }
}
