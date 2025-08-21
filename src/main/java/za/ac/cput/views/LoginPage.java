package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JTextField txtUsername, txtPassword;
    private JLabel lblUsername, lblPassword, lblLoginPage;
    private JButton btnLogin;
    private Font ft1;

    public LoginPage() {
        super();
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();

        lblLoginPage = new JLabel("Login Page");
        lblUsername = new JLabel(" Username: ");
        lblPassword = new JLabel(" Password: ");

        txtUsername = new JTextField(15);
        txtPassword = new JTextField(15);

        btnLogin = new JButton("Login");

        ft1 = new Font("Arial", Font.BOLD, 18);
    }

    public void setGUILogin() {
        panelCenter.setLayout(new GridLayout(2, 2));

        panelNorth.add(lblLoginPage);
        lblLoginPage.setFont(ft1);

        panelCenter.add(lblUsername);
        panelCenter.add(txtUsername);
        panelCenter.add(lblPassword);
        panelCenter.add(txtPassword);

        panelSouth.add(btnLogin);

        setLayout(new BorderLayout());
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        LoginPage loginPage = new LoginPage();
        loginPage.setGUILogin();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.add(loginPage);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle login logic here
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        JOptionPane.showMessageDialog(this,
                "Login attempt: " + username + " / " + password);
    }
}
