package za.ac.cput.views;

import za.ac.cput.domain.endusers.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RegisterAdminPage extends JPanel implements ActionListener {


    //NORTH
    private JPanel panelNorth;
    private JLabel lblLogo, lblTitle;
    private JButton btnHome, btnEvents, btnVenues, btnLoginPage;

    //WEST
    private JPanel panelWest;
    private JLabel lblUserName, lblFullName, lblEmail, lblPassword;
    private JLabel lblStatus, lblCreatedBy, lblCreatedDate, lblLastLogin;

    private JTextField txtUserName, txtFullName, txtEmail, txtCreatedBy;
    private JPasswordField txtPassword;
    private JComboBox<String> comboStatus;
    private JTextField txtCreatedDate, txtLastLogin;

    private JButton btnRegister, btnClear, btnCancel;

    //RIGHT
    private BackgroundPanel panelRight;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RegisterAdminPage(CardLayout cardLayout, JPanel mainPanel) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        //NORTH
        panelNorth = new JPanel(new GridLayout(3, 1));
        panelNorth.setBackground(Color.BLACK);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Logo.jpg"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(logoImg));
        lblLogo.setHorizontalAlignment(JLabel.CENTER);

        lblTitle = new JLabel("Register Admin", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        btnHome = new JButton("Home");
        btnEvents = new JButton("Current Events");
        btnVenues = new JButton("Venues");
        btnLoginPage = new JButton("Login Page");

        JButton[] navButtons = {btnHome, btnEvents, btnVenues, btnLoginPage};
        for (JButton b : navButtons) {
            b.setBackground(new Color(50, 50, 50));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        // ----- WEST -----
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

        lblStatus = new JLabel("Status:");
        lblStatus.setForeground(Color.WHITE);
        comboStatus = new JComboBox<>();
        comboStatus.addItem("ACTIVE");
        comboStatus.addItem("INACTIVE");
        comboStatus.addItem("SUSPENDED");

        lblCreatedBy = new JLabel("Created By:");
        lblCreatedBy.setForeground(Color.WHITE);
        txtCreatedBy = new JTextField(15);

        lblCreatedDate = new JLabel("Created Date:");
        lblCreatedDate.setForeground(Color.WHITE);
        txtCreatedDate = new JTextField(15);
        txtCreatedDate.setEditable(false);

        lblLastLogin = new JLabel("Last Login:");
        lblLastLogin.setForeground(Color.WHITE);
        txtLastLogin = new JTextField(15);
        txtLastLogin.setEditable(false);

        btnRegister = new JButton("Register Admin");
        btnRegister.addActionListener(this);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        //RIGHT
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/bowtie.JPG"));
        panelRight = new BackgroundPanel(bgIcon.getImage());

        setGUI();
    }

    public void setGUI() {
        // NORTH
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

        // ----- WEST (MANUAL ROWS) -----
        JPanel rowUserName = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowUserName.setOpaque(false);
        rowUserName.add(lblUserName);
        rowUserName.add(txtUserName);
        panelWest.add(rowUserName);

        JPanel rowFullName = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowFullName.setOpaque(false);
        rowFullName.add(lblFullName);
        rowFullName.add(txtFullName);
        panelWest.add(rowFullName);

        JPanel rowEmail = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowEmail.setOpaque(false);
        rowEmail.add(lblEmail);
        rowEmail.add(txtEmail);
        panelWest.add(rowEmail);

        JPanel rowPassword = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowPassword.setOpaque(false);
        rowPassword.add(lblPassword);
        rowPassword.add(txtPassword);
        panelWest.add(rowPassword);

        JPanel rowStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowStatus.setOpaque(false);
        rowStatus.add(lblStatus);
        rowStatus.add(comboStatus);
        panelWest.add(rowStatus);

        JPanel rowCreatedBy = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowCreatedBy.setOpaque(false);
        rowCreatedBy.add(lblCreatedBy);
        rowCreatedBy.add(txtCreatedBy);
        panelWest.add(rowCreatedBy);

        JPanel rowCreatedDate = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowCreatedDate.setOpaque(false);
        rowCreatedDate.add(lblCreatedDate);
        rowCreatedDate.add(txtCreatedDate);
        panelWest.add(rowCreatedDate);

        JPanel rowLastLogin = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        rowLastLogin.setOpaque(false);
        rowLastLogin.add(lblLastLogin);
        rowLastLogin.add(txtLastLogin);
        panelWest.add(rowLastLogin);

        JPanel rowButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        rowButtons.setOpaque(false);
        rowButtons.add(btnRegister);
        rowButtons.add(btnClear);
        rowButtons.add(btnCancel);
        panelWest.add(rowButtons);

        // ----- MAIN -----
        add(panelNorth, BorderLayout.NORTH);
        add(panelWest, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            try {
                String username = txtUserName.getText();
                String fullName = txtFullName.getText();
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());
                String status = (String) comboStatus.getSelectedItem();
                String createdBy = txtCreatedBy.getText();

                String jsonData = String.format(
                        "{\"userName\":\"%s\",\"fullName\":\"%s\",\"emailAddress\":\"%s\",\"password\":\"%s\",\"status\":\"%s\",\"createdBy\":\"%s\"}",
                        username, fullName, email, password, status, createdBy
                );

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/admin/create?currentAdminUsername=" + createdBy))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 || response.statusCode() == 201) {
                    JOptionPane.showMessageDialog(this, "Admin Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration Failed: " + response.body(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnClear) {
            txtUserName.setText("");
            txtFullName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            comboStatus.setSelectedIndex(0);
            txtCreatedBy.setText("");
        }

        if (e.getSource() == btnCancel) {
            SwingUtilities.getWindowAncestor(this).dispose();
        }
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

    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Register Admin Page");
        RegisterAdminPage registerPage = new RegisterAdminPage();
        registerPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(registerPage);
        frame.setVisible(true);
    }*/
}
