package za.ac.cput.factory.eventfactories;


import za.ac.cput.domain.eventdomains.Event;

import java.sql.Time;

public class EventFactory {
   public static Event createEvent(String eventName, String eventDate, Time eventTime, String eventLocation, String eventDescription) {
         if (eventName == null || eventDate == null || eventTime == null || eventLocation == null || eventDescription == null) {
                return null;

         }
       return new Event.Builder()
               .setEventName(eventName)
               .setEventDate(eventDate)
               .setEventTime(eventTime)
               .setEventLocation(eventLocation)
               .setEventDescription(eventDescription)
               .build();

   }
    }//end of class
