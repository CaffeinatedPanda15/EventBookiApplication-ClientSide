

package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Booking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;


public class BookingFactory {

    public static Booking createBooking(long customerID, long ticketID, LocalDateTime bookingDate,
                                        String status) {
        if (Helper.isNullorEmpty(String.valueOf(customerID))
                || Helper.isNullorEmpty(String.valueOf(ticketID)) || Helper.isNullorEmpty(status)) {
        }
        return new Booking.Builder()
                .setTicketID((int) ticketID)
                .setBookingDate(bookingDate)
                .setStatus(status)
                .build();


    }

}//end of class


