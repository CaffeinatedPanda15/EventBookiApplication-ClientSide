package za.ac.cput.views;

import za.ac.cput.dao.EventsDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePage extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CreateEventPage createEventPage;
    private JList<String> eventsList;

    public HomePage(CardLayout cardLayout, JPanel mainPanel, CreateEventPage createEventPage) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.createEventPage = createEventPage;
        setOpaque(true);
        setBackground(new Color(30, 30, 30));
        setGUI();
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(50, 50, 50, 200));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 50, 50, 200));
            }
        });
        return button;
    }

    public void setGUI() {
        // top panel
        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.setBackground(new Color(40, 40, 40, 220));
        panelNorth.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // left panel
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        logoPanel.setOpaque(false);

        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoPanel.add(logoLabel);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("[Logo]");
            placeholder.setForeground(Color.LIGHT_GRAY);
            placeholder.setFont(new Font("Arial", Font.ITALIC, 16));
            logoPanel.add(placeholder);
        }

        JLabel lblTitle = new JLabel("Event Booking Dashboard");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logoPanel.add(lblTitle);

        panelNorth.add(logoPanel, BorderLayout.WEST);

        // right panel
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setOpaque(false);

        JButton btnHome = createNavButton("Home");
        JButton btnAdd = createNavButton("Add Event");
        JButton btnVenues = createNavButton("venue");
        JButton btnCatering = createNavButton("Catering");
        JButton btnLogout = createNavButton("Logout");

        btnAdd.addActionListener(e -> {
            createEventPage.loadEvent("");
            cardLayout.show(mainPanel, "createEventPage");

        });

        btnHome.addActionListener(e -> cardLayout.show(mainPanel, "HomePage"));
        btnVenues.addActionListener(e -> cardLayout.show(mainPanel, "venue"));
        btnCatering.addActionListener(e -> new AddCateringPage());
        btnCatering.addActionListener(e -> cardLayout.show(mainPanel, "Catering"));
        btnLogout.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        navPanel.add(btnHome);
        navPanel.add(btnAdd);
        navPanel.add(btnVenues);
        navPanel.add(btnCatering);
        navPanel.add(btnLogout);

        panelNorth.add(navPanel, BorderLayout.EAST);
        add(panelNorth, BorderLayout.NORTH);

        // centre panel
        JPanel panelCentre = new JPanel(new BorderLayout(10, 10));
        panelCentre.setBackground(new Color(45, 45, 45));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel centerTitle = new JLabel("Available Events", JLabel.CENTER);
        centerTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        centerTitle.setForeground(Color.WHITE);
        panelCentre.add(centerTitle, BorderLayout.NORTH);

        List<String> events = EventsDAO.getAllEvents();
        if (events.isEmpty()) events = List.of("No events available");

        eventsList = new JList<>(events.toArray(new String[0]));
        eventsList.setBackground(new Color(60, 60, 60));
        eventsList.setForeground(Color.WHITE);
        eventsList.setSelectionBackground(new Color(80, 80, 80));
        eventsList.setSelectionForeground(Color.ORANGE);
        eventsList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(eventsList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        panelCentre.add(scrollPane, BorderLayout.CENTER);

        add(panelCentre, BorderLayout.CENTER);

        // south panel
        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(new Color(35, 35, 35, 220));
        JLabel footer = new JLabel("© 2025 EventBooki Application");
        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelSouth.add(footer);
        add(panelSouth, BorderLayout.SOUTH);

        // funtionality
        eventsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedEvent = eventsList.getSelectedValue();
                if (selectedEvent != null && !selectedEvent.equals("No events available")) {
                    createEventPage.loadEvent(selectedEvent);
                    cardLayout.show(mainPanel, "createEventPage");
                }
            }
        });
    }
}


