package za.ac.cput;

import za.ac.cput.views.AdminHomePage;
import za.ac.cput.views.HomePage;
import za.ac.cput.views.LoginPage;
import za.ac.cput.views.RegisterAdminPage;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("EventBookingApplication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        HomePage HomePage = new HomePage(cardLayout, mainPanel);
        RegisterAdminPage registerAdminPage = new RegisterAdminPage(cardLayout, mainPanel);
        LoginPage loginPage = new LoginPage(cardLayout, mainPanel, HomePage, registerAdminPage);

        mainPanel.add(loginPage, "login");
        mainPanel.add(registerAdminPage, "registerAdminPage");
        mainPanel.add(HomePage, "HomePage");


        cardLayout.show(mainPanel, "login"); // Landing page = Login

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
