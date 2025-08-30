package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginPage extends JPanel implements ActionListener {

    // --- North ---
    private JPanel panelNorth;
    private JLabel lblLogo;
    private JLabel lblTitle;
    private JButton btnHome;
    private JButton btnEvents;
    private JButton btnVenues;
    private JButton btnSignup;

    // --- West (login form) ---
    private JPanel panelWest;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // --- Empty labels for spacing (manually) ---
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

    // --- Right (background image) ---
    private BackgroundPanel panelRight;

    public LoginPage() {
        super(new BorderLayout());

        // --- North ---
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(logoImg));
        lblLogo.setHorizontalAlignment(JLabel.CENTER);

        lblTitle = new JLabel("Login Page", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        btnHome = new JButton("Home");
        btnEvents = new JButton("Current Events");
        btnVenues = new JButton("Venues");
        btnSignup = new JButton("Login/SignUp");
        btnHome.addActionListener(this);
        btnEvents.addActionListener(this);
        btnVenues.addActionListener(this);
        btnSignup.addActionListener(this);


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

        btnSignup.setBackground(new Color(50, 50, 50));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setFocusPainted(false);
        btnSignup.setBorderPainted(false);
        btnSignup.setFont(new Font("Arial", Font.BOLD, 14));
        btnSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // --- West (login form) ---
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        lblUserName = new JLabel("Username:");
        lblUserName.setForeground(Color.WHITE);
        txtUserName = new JTextField(15);

        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField(15);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        // --- Empty labels manually ---
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
        rowNav.add(btnSignup);
        panelNorth.add(rowNav);

        // --- West layout ---
        panelWest.add(empty1);
        panelWest.add(empty2);

        JPanel userRow = new JPanel(new GridLayout(1, 2, 5, 5));
        userRow.setOpaque(false);
        userRow.add(lblUserName);
        userRow.add(txtUserName);
        panelWest.add(userRow);

        JPanel passRow = new JPanel(new GridLayout(1, 2, 5, 5));
        passRow.setOpaque(false);
        passRow.add(lblPassword);
        passRow.add(txtPassword);
        panelWest.add(passRow);

        JPanel loginRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginRow.setOpaque(false);
        loginRow.add(btnLogin);
        panelWest.add(loginRow);

        panelWest.add(empty3);
        panelWest.add(empty4);
        panelWest.add(empty5);
        panelWest.add(empty6);
        panelWest.add(empty7);
        panelWest.add(empty8);
        panelWest.add(empty9);
        panelWest.add(empty10);

        // --- Add panels to main ---
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    public void loginCustomer() {
    try {
        String username = txtUserName.getText();
        String password = new String(txtPassword.getPassword());

        String json = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/customers/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JOptionPane.showMessageDialog(this, "✅ Login successful!\nWelcome " + username);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Login failed! Check username or password.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage());
    }
}

private void switchToRegisterCustomerPage() {
    // Get the top-level JFrame that contains this panel
    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    topFrame.getContentPane().removeAll(); // remove the current LoginPage panel

    // Create RegisterCustomerPage panel and set it up
    RegisterCustomerPage registerPage = new RegisterCustomerPage();
    registerPage.setGUI(); // call the GUI setup method if it exists

    // Add the RegisterCustomerPage panel to the frame
    topFrame.add(registerPage);
    topFrame.revalidate(); // refresh the frame
    topFrame.repaint();
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

    if (source == btnLogin) {
        loginCustomer();
    } else if (source == btnSignup) {
        switchToRegisterCustomerPage();
    }
}



    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(loginPage);
        frame.setVisible(true);
    }
}
