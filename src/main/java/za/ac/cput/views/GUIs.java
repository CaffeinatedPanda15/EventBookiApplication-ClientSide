package za.ac.cput.views;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.json.JSONObject;
import za.ac.cput.domain.endusers.Admin;
import za.ac.cput.domain.eventdomains.Venue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

public class GUIs {
    private JPanel panelRoot;
    private JPanel panelLoginPage;
    private JPanel panelAddAdminPage;
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
    private JTextField tboxVenueName;
    private JLabel labelEventnameEdit;
    private JLabel labelVenueeditDescriptions;
    private JTextArea tAreaDescription;
    private JTextField tboxAddress;
    private JTextField tboxCapacity;
    private JTextField tboxPrice;
    private JButton buttonAddImage;
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
    private JButton buttonVenueDisplayAdmin;
    private JButton buttonVenueLogout;
    private JPanel panelVenueList;
    private JScrollPane jscrollVenue;
    private JLabel labelVenueTitle;
    private JButton refreshVenueButton;
    private JLabel labelVenueImage;
    private JButton buttonAddVenue;
    private JButton buttonHomeDisplayAdmin;
    private JPanel panelAddAdminNorth;
    private JPanel panelAddAdminEast;
    private JPanel panelAddAdminSouth;
    private JPanel panelAddAdminWest;
    private JPanel panelAddAdminCenter;
    private JPanel panelAddAddNavButtons;
    private JButton buttonAddAdminHome;
    private JButton buttonAddAdminevents;
    private JButton buttonAddAdminVenue;
    private JButton buttonAddAdminCatering;
    private JTextField tboxAddAdminsUsername;
    private JTextField tboxLastLogin;
    private JTextField tboxAddAdminFullname;
    private JTextField tboxAddAdminEmail;
    private JTextField tboxAddAdminPassword;
    private JTextField tboxAddAdminCreatedBy;
    private JTextField tboxAddAdminCreatedDate;
    private JPanel panleAddAdminsButtons;
    private JButton buttonAdminRegister;
    private JButton buttonAdminClear;
    private JButton buttonAdminCancel;
    private JLabel kabekusername;
    private JLabel labelFullname;
    private JLabel labelEmail;
    private JLabel labelAdminAddPassword;
    private JLabel labelStatus;
    private JLabel labelCreatedBy;
    private JLabel labelCreatedDate;
    private JLabel labelLastLogin;
    private JComboBox cboxAddAdminStatus;
    private JButton buttonAddAdmin;
    private JPanel panelCateringNorth;
    private JPanel panelCateringWest;
    private JPanel panelCateringSouth;
    private JPanel panelCateringEast;
    private JPanel panelCateringCenter;
    private JScrollPane jScrollCatering;
    private JPanel panelCateringList;
    private JButton buttonAddCaterer;
    private JButton buttonCateringHome;
    private JButton buttonCateringEvents;
    private JButton buttonCateringVenue;
    private JButton buttonCateringLogoff;
    private JPanel panelCateringTitle;
    private JLabel LabelCateringTitle;
    private JPanel panelEditCatering;
    private JPanel panelEditCateringNorth;
    private JPanel panelEditCateringEast;
    private JPanel panelEditCateringSouth;
    private JPanel panelEditCateringWest;
    private JPanel panelEditCateringCenter;
    private JPanel panelEditCateringButtons;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panelEditCateringNavButtons;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JPanel panelEditCateringTitle;
    private JLabel labelEditCateringTitle;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button8;
    private JPanel panelDisplayAdmin;
    private JPanel panelDisplayAdminNorth;
    private JPanel panelDisplayAdminEast;
    private JPanel panelDisplayAdminSouth;
    private JPanel panelDisplayAdminWest;
    private JPanel panelDisplayAdminCenter;
    private JScrollPane jScrollAdmins;
    private JPanel panelAdminList;
    private JPanel panlDisplayAdminTitle;
    private JPanel panlDisplayAdminNavButtons;
    private JButton buttonAdminDetailsHome;
    private JButton buttonAdminDetailsEvents;
    private JButton buttonAdminDetailsVenue;
    private JButton buttonAdminDetailsCatering;
    private JPanel panelDisplayAdminButtons;
    private JButton buttonAddingAdmin;
    private JButton buttonRefreshAdminList;
    private JLabel labelAdminDetailsTitle;
    private JLabel labelAddAdminTItle;
    private JButton buttonAdminDetailsLogoff;
    private JButton buttonVenueEvents;

