package za.ac.cput.views;

import za.ac.cput.dao.EventsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateEventPage extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblTitle, lblName, lblDescription, lblLocation,
            lblDate, lblTime, lblCategory, lblStatus;
    private JTextField txtName, txtDate, txtTime, txtCategory;
    private JTextArea txtDescription;
    private JComboBox<String> cbxLocation;
    private JComboBox<String> cbxStatus;
    private JButton btnSave, btnBack;

    public CreateEventPage(CardLayout cardLayout, JPanel mainPanel) {
        super(new BorderLayout());
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setBackground(new Color(30, 30, 30)); // dark background

        panelNorth = new JPanel();
        panelCenter = new JPanel(new GridBagLayout());
        panelSouth = new JPanel();

        panelNorth.setBackground(new Color(45, 45, 45));
        panelCenter.setBackground(new Color(40, 40, 40));
        panelSouth.setBackground(new Color(45, 45, 45));

        lblTitle = new JLabel("Create / Edit Event", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.ORANGE);

        lblName = createStyledLabel("Event Name:");
        lblDescription = createStyledLabel("Description:");
        lblLocation = createStyledLabel("Location:");
        lblDate = createStyledLabel("Date (YYYY-MM-DD):");
        lblTime = createStyledLabel("Time (HH:MM):");
        lblCategory = createStyledLabel("Category:");
        lblStatus = createStyledLabel("Status:");

        txtName = createStyledTextField();
        txtDescription = createStyledTextArea();
        txtDate = createStyledTextField();
        txtTime = createStyledTextField();
        txtCategory = createStyledTextField();

        cbxLocation = new JComboBox<>(new String[]{"Venue A", "Venue B", "Venue C"});
        cbxStatus = new JComboBox<>(new String[]{"PLANNED", "ONGOING", "COMPLETED"});

        styleComboBox(cbxLocation);
        styleComboBox(cbxStatus);

        btnSave = createStyledButton("Save Event", new Color(255, 140, 0));
        btnBack = createStyledButton("⬅ Back", new Color(80, 80, 80));

        setGUI();
    }

    private JLabel createStyledLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return lbl;
    }

    private JTextField createStyledTextField() {
        JTextField txt = new JTextField(15);
        txt.setBackground(new Color(60, 60, 60));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return txt;
    }

    private JTextArea createStyledTextArea() {
        JTextArea txt = new JTextArea(3, 15);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setBackground(new Color(60, 60, 60));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return txt;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return btn;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(60, 60, 60));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void setGUI() {
        panelNorth.add(lblTitle);
        add(panelNorth, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; panelCenter.add(lblName, gbc);
        gbc.gridx = 1; panelCenter.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblDescription, gbc);
        gbc.gridx = 1; panelCenter.add(new JScrollPane(txtDescription), gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblLocation, gbc);
        gbc.gridx = 1; panelCenter.add(cbxLocation, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblDate, gbc);
        gbc.gridx = 1; panelCenter.add(txtDate, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblTime, gbc);
        gbc.gridx = 1; panelCenter.add(txtTime, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblCategory, gbc);
        gbc.gridx = 1; panelCenter.add(txtCategory, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCenter.add(lblStatus, gbc);
        gbc.gridx = 1; panelCenter.add(cbxStatus, gbc);

        add(panelCenter, BorderLayout.CENTER);

        panelSouth.add(btnBack);
        panelSouth.add(btnSave);
        add(panelSouth, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "HomePage"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();
        String location = (String) cbxLocation.getSelectedItem();
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String category = txtCategory.getText().trim();
        String status = (String) cbxStatus.getSelectedItem();

        if (name.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all mandatory fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EventsDAO.saveEvent(name, description, location, date, time, category, status);
        JOptionPane.showMessageDialog(this, " Event saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        cardLayout.show(mainPanel, "HomePage");
    }

    public void loadEvent(String eventData) {
        String[] parts = eventData.split("\\|");
        if (parts.length >= 6) {
            txtName.setText(parts[0].split("-", 2)[1].trim());
            txtDescription.setText(parts[1].trim());
            cbxLocation.setSelectedItem(parts[2].trim());
            String[] dateTime = parts[3].trim().split(" ");
            txtDate.setText(dateTime[0]);
            txtTime.setText(dateTime.length > 1 ? dateTime[1] : "");
            txtCategory.setText(parts[4].trim());
            cbxStatus.setSelectedItem(parts[5].trim());
        }
    }
}
