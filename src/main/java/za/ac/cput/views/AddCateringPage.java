package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import org.json.*;

public class AddCateringPage extends JFrame {

    // UI Components
    private JComboBox<Integer> cateringIdComboBox;
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
        setSize(600, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Label + ComboBox for Catering IDs
        JLabel comboLabel = new JLabel("Select Catering ID:");
        comboLabel.setBounds(20, 20, 150, 25);
        add(comboLabel);

        cateringIdComboBox = new JComboBox<>();
        cateringIdComboBox.setBounds(170, 20, 150, 25);
        cateringIdComboBox.setEnabled(false);
        add(cateringIdComboBox);

        editButton = new JButton("Edit");
        editButton.setBounds(340, 20, 100, 25);
        add(editButton);

        // Name
        JLabel nameLabel = new JLabel("Catering Name:");
        nameLabel.setBounds(20, 60, 150, 25);
        add(nameLabel);

        cateringNameField = new JTextField();
        cateringNameField.setBounds(170, 60, 350, 25);
        add(cateringNameField);

        // Type
        JLabel typeLabel = new JLabel("Catering Type:");
        typeLabel.setBounds(20, 100, 150, 25);
        add(typeLabel);

        cateringTypeField = new JTextField();
        cateringTypeField.setBounds(170, 100, 350, 25);
        add(cateringTypeField);

        // Description
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 140, 150, 25);
        add(descLabel);

        cateringDescriptionArea = new JTextArea();
        JScrollPane descScroll = new JScrollPane(cateringDescriptionArea);
        descScroll.setBounds(170, 140, 350, 80);
        add(descScroll);

        // Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 240, 150, 25);
        add(priceLabel);

        cateringPriceField = new JTextField();
        cateringPriceField.setBounds(170, 240, 350, 25);
        add(cateringPriceField);

        // Contact
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 280, 150, 25);
        add(contactLabel);

        cateringContactField = new JTextField();
        cateringContactField.setBounds(170, 280, 350, 25);
        add(cateringContactField);

        // Image
        JLabel imageLabel = new JLabel("Image:");
        imageLabel.setBounds(20, 320, 150, 25);
        add(imageLabel);

        cateringImageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        cateringImageLabel.setBounds(170, 320, 200, 150);
        cateringImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(cateringImageLabel);

        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(380, 370, 140, 25);
        add(uploadImageButton);

        // Buttons
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
        cancelButton.setBounds(480, 500, 100, 30);
        add(cancelButton);

        // Action Listeners
        uploadImageButton.addActionListener(new UploadImageListener());
        saveButton.addActionListener(new SaveListener());
        editButton.addActionListener(new EditListener());
        submitEditButton.addActionListener(new SubmitEditListener());
        deleteButton.addActionListener(new DeleteListener());
        cancelButton.addActionListener(new CancelListener());
        cateringIdComboBox.addActionListener(new ComboSelectionListener());

        setVisible(true);
    }

    // ----------- LISTENERS ------------ //

    private class UploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(AddCateringPage.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    cateringImageBytes = Files.readAllBytes(file.toPath());
                    ImageIcon icon = new ImageIcon(
                            new ImageIcon(cateringImageBytes).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)
                    );
                    cateringImageLabel.setIcon(icon);
                    cateringImageLabel.setText("");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to read image file.");
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String json = "{"
                        + "\"cateringName\":\"" + cateringNameField.getText().trim() + "\","
                        + "\"cateringType\":\"" + cateringTypeField.getText().trim() + "\","
                        + "\"cateringDescription\":\"" + cateringDescriptionArea.getText().trim() + "\","
                        + "\"cateringPrice\":" + cateringPriceField.getText().trim() + ","
                        + "\"cateringContact\":\"" + cateringContactField.getText().trim() + "\""
                        + "}";

                URL url = new URL("http://localhost:8080/api/catering");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.getOutputStream().write(json.getBytes());
                conn.getOutputStream().flush();

                if (conn.getResponseCode() == 201) {
                    String response = new String(conn.getInputStream().readAllBytes());
                    JSONObject obj = new JSONObject(response);
                    int id = obj.getInt("cateringId");

                    if (cateringImageBytes != null) {
                        uploadImage(id, cateringImageBytes);
                    }

                    JOptionPane.showMessageDialog(AddCateringPage.this, "Catering saved successfully!");
                    conn.disconnect();
                } else {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error saving catering.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cateringIdComboBox.setEnabled(true);
                cateringIdComboBox.removeAllItems();

                URL url = new URL("http://localhost:8080/api/catering/all");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() == 200) {
                    String response = new String(conn.getInputStream().readAllBytes());
                    JSONArray array = new JSONArray(response);

                    if (array.isEmpty()) {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "No catering IDs found.");
                        return;
                    }

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        cateringIdComboBox.addItem(obj.getInt("cateringId"));
                    }

                    saveButton.setEnabled(false);
                    submitEditButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to fetch catering IDs.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class ComboSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer id = (Integer) cateringIdComboBox.getSelectedItem();
            if (id == null) return;
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

                } else {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to fetch catering details.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class SubmitEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer id = (Integer) cateringIdComboBox.getSelectedItem();
            if (id == null) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "No catering selected.");
                return;
            }

            try {
                String json = "{"
                        + "\"cateringName\":\"" + cateringNameField.getText().trim() + "\","
                        + "\"cateringType\":\"" + cateringTypeField.getText().trim() + "\","
                        + "\"cateringDescription\":\"" + cateringDescriptionArea.getText().trim() + "\","
                        + "\"cateringPrice\":" + cateringPriceField.getText().trim() + ","
                        + "\"cateringContact\":\"" + cateringContactField.getText().trim() + "\""
                        + "}";

                URL url = new URL("http://localhost:8080/api/catering/" + id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.getOutputStream().write(json.getBytes());
                conn.getOutputStream().flush();

                if (conn.getResponseCode() == 200) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Catering updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to update catering.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
            }
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer id = (Integer) cateringIdComboBox.getSelectedItem();
            if (id == null) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "Select a catering ID to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(AddCateringPage.this,
                    "Are you sure you want to delete catering ID " + id + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    URL url = new URL("http://localhost:8080/api/catering/" + id);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("DELETE");

                    if (conn.getResponseCode() == 204) {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "Catering deleted successfully!");
                        cateringIdComboBox.removeItem(id);
                    } else {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to delete catering.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    // ---------- Helper for Image Upload ----------
    private void uploadImage(int id, byte[] imageBytes) throws IOException {
        String boundary = "---boundary";
        URL uploadUrl = new URL("http://localhost:8080/api/catering/" + id + "/uploadImage");
        HttpURLConnection conn = (HttpURLConnection) uploadUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (DataOutputStream request = new DataOutputStream(conn.getOutputStream())) {
            request.writeBytes("--" + boundary + "\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\"image.jpg\"\r\n");
            request.writeBytes("Content-Type: image/jpeg\r\n\r\n");
            request.write(imageBytes);
            request.writeBytes("\r\n--" + boundary + "--\r\n");
        }

        if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
            JOptionPane.showMessageDialog(this, "Image upload failed: " + conn.getResponseCode());
        }
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        new AddCateringPage();
    }
}