    private final CardLayout cardLayout;

    private byte[] selectedImageBytes;


    public GUIs() {
        cardLayout = new CardLayout();
        panelRoot.setLayout(cardLayout);

        panelRoot.add(panelLoginPage, "login");
        panelRoot.add(panelAddAdminPage, "addAdmin");
        panelRoot.add(PanelHomePage, "home");
        panelRoot.add(panelCateringPage, "catering");
        panelRoot.add(panelVenuePage, "venue");
        panelRoot.add(paneleditvenue, "editvenue");
        panelRoot.add(panelDisplayAdmin, "viewAdmins");

        jScrollAdmins.setViewportView(panelAdminList);
        jScrollAdmins.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollAdmins.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jscrollVenue.setViewportView(panelVenueList);

        panelAdminList.setLayout(new BoxLayout(panelAdminList, BoxLayout.Y_AXIS));
        panelAdminList.setBackground(new Color(245, 245, 245));


        //Login  Buttons
        buttonLogin.addActionListener(e -> loginAdmin());
        buttonClear.addActionListener(e -> clearLoginFields());

        //Display Admin Buttons
        buttonAddingAdmin.addActionListener(e -> cardLayout.show(panelRoot, "addAdmin"));
        buttonRefreshAdminList.addActionListener(e -> loadAdmins());
        buttonAdminDetailsHome.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        buttonAdminDetailsEvents.addActionListener(e -> cardLayout.show(panelRoot, "catering")); // or event page
        buttonAdminDetailsVenue.addActionListener(e -> cardLayout.show(panelRoot, "venue"));
        buttonAdminDetailsCatering.addActionListener(new CateringButtonListener());
        buttonAdminDetailsLogoff.addActionListener(e -> cardLayout.show(panelRoot, "login"));

        //Register Admin Buttons
        buttonAdminRegister.addActionListener(e -> registerAdmin());
        buttonAdminClear.addActionListener(e -> clearAdminFields());
        buttonAdminCancel.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        buttonAddAdminHome.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        buttonAddAdminevents.addActionListener(e -> cardLayout.show(panelRoot, "catering")); // or event page
        buttonAddAdminVenue.addActionListener(e -> cardLayout.show(panelRoot, "venue"));


        //Home Buttons
        buttonCatering.addActionListener(new CateringButtonListener());
        venueButton.addActionListener(e -> {cardLayout.show(panelRoot, "venue"); refreshVenueList();});
        logoutButton.addActionListener(e -> cardLayout.show(panelRoot, "login"));
        buttonHomeDisplayAdmin.addActionListener((e -> {cardLayout.show(panelRoot, "viewAdmins"); loadAdmins();}));


        //Venue Buttons
        buttonVenueHome.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        buttonVenueEvents.addActionListener(e -> cardLayout.show(panelRoot, "events"));
        buttonVenueCatering.addActionListener(new CateringButtonListener());
        buttonVenueDisplayAdmin.addActionListener(e -> cardLayout.show(panelRoot, "viewAdmins"));
        buttonVenueLogout.addActionListener(e -> cardLayout.show(panelRoot, "login"));
        buttonAddVenue.addActionListener(e -> cardLayout.show(panelRoot, "editvenue"));
        refreshVenueButton.addActionListener(e -> refreshVenueList());


        //Edit Venue Buttons
        homeButton.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        cateringButton.addActionListener(new CateringButtonListener());
        logoutButton1.addActionListener(e -> cardLayout.show(panelRoot, "login"));
        editButton.addActionListener(e -> setEditable(true));
        saveButton.addActionListener(e -> SaveVenue());
        clearButton.addActionListener(e -> ClearVenueEdit());
        deleteButton.addActionListener(e -> DeleteVenue());
        buttonAddImage.addActionListener(e -> uploadVenueImage());

        //Catering Buttons
        buttonCateringHome.addActionListener(e -> cardLayout.show(panelRoot, "home"));
        buttonCateringEvents.addActionListener(e -> cardLayout.show(panelRoot, "catering"));
        buttonCateringVenue.addActionListener(e -> cardLayout.show(panelRoot, "venue"));
        buttonCateringLogoff.addActionListener(e -> cardLayout.show(panelRoot, "login"));


        cardLayout.show(panelRoot, "home");


    }

//start of register admin

