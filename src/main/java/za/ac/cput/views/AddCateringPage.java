package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddCateringPage extends JFrame {

    private JComboBox<Long> cateringIdComboBox;
    private JComboBox<String> cateringTypeComboBox;
    private JButton editButton, submitEditButton, deleteButton, saveButton;
    private JTextField cateringNameField, cateringPriceField, cateringContactField;
    private JTextArea cateringDescriptionArea;
    private JLabel cateringImageLabel, phoneErrorLabel;
    private JButton uploadImageButton;

    private byte[] cateringImageBytes;

    public AddCateringPage() {
        setTitle("Add/Edit Catering");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        initComponents();
        loadCateringIds();
        clearForm();
        setVisible(true);
    }

    private void initComponents() {

        JLabel idLabel = new JLabel("Select Catering ID:");
        idLabel.setBounds(20, 20, 120, 25);
        add(idLabel);

        cateringIdComboBox = new JComboBox<>();
        cateringIdComboBox.setBounds(150, 20, 150, 25);
        cateringIdComboBox.setEnabled(false);
        add(cateringIdComboBox);
        cateringIdComboBox.addActionListener(new CateringIdSelectionListener());

        editButton = new JButton("Edit");
        editButton.setBounds(320, 20, 80, 25);
        add(editButton);
        editButton.addActionListener(new EditListener());

        submitEditButton = new JButton("Submit Edit");
        submitEditButton.setBounds(410, 20, 120, 25);
        add(submitEditButton);
        submitEditButton.setEnabled(false);
        submitEditButton.addActionListener(new SubmitEditListener());

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(540, 20, 80, 25);
        add(deleteButton);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new DeleteListener());

        // --- Catering Name ---
        JLabel nameLabel = new JLabel("Catering Name:");
        nameLabel.setBounds(20, 70, 120, 25);
        add(nameLabel);
        cateringNameField = new JTextField();
        cateringNameField.setBounds(150, 70, 300, 25);
        cateringNameField.setText(" ");
        add(cateringNameField);

        // --- Catering Type ---
        JLabel typeLabel = new JLabel("Catering Type:");
        typeLabel.setBounds(20, 110, 120, 25);
        add(typeLabel);

        cateringTypeComboBox = new JComboBox<>();
        cateringTypeComboBox.setBounds(150, 110, 300, 25);
        add(cateringTypeComboBox);
        populateCateringTypes();

        // --- Description ---
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 150, 120, 25);
        add(descLabel);
        cateringDescriptionArea = new JTextArea();
        cateringDescriptionArea.setLineWrap(true);
        cateringDescriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(cateringDescriptionArea);
        descScroll.setBounds(150, 150, 300, 80);
        add(descScroll);

        // --- Price ---
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 240, 120, 25);
        add(priceLabel);
        cateringPriceField = new JTextField();
        cateringPriceField.setBounds(150, 240, 300, 25);
        cateringPriceField.setText(" ");
        add(cateringPriceField);

        // --- Contact with validation ---
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(20, 280, 120, 25);
        add(contactLabel);
        cateringContactField = new JTextField();
        cateringContactField.setBounds(150, 280, 300, 25);
        cateringContactField.setText(" ");
        add(cateringContactField);

        phoneErrorLabel = new JLabel("");
        phoneErrorLabel.setBounds(150, 310, 300, 20);
        phoneErrorLabel.setForeground(Color.RED);
        add(phoneErrorLabel);

        cateringContactField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validatePhone();
            }
        });

        JLabel imageLabel = new JLabel("Image:");
        imageLabel.setBounds(20, 340, 120, 25);
        add(imageLabel);

        cateringImageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        cateringImageLabel.setBounds(150, 340, 200, 150);
        cateringImageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(cateringImageLabel);

        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBounds(360, 420, 120, 25);
        add(uploadImageButton);
        uploadImageButton.addActionListener(new UploadImageListener());

        // --- Save button ---
        saveButton = new JButton("Save New");
        saveButton.setBounds(150, 500, 120, 30);
        add(saveButton);
        saveButton.addActionListener(new SaveListener());


    }

    private void clearForm() {
        cateringNameField.setText("");
        cateringTypeComboBox.setSelectedIndex(0);
        cateringDescriptionArea.setText("");
        cateringPriceField.setText("");
        cateringContactField.setText("");
        cateringImageLabel.setIcon(null);
        cateringImageLabel.setText("No Image Selected");
        cateringImageBytes = null;
    }


    private void populateCateringTypes() {
        cateringTypeComboBox.addItem("None");
        cateringTypeComboBox.addItem("Buffet");
        cateringTypeComboBox.addItem("Cocktail");
        cateringTypeComboBox.addItem("Wedding");
        cateringTypeComboBox.addItem("Corporate");
        cateringTypeComboBox.addItem("Birthday");
        cateringTypeComboBox.addItem("BBQ");
        cateringTypeComboBox.addItem("Vegan");
        cateringTypeComboBox.addItem("Seafood");
        cateringTypeComboBox.addItem("Dessert");
    }

    private void validatePhone() {
        String input = cateringContactField.getText().trim();
        boolean valid = input.matches("0\\d{9}");

        if (!valid) {
            phoneErrorLabel.setText("Invalid phone number! Must start with 0 and be 10 digits.");
            saveButton.setEnabled(false);
            submitEditButton.setEnabled(false);
        } else {
            phoneErrorLabel.setText("");
            saveButton.setEnabled(true);
            submitEditButton.setEnabled(true);
        }
    }

    private void loadCateringIds() {
        cateringIdComboBox.addItem(0L);
        try {
            URL url = new URL("http://localhost:8080/api/catering/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes());
            JSONArray caterings = new JSONArray(response);

            if (caterings.length() == 1) {
                JOptionPane.showMessageDialog(this, "No catering IDs found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 1; i < caterings.length(); i++) {
                JSONObject c = caterings.getJSONObject(i);
                cateringIdComboBox.addItem(c.getLong("cateringId"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load catering IDs: " + e.getMessage());
            clearForm();
        }
    }

    // --- Populate fields when an ID is selected ---
    private class CateringIdSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long selectedId = (Long) cateringIdComboBox.getSelectedItem();
            if (selectedId == null) return;

            new Thread(() -> {
                try {
                    // --- Fetch JSON data ---
                    URL url = new URL("http://localhost:8080/api/catering/" + selectedId);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();

                    if (conn.getResponseCode() == 200) {
                        InputStream in = conn.getInputStream();
                        String response = new String(in.readAllBytes());
                        JSONObject c = new JSONObject(response);

                        String name = c.getString("cateringName");
                        String type = c.getString("cateringType");
                        String desc = c.getString("cateringDescription");
                        double price = c.getDouble("cateringPrice");
                        String contact = c.getString("cateringContact");

                        // Update text fields on Swing thread
                        SwingUtilities.invokeLater(() -> {
                            cateringNameField.setText(name);
                            cateringTypeComboBox.setSelectedItem(type);
                            cateringDescriptionArea.setText(desc);
                            cateringPriceField.setText(String.valueOf(price));
                            cateringContactField.setText(contact);
                        });

                        // --- Fetch image bytes separately ---
                        try {
                            URL imgUrl = new URL("http://localhost:8080/api/catering/" + selectedId + "/image");
                            HttpURLConnection imgConn = (HttpURLConnection) imgUrl.openConnection();
                            imgConn.setRequestMethod("GET");
                            imgConn.connect();

                            if (imgConn.getResponseCode() == 200) {
                                byte[] imageBytes = imgConn.getInputStream().readAllBytes();
                                ImageIcon icon = new ImageIcon(imageBytes);
                                Image scaled = icon.getImage().getScaledInstance(
                                        cateringImageLabel.getWidth(),
                                        cateringImageLabel.getHeight(),
                                        Image.SCALE_SMOOTH
                                );

                                SwingUtilities.invokeLater(() -> {
                                    cateringImageLabel.setText("");
                                    cateringImageLabel.setIcon(new ImageIcon(scaled));
                                });
                            } else {
                                SwingUtilities.invokeLater(() -> {
                                    cateringImageLabel.setText("No Image Available");
                                    cateringImageLabel.setIcon(null);
                                });
                            }

                        } catch (Exception imgEx) {
                            SwingUtilities.invokeLater(() -> {
                                cateringImageLabel.setText("No Image");
                                cateringImageLabel.setIcon(null);
                            });
                        }

                    } else {
                        SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(AddCateringPage.this,
                                        "Failed to fetch catering data!")
                        );
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(AddCateringPage.this,
                                    "Error: " + ex.getMessage())
                    );
                }
            }).start();
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
                    cateringImageBytes = Files.readAllBytes(file.toPath());

                    ImageIcon icon = new ImageIcon(cateringImageBytes);
                    Image img = icon.getImage().getScaledInstance(
                            cateringImageLabel.getWidth(),
                            cateringImageLabel.getHeight(),
                            Image.SCALE_SMOOTH
                    );
                    cateringImageLabel.setIcon(new ImageIcon(img));
                    cateringImageLabel.setText(null);

                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Failed to read image file.");
                }
            }
        }
    }

    private class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cateringIdComboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(AddCateringPage.this, "No catering IDs available!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cateringIdComboBox.setEnabled(true);
            saveButton.setEnabled(false);
            submitEditButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
    }

    private class SubmitEditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long selectedId = (Long) cateringIdComboBox.getSelectedItem();
            if (selectedId == null) return;

            try {
                JSONObject dto = new JSONObject();
                dto.put("cateringName", cateringNameField.getText());
                dto.put("cateringType", (String) cateringTypeComboBox.getSelectedItem());
                dto.put("cateringDescription", cateringDescriptionArea.getText());
                dto.put("cateringPrice", Double.parseDouble(cateringPriceField.getText()));
                dto.put("cateringContact", cateringContactField.getText());
                dto.put("cateringImage", cateringImageBytes != null ? Base64.getEncoder().encodeToString(cateringImageBytes) : null);

                URL url = new URL("http://localhost:8080/api/catering/" + selectedId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");

                OutputStream os = conn.getOutputStream();
                os.write(dto.toString().getBytes());
                os.flush();
                os.close();

                if (conn.getResponseCode() == 200) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Catering updated successfully!");
                } else {
                    InputStream err = conn.getErrorStream();
                    String error = new String(err.readAllBytes());
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error updating: " + error);
                    clearForm();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
            }
            editButton.setEnabled(true);
            saveButton.setEnabled(true);
            deleteButton.setEnabled(false);
            cateringIdComboBox.setEnabled(false);
            loadCateringIds();
            clearForm();
        }

    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long selectedId = (Long) cateringIdComboBox.getSelectedItem();
            if (selectedId == null) return;

            int confirm = JOptionPane.showConfirmDialog(AddCateringPage.this,
                    "Are you sure you want to delete this catering?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    URL url = new URL("http://localhost:8080/api/catering/" + selectedId);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("DELETE");
                    conn.connect();

                    if (conn.getResponseCode() == 204) {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "Deleted successfully!");
                        cateringIdComboBox.removeItem(selectedId);
                    } else {
                        JOptionPane.showMessageDialog(AddCateringPage.this, "Error deleting catering.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JSONObject dto = new JSONObject();
                dto.put("cateringName", cateringNameField.getText());
                dto.put("cateringType", (String) cateringTypeComboBox.getSelectedItem());
                dto.put("cateringDescription", cateringDescriptionArea.getText());
                dto.put("cateringPrice", Double.parseDouble(cateringPriceField.getText()));
                dto.put("cateringContact", cateringContactField.getText());
                dto.put("cateringImage", cateringImageBytes != null ? Base64.getEncoder().encodeToString(cateringImageBytes) : null);

                URL url = new URL("http://localhost:8080/api/catering");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                OutputStream os = conn.getOutputStream();
                os.write(dto.toString().getBytes());
                os.flush();
                os.close();

                if (conn.getResponseCode() == 201) {
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Catering added successfully!");
                    loadCateringIds();
                } else {
                    InputStream err = conn.getErrorStream();
                    String error = new String(err.readAllBytes());
                    JOptionPane.showMessageDialog(AddCateringPage.this, "Error saving: " + error);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(AddCateringPage.this, "Error: " + ex.getMessage());
            }

            loadCateringIds();
            clearForm();
        }
    }
}
