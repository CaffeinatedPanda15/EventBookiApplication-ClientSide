package za.ac.cput.views;

import za.ac.cput.domain.eventdomains.Catering;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class AddCateringPage extends JFrame {

    // Entity-based input fields
    private JTextField cateringNameField;
    private JTextField cateringTypeField;
    private JTextArea cateringDescriptionArea;
    private JTextField cateringPriceField;
    private JTextField cateringContactField;
    private JLabel cateringImageLabel;
    private JButton uploadImageButton;
    private JButton saveButton;
    private JButton cancelButton;

    private byte[] cateringImageBytes;

    private JPanel cards; // CardLayout container

    public AddCateringPage() {
        setTitle("Add New Catering");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use CardLayout for simple student-style navigation (even though it's one form)
        cards = new JPanel(new CardLayout());

        // Create the main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); // absolute positioning (student-style, simple)
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Catering Name
        JLabel nameLabel = new JLabel("Catering Name:");
        nameLabel.setBounds(20, 20, 120, 25);
        formPanel.add(nameLabel);

        cateringNameField = new JTextField();
        cateringNameField.setBounds(150, 20, 300, 25);
        formPanel.add(cateringNameField);

        // Catering Type
        JLabel typeLabel = new JLabel("Catering Type:");
        typeLabel.setBounds(20, 60, 120, 25);
        formPanel.add(typeLabel);

        cateringTypeField = new JTextField();
        cateringTypeField.setBounds(150, 60, 300, 25);
        formPanel.add(cateringTypeField);

        // Catering Description
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 100, 120, 25);
        formPanel.add(descLabel);

        cateringDescriptionArea = new JTextArea();
        cateringDescriptionArea.setLineWrap(true);
        cateringDescriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(cateringDescriptionArea);
        descScroll.setBounds(150, 100, 300, 80);
        formPanel.add(descScroll);

        // Catering Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 200, 120, 25);
        formPanel.add(priceLabel);

        cateringPriceField = new JTextField();
        cateringPriceField.setBounds(150, 200, 300, 25);
        formPanel.add(cateringPriceField);

        // Catering Contact
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 240, 120, 25);
        formPanel.add(contactLabel);

        cateringContactField = new JTextField();
        cateringContactField.setBounds(150, 240, 300, 25);
        formPanel.add(cateringContactField);

        // Catering Image
        JLabel imageLabel = new JLabel("Image:");
        imageLabel.setBounds(20, 280, 120, 25);
        formPanel.add(imageLabel);

        cateringImageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        cateringImageLabel.setBounds(150, 280, 200, 150);
        cateringImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        formPanel.add(cateringImageLabel);

        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(360, 350, 120, 25);
        formPanel.add(uploadImageButton);

        // Buttons
        saveButton = new JButton("Save");
        saveButton.setBounds(150, 450, 100, 30);
        formPanel.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(270, 450, 100, 30);
        formPanel.add(cancelButton);

        cards.add(formPanel, "FORM");
        add(cards);

        setVisible(true);

        // --- Action Listeners ---
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showOpenDialog(AddCateringPage.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        cateringImageBytes = Files.readAllBytes(file.toPath());
                        cateringImageLabel.setText(file.getName());
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to read image file.");
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCatering();
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }

    private void saveCatering() {
        try {
            String name = cateringNameField.getText().trim();
            String type = cateringTypeField.getText().trim();
            String desc = cateringDescriptionArea.getText().trim();
            double price = Double.parseDouble(cateringPriceField.getText().trim());
            String contact = cateringContactField.getText().trim();

            Catering newCatering = new Catering.Builder()
                    .setCateringName(name)
                    .setCateringType(type)
                    .setCateringDescription(desc)
                    .setCateringPrice(price)
                    .setCateringContact(contact)
                    .setCateringImage(cateringImageBytes)
                    .build();

            // POST request to API
            URL url = new URL("http://localhost:8080/api/catering");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String json = "{"
                    + "\"cateringName\":\"" + newCatering.getCateringName() + "\","
                    + "\"cateringType\":\"" + newCatering.getCateringType() + "\","
                    + "\"cateringDescription\":\"" + newCatering.getCateringDescription() + "\","
                    + "\"cateringPrice\":" + newCatering.getCateringPrice() + ","
                    + "\"cateringContact\":\"" + newCatering.getCateringContact() + "\","
                    + "\"cateringImage\":\"" + (cateringImageBytes != null ? java.util.Base64.getEncoder().encodeToString(cateringImageBytes) : "") + "\""
                    + "}";

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                JOptionPane.showMessageDialog(this, "Catering saved successfully!");
                dispose();
            } else {
                InputStream errorStream = conn.getErrorStream();
                String error = new String(errorStream.readAllBytes());
                JOptionPane.showMessageDialog(this, "Error saving catering: " + error);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}


        SwingUtilities.invokeLater(() -> new AddCateringPage());
    }

}

