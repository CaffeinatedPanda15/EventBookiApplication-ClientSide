package za.ac.cput.views;

import org.json.JSONObject;
import za.ac.cput.domain.eventdomains.Venue;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GUIs {
    private JPanel panelRoot;
    private JPanel panelLoginPage;
    private JPanel panelAddAdminPahge;
    private JPanel PanelHomePage;
    private JPanel panelCateringPage;
    private JPanel panelVenuePage;
    private JPanel loginPanel;
    private JPanel panelNorth;
    private JPanel panelWest;
    private JPanel panelEast;
    private JPanel panelSouth;
    private JPanel panelCenter;
    private JLabel labelTitle;
    private JLabel labelUsername;
    private JTextField tboxUsername;
    private JTextField tboxPassword;
    private JLabel labelPassword;
    private JButton buttonLogin;
    private JButton buttonClear;
    private JPanel panelHome;
    private JPanel panelHomeNorth;
    private JPanel panelHomeSouth;
    private JPanel panelNavButton;
    private JPanel panelTitle;
    private JButton buttonCatering;
    private JButton venueButton;
    private JButton logoutButton;
    private JScrollPane jscrollCenter;
    private JPanel panelEventList;
    private JPanel paneleditvenue;
    private JPanel panelEditlogNorth;
    private JPanel panelEditvenEast;
    private JPanel panelEditvenSouth;
    private JPanel panelEditvenWest;
    private JPanel panelEditlogCenter;
    private JPanel panelEdutVenTitle;
    private JLabel panelEditvenTitle;
    private JPanel panelEditvenNavButtons;
    private JButton homeButton;
    private JButton cateringButton;
    private JButton logoutButton1;
    private JTextField textField1;
    private JLabel labelEventnameEdit;
    private JLabel labelVenueeditDescriptions;
    private JTextArea textArea1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button4;
    private JPanel panelEditvenbuttons;
    private JButton editButton;
    private JButton saveButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JLabel labelEmpty1;
    private JLabel labelEmpty2;
    private JLabel labelEmpty3;
    private JLabel labelEmpty4;
    private JLabel labelEmpty5;
    private JPanel panelVenueNorth;
    private JPanel panelVenueEast;
    private JPanel panelVenueSouth;
    private JPanel panelVenueWest;
    private JPanel panelVenueCenter;
    private JPanel panelVenueTitle;
    private JPanel panelVenueNavButtons;
    private JButton buttonVenueHome;
    private JButton buttonVenueCatering;
    private JButton buttonVenueAddAdmin;
    private JButton buttonVenueLogout;
    private JPanel panelVenueList;
    private JScrollPane jscrollVenue;
    private JLabel labelVenueTitle;
    private JButton refreshVenueButton;
    private JLabel labelVenueImage;
    private JButton buttonAddVenue;

    private final CardLayout cardLayout;


    public GUIs() {
        cardLayout = new CardLayout();
        panelRoot.setLayout(cardLayout);

        panelRoot.add(panelLoginPage, "login");
        panelRoot.add(panelAddAdminPahge, "addAdmin");
        panelRoot.add(PanelHomePage, "home");
        panelRoot.add(panelCateringPage, "catering");
        panelRoot.add(panelVenuePage, "venue");
        panelRoot.add(paneleditvenue, "editvenue");

        //Login  Buttons
        buttonLogin.addActionListener(e -> loginAdmin());
        buttonClear.addActionListener(e -> clearLoginFields());

        //Home Buttons
        buttonCatering.addActionListener(e -> cardLayout.show(panelRoot, "catering"));
        venueButton.addActionListener(e -> cardLayout.show(panelRoot, "venue"));
        logoutButton.addActionListener(e -> cardLayout.show(panelRoot, "login"));


        //Edit Venue Buttons
        homeButton.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        cateringButton.addActionListener(e -> cardLayout.show(panelRoot, "catering"));
        logoutButton1.addActionListener(e -> cardLayout.show(panelRoot, "login"));

    }

    private void loginAdmin() {
        try {
            String email = tboxUsername.getText();
            String password = tboxPassword.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both email and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String json = String.format("{\"emailAddress\":\"%s\",\"password\":\"%s\"}", email, password);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonObj = new JSONObject(responseBody);
                String fullName = jsonObj.getString("fullName");

                JOptionPane.showMessageDialog(null, "✅ Admin Login successful!\nWelcome " + fullName);

                // Switch to Admin Home
                cardLayout.show(panelRoot, "home");

            } else {
                JOptionPane.showMessageDialog(null, "❌ Login failed! Check email or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//end of login method

    private void clearLoginFields() {
        tboxUsername.setText("");
        tboxPassword.setText("");
    }//end of clearLoginFields method

    public JPanel getPanelRoot() {
        return panelRoot;
    }

    //end of login page and start of venue

    public void loadVenues(java.util.List<Venue> venues) {
        panelVenueList.removeAll();

        for (Venue venue : venues) {
            VenueBox box = new VenueBox(venue, e -> {

                populateEditVenuePage(venue);
                cardLayout.show(panelRoot, "venueEditPage");
            });
            panelVenueList.add(box.getPanelBoxRoot());
            panelVenueList.add(Box.createVerticalStrut(10)); // spacing
        }

        panelVenueList.revalidate();
        panelVenueList.repaint();
    }

    private void populateEditVenuePage(Venue venue) {
        textField1.setText(String.valueOf(venue.getVenueId()));
        labelEventnameEdit.setText(venue.getVenueName());
        textArea1.setText(venue.getVenueDescription());
        textField2.setText(venue.getVenueAddress());
        textField3.setText(String.valueOf(venue.getVenueCapacity()));
        textField4.setText(String.valueOf(venue.getVenuePrice()));

        if (venue.getVenueImage() != null) {
            ImageIcon image = new ImageIcon(venue.getVenueImage());
            Image img = image.getImage().getScaledInstance(
                    labelVenueImage.getWidth(),
                    labelVenueImage.getHeight(),
                    Image.SCALE_SMOOTH
            );
            labelVenueImage.setIcon(new ImageIcon(img));
        } else {
            labelVenueImage.setIcon(null);
        }
    }

    //end of venue page and start of edit venue page
}//end if class
