package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Venue;

public class VenueFactory {
    private Venue createVenue(int venueId, String venueName, String venueAddress, String venueLocation, int capacity) {
        if ( venueName == null || venueAddress == null || venueLocation == null) {
            return null;
        }
        int venueCapacity = 0;
        return new Venue.Builder()
                .setVenueId(venueId)
                .setVenueName(venueName)
                .setVenueAddress(venueAddress)
                .setVenueCapacity(venueCapacity)
                .build();
    }
}
