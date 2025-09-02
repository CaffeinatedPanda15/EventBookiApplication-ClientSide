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

    // North Panel
    private JPanel panelNorth;
    private JLabel labelLogo;
    private JLabel labelTitle;
    private JButton buttonHome;
    private JButton buttonEvents;
    private JButton buttonVenues;
    private JButton buttonSignup;

    // West/Login Panel
    private JPanel panelWest;
    private JLabel labelEmailAddress;
    private JLabel labelPassword;
    private JTextField tfieldUserName;
    private JPasswordField txtPassword;
    private JButton buttonLogin;

    // Spacing panels
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


    private BackgroundPanel panelRight;

    public LoginPage() {
        super(new BorderLayout());

        // north panel
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        labelLogo = new JLabel(new ImageIcon(logoImg));
        labelLogo.setHorizontalAlignment(JLabel.CENTER);

        labelTitle = new JLabel("Login Page", JLabel.CENTER);
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


        buttonSignup = new JButton("Login/SignUp");
        buttonSignup.setBackground(new Color(50, 50, 50));
        buttonSignup.setForeground(Color.WHITE);
        buttonSignup.setFocusPainted(false);
        buttonSignup.setBorderPainted(false);
        buttonSignup.setFont(new Font("Arial", Font.BOLD, 14));
        buttonSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        //add actionlisytener
        buttonHome.addActionListener(this);
        buttonEvents.addActionListener(this);
        buttonVenues.addActionListener(this);
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

        // empty panels for spacing
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

        // background image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/bowtie.JPG"));
        panelRight = new BackgroundPanel(bgIcon.getImage());
    }

    public void setGUI() {

        //North
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

        // West
        panelWest.add(empty1);
        panelWest.add(empty2);

        JPanel userRow = new JPanel(new GridLayout(1, 2, 5, 5));
        userRow.setOpaque(false);
        userRow.add(labelEmailAddress);
        userRow.add(tfieldUserName);
        panelWest.add(userRow);

        JPanel passRow = new JPanel(new GridLayout(1, 2, 5, 5));
        passRow.setOpaque(false);
        passRow.add(labelPassword);
        passRow.add(txtPassword);
        panelWest.add(passRow);

        JPanel loginRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginRow.setOpaque(false);
        loginRow.add(buttonLogin);
        panelWest.add(loginRow);

        panelWest.add(empty3);
        panelWest.add(empty4);
        panelWest.add(empty5);
        panelWest.add(empty6);
        panelWest.add(empty7);
        panelWest.add(empty8);
        panelWest.add(empty9);
        panelWest.add(empty10);

        // add panels to main panel
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    public void loginCustomer() {
        try {
            String email = tfieldUserName.getText();
            String password = new String(txtPassword.getPassword());


            String json = String.format("{\"emailAddress\":\"%s\",\"password\":\"%s\"}", email, password);


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/customer/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                org.json.JSONObject jsonObj = new org.json.JSONObject(responseBody);
                String fullName = jsonObj.getString("fullName");

                JOptionPane.showMessageDialog(this, "✅ Login successful!\nWelcome " + fullName);
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                topFrame.getContentPane().removeAll();

                HomePage homePage = new HomePage();
                homePage.setGUI();
                topFrame.add(homePage);
                topFrame.revalidate();
                topFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "❌ Login failed! Check email or password.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage());
        }
    }
private void switchToRegisterCustomerPage() {

    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    topFrame.getContentPane().removeAll();


   RegisterCustomerPage registerPage = new RegisterCustomerPage();
   registerPage.setGUI();


   topFrame.add(registerPage);
    topFrame.revalidate();
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

    if (source == buttonLogin) {
        loginCustomer();
    } else if (source == buttonSignup) {
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
