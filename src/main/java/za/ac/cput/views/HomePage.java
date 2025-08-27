package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    // --- North ---
    protected JPanel panelNorth;
    protected JLabel lblLogo;
    protected JLabel lblTitle;
    protected JButton btnHome;
    protected JButton btnEvents;
    protected JButton btnVenues;
    protected JButton btnOther;

    // --- West (form panel) ---
    protected JPanel panelWest;

    // --- Empty labels for spacing (manually) ---
    protected JLabel empty1;
    protected JLabel empty2;
    protected JLabel empty3;
    protected JLabel empty4;
    protected JLabel empty5;
    protected JLabel empty6;
    protected JLabel empty7;
    protected JLabel empty8;
    protected JLabel empty9;
    protected JLabel empty10;
    protected JLabel empty11;
    protected JLabel empty12;

    // --- Right (background image) ---
    protected BackgroundPanel panelRight;

    public HomePage() {
        super(new BorderLayout());

        // --- North ---
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(logoImg));
        lblLogo.setHorizontalAlignment(JLabel.CENTER);

        lblTitle = new JLabel("Homepage", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        btnHome = new JButton("Home");
        btnEvents = new JButton("Current Events");
        btnVenues = new JButton("Venues");
        btnOther = new JButton("Other");

        // Style buttons manually
        btnHome.setBackground(new Color(50, 50, 50));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.setBorderPainted(false);
        btnHome.setFont(new Font("Arial", Font.BOLD, 14));
        btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnEvents.setBackground(new Color(50, 50, 50));
        btnEvents.setForeground(Color.WHITE);
        btnEvents.setFocusPainted(false);
        btnEvents.setBorderPainted(false);
        btnEvents.setFont(new Font("Arial", Font.BOLD, 14));
        btnEvents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnVenues.setBackground(new Color(50, 50, 50));
        btnVenues.setForeground(Color.WHITE);
        btnVenues.setFocusPainted(false);
        btnVenues.setBorderPainted(false);
        btnVenues.setFont(new Font("Arial", Font.BOLD, 14));
        btnVenues.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnOther.setBackground(new Color(50, 50, 50));
        btnOther.setForeground(Color.WHITE);
        btnOther.setFocusPainted(false);
        btnOther.setBorderPainted(false);
        btnOther.setFont(new Font("Arial", Font.BOLD, 14));
        btnOther.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // --- West (form panel) ---
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        // Empty labels manually
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

        // --- Right (background image) ---
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/bowtie.JPG"));
        panelRight = new BackgroundPanel(bgIcon.getImage());
    }

    public void setGUI() {
        // --- North layout ---
        panelNorth.add(lblLogo);

        JPanel rowTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rowTitle.setOpaque(false);
        rowTitle.add(lblTitle);
        panelNorth.add(rowTitle);

        JPanel rowNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        rowNav.setOpaque(false);
        rowNav.add(btnHome);
        rowNav.add(btnEvents);
        rowNav.add(btnVenues);
        rowNav.add(btnOther);
        panelNorth.add(rowNav);

        // --- West layout ---
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

        // --- Add panels to main ---
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    // --- Background panel class ---
    protected static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // --- Test the base page ---
    public static void main(String[] args) {
        JFrame frame = new JFrame("Base Page");
        HomePage basePage = new HomePage();
        basePage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(basePage);
        frame.setVisible(true);
    }
}
