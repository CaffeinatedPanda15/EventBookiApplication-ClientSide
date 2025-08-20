package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Event;

import java.sql.Time;

public class EventFactory {
    public static Event createEvent(int eventId, String eventName, String eventDescription, String eventDate, String eventTime, int venueId, int organizerId) {
        if (eventName == null || eventDescription == null || eventDate == null || eventTime == null) {
            return null;
        }
        return new Event.Builder()
                .setEventId(eventId)
                .setEventName(eventName)
                .setEventDescription(eventDescription)
                .setEventDate(eventDate)
                .setEventTime(Time.valueOf(eventTime))
                .setVenueId(venueId)
                .build();
    }
}
