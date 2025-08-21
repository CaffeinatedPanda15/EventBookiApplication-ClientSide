package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRegisterPage extends JPanel implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblTitle, lblUserName, lblFullName, lblEmail, lblPassword,
            lblUserType, lblAddress, lblContact;
    private JTextField txtUserName, txtFullName, txtEmail, txtAddress, txtContact;
    private JPasswordField txtPassword;
    private JComboBox<String> cbxUserType; // Replace String with UserType enum later
    private JButton btnRegister;

    public CustomerRegisterPage() {
        super();
        panelNorth = new JPanel();
        panelCenter = new JPanel(new GridLayout(7, 2, 5, 5));
        panelSouth = new JPanel();

        lblTitle = new JLabel("Register as Customer");
        lblUserName = new JLabel("Username:");
        lblFullName = new JLabel("Full Name:");
        lblEmail = new JLabel("Email Address:");
        lblPassword = new JLabel("Password:");
        lblUserType = new JLabel("User Type:");
        lblAddress = new JLabel("Address:");
        lblContact = new JLabel("Contact Number:");

        txtUserName = new JTextField(15);
        txtFullName = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtAddress = new JTextField(15);
        txtContact = new JTextField(15);

        cbxUserType = new JComboBox<>(new String[]{"CUSTOMER", "ADMIN", "GUEST"});
        btnRegister = new JButton("Register");
    }

    public void setGUI() {
        panelNorth.add(lblTitle);

        panelCenter.add(lblUserName);
        panelCenter.add(txtUserName);

        panelCenter.add(lblFullName);
        panelCenter.add(txtFullName);

        panelCenter.add(lblEmail);
        panelCenter.add(txtEmail);

        panelCenter.add(lblPassword);
        panelCenter.add(txtPassword);

        panelCenter.add(lblUserType);
        panelCenter.add(cbxUserType);

        panelCenter.add(lblAddress);
        panelCenter.add(txtAddress);

        panelCenter.add(lblContact);
        panelCenter.add(txtContact);

        panelSouth.add(btnRegister);

        setLayout(new BorderLayout(10, 10));
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        btnRegister.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = txtUserName.getText();
        String fullName = txtFullName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String userType = (String) cbxUserType.getSelectedItem(); // replace with UserType
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer Registration");
        CustomerRegisterPage registerPage = new CustomerRegisterPage();
        registerPage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(registerPage);
        frame.setVisible(true);
    }
}
