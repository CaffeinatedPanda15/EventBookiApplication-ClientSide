package za.ac.cput;

import za.ac.cput.views.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
    /*    JFrame frame = new JFrame("EventBookingApplication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        HomePage HomePage = new HomePage(cardLayout, mainPanel);
        CreateEventPage CreateEventPage = new CreateEventPage(cardLayout, mainPanel);
        RegisterAdminPage registerAdminPage = new RegisterAdminPage(cardLayout, mainPanel);
        LoginPage loginPage = new LoginPage(cardLayout, mainPanel, HomePage, registerAdminPage);

        mainPanel.add(loginPage, "login");
        mainPanel.add(CreateEventPage, "CreateEventPage");
        mainPanel.add(registerAdminPage, "registerAdminPage");
        mainPanel.add(HomePage, "HomePage");


        cardLayout.show(mainPanel, "login"); // Landing page = Login

        frame.add(mainPanel);
        frame.setVisible(true);*/

        SwingUtilities.invokeLater(() -> {
            // Create a frame (window)
            JFrame frame = new JFrame("Login Page");


            // Basic window setup
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null); // center window
            frame.setVisible(true);
        });



    }
}
