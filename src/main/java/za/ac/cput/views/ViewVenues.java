package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewVenues extends JFrame {

    private JPanel gridPanel;

    public ViewVenues() {
        setTitle("All Venues");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        loadVenues();
        setVisible(true);
    }

    private void loadVenues() {
        try {
            URL url = new URL("http://localhost:8080/api/venue/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream in = conn.getInputStream();
            String response = new String(in.readAllBytes());
            JSONArray venues = new JSONArray(response);

            for (int i = 0; i < venues.length(); i++) {
                JSONObject v = venues.getJSONObject(i);

                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                card.setBackground(Color.WHITE);

                // Load image
                JLabel imgLabel = new JLabel();
                imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

                try {
                    URL imgUrl = new URL("http://localhost:8080/api/venue/" + v.getInt("venueId") + "/image");
                    ImageIcon icon = new ImageIcon(imgUrl);
                    Image scaled = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
                    imgLabel.setIcon(new ImageIcon(scaled));
                } catch (Exception e) {
                    imgLabel.setText("No Image");
                    imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                }

                JLabel nameLabel = new JLabel(v.getString("venueName"), SwingConstants.CENTER);
                nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

                card.add(imgLabel, BorderLayout.CENTER);
                card.add(nameLabel, BorderLayout.SOUTH);

                gridPanel.add(card);
            }

            gridPanel.revalidate();
            gridPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load venues: " + e.getMessage());
        }
    }
}
