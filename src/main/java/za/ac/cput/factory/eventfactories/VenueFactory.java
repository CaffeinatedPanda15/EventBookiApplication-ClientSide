package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Venue;

public class VenueFactory {

    public static Venue createVenue(int venueId, String venueName, String venueLocation, int venueCapacity, byte[] venueImage) {
        // Basic validation
        if (venueId <= 0) {
            throw new IllegalArgumentException("Venue ID must be positive.");
        }
        if (venueName == null || venueName.isEmpty()) {
            throw new IllegalArgumentException("Venue name cannot be null or empty.");
        }
        if (venueLocation == null || venueLocation.isEmpty()) {
            throw new IllegalArgumentException("Venue location cannot be null or empty.");
        }
        if (venueCapacity <= 0) {
            throw new IllegalArgumentException("Venue capacity must be positive.");
        }
        if (venueImage == null || venueImage.length == 0) {
            throw new IllegalArgumentException("Venue image cannot be null or empty.");
        }

        return new Venue.Builder()
                .setVenueId(venueId)
                .setVenueName(venueName)
                .setVenueDescription(venueLocation)
                .setVenueCapacity(venueCapacity)
                .setVenueImage(venueImage)
                .build();

    }

}//end of class
