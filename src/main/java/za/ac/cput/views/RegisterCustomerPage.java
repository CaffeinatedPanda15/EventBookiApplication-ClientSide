package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterCustomerPage extends JPanel implements ActionListener {
    // === North (3 rows × 6 cols) ===
    private JPanel northPanel;
    private JPanel northRow1Col1, northRow1Col2, northRow1Col3, northRow1Col4, northRow1Col5, northRow1Col6;
    private JPanel northRow2Col1, northRow2Col2, northRow2Col3, northRow2Col4, northRow2Col5, northRow2Col6;
    private JPanel northRow3Col1, northRow3Col2, northRow3Col3, northRow3Col4, northRow3Col5, northRow3Col6;

    // === Center (form) ===
    private JLabel lblTitle, lblUserName, lblFullName, lblEmail, lblPassword,
            lblUserType, lblAddress, lblContact;
    private JTextField txtUserName, txtFullName, txtEmail, txtAddress, txtContact;
    private JPasswordField txtPassword;
    private JComboBox<String> cbxUserType;
    private JButton btnRegister;

    private BackgroundPanel backgroundPanel;

    public RegisterCustomerPage() {
        super(new BorderLayout());

        // ---- Background for center content ----
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/background.jpg"));
        backgroundPanel = new BackgroundPanel(bgIcon.getImage());
        backgroundPanel.setLayout(new BorderLayout(10, 10));

        // ---- North grid (3 rows × 6) to match Login ----
        northPanel = new JPanel(new GridLayout(3, 6));
        // Row 1
        northRow1Col1 = new JPanel(new BorderLayout()); northRow1Col2 = new JPanel(); northRow1Col3 = new JPanel();
        northRow1Col4 = new JPanel(); northRow1Col5 = new JPanel(); northRow1Col6 = new JPanel();
        // Row 2
        northRow2Col1 = new JPanel(); northRow2Col2 = new JPanel(); northRow2Col3 = new JPanel();
        northRow2Col4 = new JPanel(); northRow2Col5 = new JPanel(); northRow2Col6 = new JPanel();
        // Row 3
        northRow3Col1 = new JPanel(); northRow3Col2 = new JPanel(); northRow3Col3 = new JPanel();
        northRow3Col4 = new JPanel(); northRow3Col5 = new JPanel(); northRow3Col6 = new JPanel();

        // ---- Form UI ----
        lblTitle = new JLabel("Register as Customer", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        lblUserName = new JLabel("Username:");
        lblFullName = new JLabel("Full Name:");
        lblEmail = new JLabel("Email Address:");
        lblPassword = new JLabel("Password:");
        lblUserType = new JLabel("User Type:");
        lblAddress = new JLabel("Address:");
        lblContact = new JLabel("Contact Number:");

        // make labels visible on image
        for (JLabel l : new JLabel[]{lblUserName, lblFullName, lblEmail, lblPassword, lblUserType, lblAddress, lblContact}) {
            l.setForeground(Color.WHITE);
        }

        txtUserName = new JTextField(15);
        txtFullName = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtAddress = new JTextField(15);
        txtContact = new JTextField(15);

        cbxUserType = new JComboBox<>(new String[]{"CUSTOMER", "ADMIN"});
        btnRegister = new JButton("Register");
    }

    public void setGUI() {
        Color darkGray = new Color(25, 25, 25);
        Color black = new Color(0, 0, 0);

        // ---------- NORTH STYLING ----------
        // Row 1 dark gray
        northRow1Col1.setBackground(darkGray); northRow1Col2.setBackground(darkGray); northRow1Col3.setBackground(darkGray);
        northRow1Col4.setBackground(darkGray); northRow1Col5.setBackground(darkGray); northRow1Col6.setBackground(darkGray);
        // Row 2 black
        northRow2Col1.setBackground(black); northRow2Col2.setBackground(black); northRow2Col3.setBackground(black);
        northRow2Col4.setBackground(black); northRow2Col5.setBackground(black); northRow2Col6.setBackground(black);
        // Row 3 dark gray
        northRow3Col1.setBackground(darkGray); northRow3Col2.setBackground(darkGray); northRow3Col3.setBackground(darkGray);
        northRow3Col4.setBackground(darkGray); northRow3Col5.setBackground(darkGray); northRow3Col6.setBackground(darkGray);

        // Logo in Row1-Col1
        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        JLabel logo = new JLabel(icon);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        northRow1Col1.add(logo, BorderLayout.CENTER);

        // Row 3 navigation buttons
        JButton btnHome = new JButton("Home");
        JButton btnEvents = new JButton("Current Events");
        JButton btnVenues = new JButton("Venues");
        JButton btnLoginSignup = new JButton("Login/SignUp");

        JButton[] navButtons = {btnHome, btnEvents, btnVenues, btnLoginSignup};
        for (JButton b : navButtons) {
            b.setBackground(new Color(50, 50, 50));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        northRow3Col1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); northRow3Col1.add(btnHome);
        northRow3Col2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); northRow3Col2.add(btnEvents);
        northRow3Col3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); northRow3Col3.add(btnVenues);
        northRow3Col4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); northRow3Col4.add(btnLoginSignup);

        // Add all cells into the north grid
        northPanel.add(northRow1Col1); northPanel.add(northRow1Col2); northPanel.add(northRow1Col3);
        northPanel.add(northRow1Col4); northPanel.add(northRow1Col5); northPanel.add(northRow1Col6);

        northPanel.add(northRow2Col1); northPanel.add(northRow2Col2); northPanel.add(northRow2Col3);
        northPanel.add(northRow2Col4); northPanel.add(northRow2Col5); northPanel.add(northRow2Col6);

        northPanel.add(northRow3Col1); northPanel.add(northRow3Col2); northPanel.add(northRow3Col3);
        northPanel.add(northRow3Col4); northPanel.add(northRow3Col5); northPanel.add(northRow3Col6);

        // Wrapper to control north height (tune to match Login)
        JPanel wrapperNorth = new JPanel(new BorderLayout());
        wrapperNorth.add(northPanel, BorderLayout.CENTER);
        wrapperNorth.setPreferredSize(new Dimension(1000, 230)); // adjust as needed
        add(wrapperNorth, BorderLayout.NORTH);

        // ---------- CENTER (on background image) ----------
        // Title on top of center
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(lblTitle);
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);

        // Form grid
        JPanel panelCenter = new JPanel(new GridLayout(7, 2, 10, 10));
        panelCenter.setOpaque(false);
        panelCenter.add(lblUserName); panelCenter.add(txtUserName);
        panelCenter.add(lblFullName); panelCenter.add(txtFullName);
        panelCenter.add(lblEmail); panelCenter.add(txtEmail);
        panelCenter.add(lblPassword); panelCenter.add(txtPassword);
        panelCenter.add(lblUserType); panelCenter.add(cbxUserType);
        panelCenter.add(lblAddress); panelCenter.add(txtAddress);
        panelCenter.add(lblContact); panelCenter.add(txtContact);
        backgroundPanel.add(panelCenter, BorderLayout.CENTER);

        // Register button at bottom
        JPanel panelSouth = new JPanel();
        panelSouth.setOpaque(false);
        panelSouth.add(btnRegister);
        backgroundPanel.add(panelSouth, BorderLayout.SOUTH);

        add(backgroundPanel, BorderLayout.CENTER);

        btnRegister.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = txtUserName.getText();
        String fullName = txtFullName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String userType = (String) cbxUserType.getSelectedItem();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        JOptionPane.showMessageDialog(this,
                "Registered Successfully:\n" +
                        "Username: " + userName + "\n" +
                        "Full Name: " + fullName + "\n" +
                        "Email: " + email + "\n" +
                        "Password: " + password + "\n" +
                        "User Type: " + userType + "\n" +
                        "Address: " + address + "\n" +
                        "Contact: " + contact);
    }

    // Background painter for the center
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer Registration");
        RegisterCustomerPage registerPage = new RegisterCustomerPage();
        registerPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(registerPage);
        frame.setVisible(true);
    }
}
