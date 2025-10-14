package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import za.ac.cput.domain.eventdomains.Venue;

public class VenueBox {
    private JPanel panelBoxRoot;
    private JPanel panelVenueBoxWest;
    private JPanel panelVenueBoxNorth;
    private JPanel panelVenueBoxEast;
    private JPanel panelVenueBoxSouth;
    private JPanel panelVenueBoxCenter;
    private JTextArea tAreaVenueDescription;
    private JLabel labelVenueBoxVenueName;
    private JLabel labelVenueBoxVenueCapacity;
    private JLabel labelVenueBoxVenuePrice;
    private JLabel EmptyLabel1;
    private JLabel EmptyLabel2;
    private JButton editButton;
    private JLabel labelVenueImage;

    public VenueBox(Venue venue, java.awt.event.ActionListener editListener) {
        labelVenueBoxVenueName.setText(venue.getVenueName());
        labelVenueBoxVenueCapacity.setText(String.valueOf(venue.getVenueCapacity()));
        labelVenueBoxVenuePrice.setText(String.valueOf(venue.getVenuePrice()));
        tAreaVenueDescription.setText(venue.getVenueDescription());

        if (venue.getVenueImage() != null) {
            ImageIcon imageIcon = new ImageIcon(venue.getVenueImage());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            labelVenueImage.setIcon(new ImageIcon(image));
        } else {
            labelVenueImage.setText("No Image Available");
        }

        editButton.addActionListener(editListener);
    }



    public JPanel getPanelBoxRoot() {
        return panelBoxRoot;
    }

}//end of class
