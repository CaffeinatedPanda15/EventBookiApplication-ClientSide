package za.ac.cput.views;

import org.json.JSONObject;
import za.ac.cput.domain.endusers.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginPage extends JPanel implements ActionListener {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final AdminHomePage adminHomePage;
    private final RegisterAdminPage registerPage;


    private JPanel panelNorth, panelWest;
    private JLabel labelLogo, labelTitle, labelEmailAddress, labelPassword;
    private JTextField tfieldUserName;
    private JPasswordField txtPassword;
    private JButton buttonHome, buttonEvents, buttonVenues, buttonSignup, buttonLogin;
    private BackgroundPanel panelRight;

    public LoginPage(CardLayout cardLayout, JPanel mainPanel, AdminHomePage adminHomePage, RegisterAdminPage registerAdminPage) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.adminHomePage = adminHomePage;
        this.registerPage = registerAdminPage;

        setGUI();
    }

    public void setGUI() {
        // north panel
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        labelLogo = new JLabel(new ImageIcon(logoImg));
        labelLogo.setHorizontalAlignment(JLabel.CENTER);

        labelTitle = new JLabel("Admin Login", JLabel.CENTER);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitle.setForeground(Color.WHITE);

        buttonHome = new JButton("Home");
        buttonEvents = new JButton("Current Events");
        buttonVenues = new JButton("Venues");
        buttonSignup = new JButton("Register Admin");

        JButton[] navButtons = {buttonHome, buttonEvents, buttonVenues, buttonSignup};
        for (JButton b : navButtons) {
            b.setBackground(new Color(50, 50, 50));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        // add action listeners
        buttonSignup.addActionListener(this);

        // West/Login Panel
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        labelEmailAddress = new JLabel("Email Address:");
        labelEmailAddress.setForeground(Color.WHITE);
        tfieldUserName = new JTextField(15);

        labelPassword = new JLabel("Password:");
        labelPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField(15);

        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(this);

        // build West panel
        panelWest.add(Box.createVerticalStrut(40));
        panelWest.add(labelEmailAddress);
        panelWest.add(tfieldUserName);
        panelWest.add(Box.createVerticalStrut(10));
        panelWest.add(labelPassword);
        panelWest.add(txtPassword);
        panelWest.add(Box.createVerticalStrut(20));
        panelWest.add(buttonLogin);

        // background image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/bowtie.JPG"));
        panelRight = new BackgroundPanel(bgIcon.getImage());

        // North section assembly
        panelNorth.add(labelLogo);

        JPanel rowTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rowTitle.setOpaque(false);
        rowTitle.add(labelTitle);
        panelNorth.add(rowTitle);

        JPanel rowNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        rowNav.setOpaque(false);
        rowNav.add(buttonHome);
        rowNav.add(buttonEvents);
        rowNav.add(buttonVenues);
        rowNav.add(buttonSignup);
        panelNorth.add(rowNav);

        // Add panels to main panel
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    private void loginAdmin() {
        try {
            String email = tfieldUserName.getText();
            String password = new String(txtPassword.getPassword());

            String json = String.format("{\"emailAddress\":\"%s\",\"password\":\"%s\"}", email, password);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonObj = new JSONObject(responseBody);
                String fullName = jsonObj.getString("fullName");

                JOptionPane.showMessageDialog(this, "✅ Admin Login successful!\nWelcome " + fullName);

                // Switch to Admin Home
                cardLayout.show(mainPanel, "admin");

            } else {
                JOptionPane.showMessageDialog(this, "❌ Login failed! Check email or password.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage());
        }
    }

    private void switchToRegisterAdminPage() {
        cardLayout.show(mainPanel, "registerAdminPage");
    }

    private static class BackgroundPanel extends JPanel {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == buttonLogin) {
            loginAdmin();
        } else if (source == buttonSignup) {
            switchToRegisterAdminPage();
        }
    }
}
