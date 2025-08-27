package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterCustomerPage extends JPanel implements ActionListener {

    // --- North ---
    private JPanel panelNorth;
    private JLabel lblLogo;
    private JLabel lblTitle;
    private JButton btnHome;
    private JButton btnEvents;
    private JButton btnVenues;
    private JButton btnLoginPage;

    // --- West (registration form) ---
    private JPanel panelWest;
    private JLabel lblUserName;
    private JLabel lblFullName;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JLabel lblUserType;
    private JLabel lblAddress;
    private JLabel lblContact;

    private JTextField txtUserName;
    private JTextField txtFullName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<String> comboUserType;
    private JTextField txtAddress;
    private JTextField txtContact;

    private JButton btnRegister;

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
    private JLabel empty11;
    private JLabel empty12;

    // --- Right (background image) ---
    private BackgroundPanel panelRight;

    public RegisterCustomerPage() {
        super(new BorderLayout());

        // --- North ---
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(logoImg));
        lblLogo.setHorizontalAlignment(JLabel.CENTER);

        lblTitle = new JLabel("Register as Customer", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        btnHome = new JButton("Home");
        btnEvents = new JButton("Current Events");
        btnVenues = new JButton("Venues");
        btnLoginPage = new JButton("Login Page");

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

        btnLoginPage.setBackground(new Color(50, 50, 50));
        btnLoginPage.setForeground(Color.WHITE);
        btnLoginPage.setFocusPainted(false);
        btnLoginPage.setBorderPainted(false);
        btnLoginPage.setFont(new Font("Arial", Font.BOLD, 14));
        btnLoginPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // --- West (registration form) ---
        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        lblUserName = new JLabel("Username:");
        lblUserName.setForeground(Color.WHITE);
        txtUserName = new JTextField(15);

        lblFullName = new JLabel("Full Name:");
        lblFullName.setForeground(Color.WHITE);
        txtFullName = new JTextField(15);

        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        txtEmail = new JTextField(15);

        lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField(15);

        lblUserType = new JLabel("User Type:");
        lblUserType.setForeground(Color.WHITE);
        comboUserType = new JComboBox<>();
        comboUserType.addItem("Regular");
        comboUserType.addItem("VIP");

        lblAddress = new JLabel("Address:");
        lblAddress.setForeground(Color.WHITE);
        txtAddress = new JTextField(15);

        lblContact = new JLabel("Contact Number:");
        lblContact.setForeground(Color.WHITE);
        txtContact = new JTextField(15);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this);

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
        rowNav.add(btnLoginPage);
        panelNorth.add(rowNav);

        // --- West layout ---
        panelWest.add(empty1);
        panelWest.add(empty2);

        JPanel userRow = new JPanel(new GridLayout(1, 2, 5, 5));
        userRow.setOpaque(false);
        userRow.add(lblUserName);
        userRow.add(txtUserName);
        panelWest.add(userRow);

        JPanel fullNameRow = new JPanel(new GridLayout(1, 2, 5, 5));
        fullNameRow.setOpaque(false);
        fullNameRow.add(lblFullName);
        fullNameRow.add(txtFullName);
        panelWest.add(fullNameRow);

        JPanel emailRow = new JPanel(new GridLayout(1, 2, 5, 5));
        emailRow.setOpaque(false);
        emailRow.add(lblEmail);
        emailRow.add(txtEmail);
        panelWest.add(emailRow);

        JPanel passRow = new JPanel(new GridLayout(1, 2, 5, 5));
        passRow.setOpaque(false);
        passRow.add(lblPassword);
        passRow.add(txtPassword);
        panelWest.add(passRow);

        JPanel userTypeRow = new JPanel(new GridLayout(1, 2, 5, 5));
        userTypeRow.setOpaque(false);
        userTypeRow.add(lblUserType);
        userTypeRow.add(comboUserType);
        panelWest.add(userTypeRow);

        JPanel addressRow = new JPanel(new GridLayout(1, 2, 5, 5));
        addressRow.setOpaque(false);
        addressRow.add(lblAddress);
        addressRow.add(txtAddress);
        panelWest.add(addressRow);

        JPanel contactRow = new JPanel(new GridLayout(1, 2, 5, 5));
        contactRow.setOpaque(false);
        contactRow.add(lblContact);
        contactRow.add(txtContact);
        panelWest.add(contactRow);

        JPanel registerRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerRow.setOpaque(false);
        registerRow.add(btnRegister);
        panelWest.add(registerRow);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUserName.getText();
        String fullName = txtFullName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String userType = (String) comboUserType.getSelectedItem();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        JOptionPane.showMessageDialog(this, "Register attempt: "
                + username + " / "
                + fullName + " / "
                + email + " / "
                + password + " / "
                + userType + " / "
                + address + " / "
                + contact);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Register Customer Page");
        RegisterCustomerPage registerPage = new RegisterCustomerPage();
        registerPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(registerPage);
        frame.setVisible(true);
    }
}
