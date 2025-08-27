package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {

    // North rows
    private JPanel northRow1, northRow2, northRow3;

    // Center login components
    private JPanel panelTitle, panelUser, panelPass, panelButton;
    private JTextField txtUsername, txtPassword;
    private JLabel lblUsername, lblPassword, lblLoginPage;
    private JButton btnLogin;

    private Font ft1;

    public LoginPage() {
        super();
        ft1 = new Font("Arial", Font.BOLD, 18);

        // North rows
        northRow1 = new JPanel(new BorderLayout());
        northRow2 = new JPanel();
        northRow3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // Login panels
        panelTitle = new JPanel();
        panelUser = new JPanel();
        panelPass = new JPanel();
        panelButton = new JPanel();

        lblLoginPage = new JLabel("Login Page", SwingConstants.CENTER);
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUsername = new JTextField(15);
        txtPassword = new JTextField(15);
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
    }

    public void setGUI() {
        Color darkGray = new Color(25,25,25);
        Color black = new Color(0,0,0);

        // ---------- NORTH ----------
        JPanel northWrapper = new JPanel();
        northWrapper.setLayout(new BoxLayout(northWrapper, BoxLayout.Y_AXIS));

        // Row 1: Logo
        northRow1.setBackground(darkGray);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        JLabel logo = new JLabel(icon);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        northRow1.add(logo, BorderLayout.CENTER);
        northRow1.setPreferredSize(new Dimension(1000, 60));
        northWrapper.add(northRow1);

        // Row 2: Separator bar
        northRow2.setBackground(black);
        northRow2.setPreferredSize(new Dimension(1000, 20));
        northWrapper.add(northRow2);

        // Row 3: Buttons
        northRow3.setBackground(darkGray);
        JButton btnHome = new JButton("Home");
        JButton btnEvents = new JButton("Current Events");
        JButton btnVenues = new JButton("Venues");
        JButton btnLoginSignup = new JButton("Login/SignUp");

        JButton[] buttons = {btnHome, btnEvents, btnVenues, btnLoginSignup};
        Color buttonColor = new Color(50,50,50);
        Color buttonText = Color.WHITE;
        Font btnFont = new Font("Arial", Font.BOLD, 14);

        for (JButton btn : buttons) {
            btn.setBackground(buttonColor);
            btn.setForeground(buttonText);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setFont(btnFont);
            northRow3.add(btn);
        }
        northRow3.setPreferredSize(new Dimension(1000, 50));
        northWrapper.add(northRow3);

        // ---------- CENTER ----------
        JPanel centerPanel = new JPanel() {
            private Image background = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        centerPanel.setLayout(new BorderLayout());

        // Original login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        panelTitle.setOpaque(false); panelUser.setOpaque(false);
        panelPass.setOpaque(false); panelButton.setOpaque(false);

        lblLoginPage.setFont(ft1); lblLoginPage.setForeground(Color.WHITE);

        panelTitle.add(lblLoginPage);
        panelUser.add(lblUsername); panelUser.add(txtUsername);
        panelPass.add(lblPassword); panelPass.add(txtPassword);
        panelButton.add(btnLogin);

        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(panelTitle);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(panelUser);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(panelPass);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(panelButton);
        loginPanel.add(Box.createVerticalGlue());

        centerPanel.add(loginPanel, BorderLayout.WEST);

        // ---------- ADD TO MAIN ----------
        setLayout(new BorderLayout());
        add(northWrapper, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        JOptionPane.showMessageDialog(this,"Login attempt: " + username + " / " + password);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.add(loginPage);
        frame.setVisible(true);
    }
}
