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
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddCateringPage extends JFrame {

    private JComboBox<String> cateringIdComboBox;
    private JTextField cateringNameField;
    private JTextField cateringTypeField;
    private JTextArea cateringDescriptionArea;
    private JTextField cateringPriceField;
    private JTextField cateringContactField;
    private JLabel cateringImageLabel;

    private JButton uploadImageButton;
    private JButton saveButton;
    private JButton editButton;
    private JButton submitEditButton;
    private JButton deleteButton;
    private JButton cancelButton;

    private byte[] cateringImageBytes;

    public AddCateringPage() {
        setTitle("Catering Management");
        setSize(550, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel comboLabel = new JLabel("Select Catering ID:");
        comboLabel.setBounds(20, 20, 150, 25);
        add(comboLabel);

        cateringIdComboBox = new JComboBox<>();
        cateringIdComboBox.setBounds(180, 20, 250, 25);
        cateringIdComboBox.setEditable(false);
        add(cateringIdComboBox);

        editButton = new JButton("Edit");
        editButton.setBounds(440, 20, 80, 25);
        add(editButton);

        JLabel nameLabel = new JLabel("Catering Name:");
        nameLabel.setBounds(20, 60, 120, 25);
        add(nameLabel);

        cateringNameField = new JTextField();
        cateringNameField.setBounds(150, 60, 300, 25);
        add(cateringNameField);

        JLabel typeLabel = new JLabel("Catering Type:");
        typeLabel.setBounds(20, 100, 120, 25);
        add(typeLabel);

        cateringTypeField = new JTextField();
        cateringTypeField.setBounds(150, 100, 300, 25);
        add(cateringTypeField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 140, 120, 25);
        add(descLabel);

        cateringDescriptionArea = new JTextArea();
        cateringDescriptionArea.setLineWrap(true);
        cateringDescriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(cateringDescriptionArea);
        descScroll.setBounds(150, 140, 300, 80);
        add(descScroll);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 240, 120, 25);
        add(priceLabel);

        cateringPriceField = new JTextField();
        cateringPriceField.setBounds(150, 240, 300, 25);
        add(cateringPriceField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 280, 120, 25);
        add(contactLabel);

        cateringContactField = new JTextField();
        cateringContactField.setBounds(150, 280, 300, 25);
        add(cateringContactField);

        JLabel imageLabel = new JLabel("Image:");
        imageLabel.setBounds(20, 320, 120, 25);
        add(imageLabel);

        cateringImageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        cateringImageLabel.setBounds(150, 320, 200, 150);
        cateringImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(cateringImageLabel);

        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(360, 400, 140, 25);
        add(uploadImageButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 500, 100, 30);
        add(saveButton);

        submitEditButton = new JButton("Submit Edit");
        submitEditButton.setBounds(220, 500, 120, 30);
        submitEditButton.setEnabled(false);
        add(submitEditButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(360, 500, 100, 30);
        deleteButton.setEnabled(false);
        add(deleteButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 550, 100, 30);
        add(cancelButton);

        loadCateringIds();

        uploadImageButton.addActionListener(new UploadImageListener());
        saveButton.addActionListener(new SaveCateringListener());
        editButton.addActionListener(new EditButtonListener());
        submitEditButton.addActionListener(new SubmitEditListener());
        deleteButton.addActionListener(new DeleteListener());
        cateringIdComboBox.addActionListener(new ComboBoxSelectListener());
        cancelButton.addActionListener(new CancelListener());

        setVisible(true);
    }

    private void loadCateringIds() {
        try {
            URL url = new URL("http://localhost:8080/api/catering/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                InputStream input = conn.getInputStream();
                String response = new String(input.readAllBytes());
                JSONArray array = new JSONArray(response);

                cateringIdComboBox.removeAllItems();

                if (array.length() == 0) {
                    JOptionPane.showMessageDialog(this, "No catering IDs found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    cateringIdComboBox.addItem(String.valueOf(obj.getLong("cateringId")));
                }

            } else {
                JOptionPane.showMessageDialog(this, "Failed to load catering IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading catering IDs: " + ex.getMessage());
        }
    }

    // Fetch record details when a catering ID is selected
    private void populateCateringDetails(String id) {
        try {
            URL url = new URL("http://localhost:8080/api/catering/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                String response = new String(conn.getInputStream().readAllBytes());
                JSONObject obj = new JSONObject(response);

                cateringNameField.setText(obj.getString("cateringName"));
                cateringTypeField.setText(obj.getString("cateringType"));
                cateringDescriptionArea.setText(obj.getString("cateringDescription"));
                cateringPriceField.setText(String.valueOf(obj.getDouble("cateringPrice")));
                cateringContactField.setText(obj.getString("cateringContact"));

                String img = obj.optString("cateringImage", "");
                if (!img.isEmpty()) {
                    cateringImageBytes = Base64.getDecoder().decode(img);
                    cateringImageLabel.setText("Image Loaded");
                } else {
                    cateringImageLabel.setText("No Image");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error populating catering details: " + e.getMessage());
        }
    }

    private class UploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(AddCateringPage.this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    // Read image file into byte array
                    cateringImageBytes = Files.readAllBytes(file.toPath());

                    // Load image preview into label
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    Image image = icon.getImage().getScaledInstance(
                            cateringImageLabel.getWidth(),
                            cateringImageLabel.getHeight(),
                            Image.SCALE_SMOOTH
                    );

                    // Set the scaled image as label icon
                    cateringImageLabel.setIcon(new ImageIcon(image));
                    cateringImageLabel.setText(""); // Remove placeholder text
                    cateringImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    cateringImageLabel.setVerticalAlignment(SwingConstants.CENTER);

                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(
                            AddCateringPage.this,
                            "Failed to read image file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }


    private class SaveCateringListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String json = buildJson();
                sendRequest("http://localhost:8080/api/catering", "POST", json);
                JOptionPane.showMessageDialog(AddCateringPage.this, "Catering saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error saving catering: " + ex.getMessage());
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cateringIdComboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "No catering IDs available.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cateringIdComboBox.setEditable(true);
            saveButton.setEnabled(false);
            submitEditButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
    }

    private class SubmitEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String selectedId = (String) cateringIdComboBox.getSelectedItem();
                if (selectedId == null || selectedId.isEmpty()) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Please select a catering ID.");
                    return;
                }

                String json = buildJson();
                sendRequest("http://localhost:8080/api/catering/" + selectedId, "PUT", json);
                JOptionPane.showMessageDialog(AddCateringPage.this, "Catering updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error updating catering: " + ex.getMessage());
            }
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = (String) cateringIdComboBox.getSelectedItem();
            if (selectedId == null || selectedId.isEmpty()) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Please select a catering ID.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(AddCateringPage.this,
                    "Are you sure you want to delete this catering record?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    sendRequest("http://localhost:8080/api/catering/" + selectedId, "DELETE", null);
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Catering deleted successfully!");
                    loadCateringIds();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error deleting catering: " + ex.getMessage());
                }
            }
        }
    }

    private class ComboBoxSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = (String) cateringIdComboBox.getSelectedItem();
            if (selectedId != null && !selectedId.isEmpty()) {
                populateCateringDetails(selectedId);
            }
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    // --- Helper Methods ---
    private String buildJson() {
        return "{"
                + "\"cateringName\":\"" + cateringNameField.getText() + "\","
                + "\"cateringType\":\"" + cateringTypeField.getText() + "\","
                + "\"cateringDescription\":\"" + cateringDescriptionArea.getText() + "\","
                + "\"cateringPrice\":" + cateringPriceField.getText() + ","
                + "\"cateringContact\":\"" + cateringContactField.getText() + "\","
                + "\"cateringImage\":\"" + (cateringImageBytes != null ? Base64.getEncoder().encodeToString(cateringImageBytes) : "") + "\""
                + "}";
    }

    private void sendRequest(String urlString, String method, String json) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        if (json != null) {
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200 && responseCode != 201) {
            String error = new String(conn.getErrorStream().readAllBytes());
            throw new IOException("Backend Error: " + error);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddCateringPage();
            }
        });
    }
}
