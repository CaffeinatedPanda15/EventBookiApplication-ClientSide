package za.ac.cput.views;

import za.ac.cput.DAO.venuesDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Venues page that will be used on the homepage at the centre to display the venues
public class VenuesPage extends JPanel {
    private JList<String> venueList;

    public VenuesPage() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Available Venues", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        List<String> venues = venuesDAO.getAllVenues();

        venueList = new JList<>(venues.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(venueList);
        add(scrollPane, BorderLayout.CENTER);
    }
}

