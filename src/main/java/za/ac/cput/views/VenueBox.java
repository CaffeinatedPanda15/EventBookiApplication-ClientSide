package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
       labelVenueBoxVenueCapacity.setText("Capacity: " + venue.getVenueCapacity());
       labelVenueBoxVenuePrice.setText("Price: R" + venue.getVenuePrice());
       tAreaVenueDescription.setText(venue.getVenueDescription());

       if (venue.getVenueImage() != null) {
           ImageIcon imageIcon = new ImageIcon(venue.getVenueImage());
           Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
           labelVenueImage.setIcon(new ImageIcon(image));
       } else {
           labelVenueImage.setText("No Image");
           labelVenueImage.setHorizontalAlignment(SwingConstants.CENTER);
       }

         editButton.addActionListener(editListener);

       panelBoxRoot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       panelBoxRoot.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               editListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
           }
       });
    }



    public JPanel getPanelBoxRoot() {
        return panelBoxRoot;
    }

}//end of class