    private void registerAdmin() {
        try {
            String username = tboxAddAdminsUsername.getText();
            String fullName = tboxAddAdminFullname.getText();
            String email = tboxAddAdminEmail.getText();
            String password = tboxAddAdminPassword.getText();
            String status = (String) cboxAddAdminStatus.getSelectedItem();
            String createdBy = tboxAddAdminCreatedBy.getText();

            if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || status == null || status.isEmpty() || createdBy.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String json = String.format("{\"userName\":\"%s\",\"fullName\":\"%s\",\"emailAddress\":\"%s\",\"password\":\"%s\",\"status\":\"%s\",\"createdBy\":\"%s\"}",
                    username, fullName, email, password, status, createdBy);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/create?currentAdminUsername=" + createdBy))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201 || response.statusCode() == 200) {
                JOptionPane.showMessageDialog(null, "✅ Admin registered successfully!");
                clearAdminFields();
                cardLayout.show(panelRoot, "home");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Registration failed! Try again.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//end of register admin method


    private void clearAdminFields() {
        tboxAddAdminsUsername.setText("");
        tboxAddAdminFullname.setText("");
        tboxAddAdminEmail.setText("");
        tboxAddAdminPassword.setText("");
        cboxAddAdminStatus.setSelectedIndex(0);
        tboxAddAdminCreatedBy.setText("");
        tboxAddAdminCreatedDate.setText("");
    }//end of clearAdminFields method


//end of register admin method and start of display admin

    private java.util.List<Admin> fetchedAdmins() {
        java.util.List<Admin> admins = new java.util.ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/getAll"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                Admin[] adminArray = mapper.readValue(responseBody, Admin[].class);
                admins = java.util.Arrays.asList(adminArray);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Failed to load admins.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return admins;
    }


    public void loadAdmins() {
        panelAdminList.removeAll();

        java.util.List<Admin> admins = fetchedAdmins();

        if (admins.isEmpty()) {
            JLabel noAdminsLabel = new JLabel("No admins found.");
            noAdminsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            noAdminsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panelAdminList.setLayout(new BorderLayout());
            panelAdminList.add(noAdminsLabel, BorderLayout.CENTER);
        } else {
            panelAdminList.setLayout(new BoxLayout(panelAdminList, BoxLayout.Y_AXIS));

            for (int i = 0; i < admins.size(); i += 3) {
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                rowPanel.setBackground(new Color(245, 245, 245));

                for (int j = i; j < i + 3 && j < admins.size(); j++) {
                    Admin admin = admins.get(j);

                    JPanel adminBox = new JPanel();
                    adminBox.setLayout(new GridLayout(0, 1, 5, 2));
                    adminBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    adminBox.setBackground(new Color(240, 240, 240));
                    adminBox.setPreferredSize(new Dimension(300, 150));

                    adminBox.add(new JLabel("Username: " + admin.getUserName()));
                    adminBox.add(new JLabel("Full Name: " + admin.getFullName()));
                    adminBox.add(new JLabel("Email: " + admin.getEmailAddress()));
                    adminBox.add(new JLabel("Status: " + admin.getStatus()));
                    adminBox.add(new JLabel("Created By: " + admin.getCreatedBy()));
                    adminBox.add(new JLabel("Created Date: " + admin.getCreatedDate()));
                    adminBox.add(new JLabel("Last Login: " + (admin.getLastLogin() != null ? admin.getLastLogin() : "Never")));

                    rowPanel.add(adminBox);
                }

                panelAdminList.add(rowPanel);
            }

            // Fix the preferred size to make JScrollPane scroll properly
            panelAdminList.setPreferredSize(null); // reset
            panelAdminList.revalidate(); // force recalculation
        }

        panelAdminList.revalidate();
        panelAdminList.repaint();
    }//end of display admin method




    //end of display admin method and start of login


    private void loginAdmin() {
        try {
            String email = tboxUsername.getText();
            String password = tboxPassword.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both email and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String auth = email + ":" + password;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/profile"))
                    .header("Authorization", "Basic " + encodedAuth)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                String fullName = json.getString("fullName");

                JOptionPane.showMessageDialog(null, "✅ Welcome, " + fullName + "!");
                cardLayout.show(panelRoot, "home");
            } else if (response.statusCode() == 401) {
                JOptionPane.showMessageDialog(null, "❌ Invalid email or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Login failed! Try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
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

    public void refreshVenueList() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/venue/all"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                Venue[] venues = mapper.readValue(responseBody, Venue[].class);
                loadVenues(java.util.Arrays.asList(venues));
            } else {
                JOptionPane.showMessageDialog(null, "❌ Failed to load venues.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//end of refresh venue list method

    private void populateEditVenuePage(Venue venue) {
        currentEditingVenue = venue;
        tboxVenueName.setText(String.valueOf(venue.getVenueName()));
        labelEventnameEdit.setText(venue.getVenueName());
        tAreaDescription.setText(venue.getVenueDescription());
        tboxAddress.setText(venue.getVenueAddress());
        tboxCapacity.setText(String.valueOf(venue.getVenueCapacity()));
        tboxPrice.setText(String.valueOf(venue.getVenuePrice()));

        int width = 150;
        int height = 150;

        if (venue.getVenueImage() != null && venue.getVenueImage().length > 0) {
            ImageIcon image = new ImageIcon(venue.getVenueImage());
            Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            labelVenueImage.setIcon(new ImageIcon(img));
            labelVenueImage.setText("");
        } else {
            labelVenueImage.setIcon(null);
            labelVenueImage.setText("No Image Available");
            labelVenueImage.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    //end of venue page and start of edit venue page

    private Venue currentEditingVenue;

    public void setEditable(boolean isEditable) {
        tboxVenueName.setEditable(isEditable);
        tAreaDescription.setEditable(isEditable);
        tboxAddress.setEditable(isEditable);
        tboxCapacity.setEditable(isEditable);
        tboxPrice.setEditable(isEditable);

    }

    private void populateEditingVenuePage(int venueId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/venue/read/" + venueId))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonObj = new JSONObject(responseBody);

                currentEditingVenue = new Venue.Builder()
                        .setVenueId(jsonObj.getInt("venueId"))
                        .setVenueName(jsonObj.getString("venueName"))
                        .setVenueDescription(jsonObj.getString("venueDescription"))
                        .setVenueAddress(jsonObj.getString("venueAddress"))
                        .setVenueCapacity(jsonObj.getInt("venueCapacity"))
                        .setVenuePrice((float) jsonObj.getDouble("venuePrice"))
                        .build();

                tboxVenueName.setText(String.valueOf(currentEditingVenue.getVenueId()));
                labelEventnameEdit.setText(currentEditingVenue.getVenueName());
                tAreaDescription.setText(currentEditingVenue.getVenueDescription());
                tboxAddress.setText(currentEditingVenue.getVenueAddress());
                tboxCapacity.setText(String.valueOf(currentEditingVenue.getVenueCapacity()));
                tboxPrice.setText(String.valueOf(currentEditingVenue.getVenuePrice()));

                int width = 150;
                int height = 150;

                if (currentEditingVenue.getVenueImage() != null && currentEditingVenue.getVenueImage().length > 0) {
                    ImageIcon image = new ImageIcon(currentEditingVenue.getVenueImage());
                    Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    labelVenueImage.setIcon(new ImageIcon(img));
                    labelVenueImage.setText("");
                } else {
                    labelVenueImage.setIcon(null);
                    labelVenueImage.setText("No Image Available");
                    labelVenueImage.setHorizontalAlignment(SwingConstants.CENTER);
                }

                setEditable(false);

            } else {
                JOptionPane.showMessageDialog(null, "❌ Failed to load venue details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ;}
    }

    private void SaveVenue() {
       try {
           String venueName = tboxVenueName.getText();
           String venueDescription = tAreaDescription.getText();
           String venueAddress = tboxAddress.getText();
           int venueCapacity = Integer.parseInt(tboxCapacity.getText());
           double venuePrice = Double.parseDouble(tboxPrice.getText());
           byte[] venueImage = selectedImageBytes;

           Venue venueToSave;

           if (currentEditingVenue != null) {
                venueToSave = new Venue.Builder()
                          .copy(currentEditingVenue)
                          .setVenueName(venueName)
                          .setVenueDescription(venueDescription)
                          .setVenueAddress(venueAddress)
                          .setVenueCapacity(venueCapacity)
                          .setVenuePrice(venuePrice)
                          .setVenueImage(venueImage != null ? venueImage : currentEditingVenue.getVenueImage())
                          .build();
              } else {
                venueToSave = new Venue.Builder()
                          .setVenueName(venueName)
                          .setVenueDescription(venueDescription)
                          .setVenueAddress(venueAddress)
                          .setVenueCapacity(venueCapacity)
                          .setVenuePrice(venuePrice)
                          .setVenueImage(venueImage)
                          .build();
           }

           String endpoint = (currentEditingVenue != null) ? "http://localhost:8080/venue/update" : "http://localhost:8080/venue/create";
           String json = new ObjectMapper().writeValueAsString(venueToSave);

              HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .header("Content-Type", "application/json")
                        .method((currentEditingVenue != null) ? "PUT" : "POST", HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 || response.statusCode() == 201) {
                    JOptionPane.showMessageDialog(null, "✅ Venue saved successfully!");
                    ClearVenueEdit();
                    cardLayout.show(panelRoot, "venue");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Save failed! Try again.", "Save Error", JOptionPane.ERROR_MESSAGE);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//end of save venue method

    private void ClearVenueEdit() {
        if (currentEditingVenue != null) {
                populateEditingVenuePage(currentEditingVenue.getVenueId());
                setEditable(false);
        } else {
                tboxVenueName.setText("");
                labelEventnameEdit.setText("");
                tAreaDescription.setText("");
                tboxAddress.setText("");
                tboxCapacity.setText("");
                tboxPrice.setText("");
                labelVenueImage.setIcon(null);
                labelVenueImage.setText("No Image Available");
                labelVenueImage.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }//end of clear venue edit method

    private void DeleteVenue() {
        try {
            if (currentEditingVenue == null) {
                JOptionPane.showMessageDialog(null, "No venue selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this venue?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/venue/delete/" + currentEditingVenue.getVenueId()))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JOptionPane.showMessageDialog(null, "✅ Venue deleted successfully!");
                ClearVenueEdit();
                cardLayout.show(panelRoot, "venue");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Deletion failed! Try again.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//end of delete venue method

    private void uploadVenueImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                selectedImageBytes = Files.readAllBytes(selectedFile.toPath());

                ImageIcon image = new ImageIcon(selectedImageBytes);
                Image img = image.getImage().getScaledInstance(
                        labelVenueImage.getWidth(),
                        labelVenueImage.getHeight(),
                        Image.SCALE_SMOOTH
                );
                labelVenueImage.setIcon(new ImageIcon(img));
                labelVenueImage.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//end of upload venue image method


    private class CateringButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                AddCateringPage cateringPage = new AddCateringPage();
                                cateringPage.setVisible(true);
                            }
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "Failed to open Add Catering Page: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            thread.start();
        }
    }

//end of edit venue page
}//end if class
