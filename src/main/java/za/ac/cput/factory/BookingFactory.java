package za.ac.cput.factory;
import za.ac.cput.domain.eventdomains.Booking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;


public class BookingFactory {
    public static  Booking createBooking(String bookingID,int customerID,
                                         int ticketID, LocalDateTime bookingDate,
                                         String status){
        if (Helper.isNullorEmpty(bookingID) || Helper.isZeroOrNull(customerID)
                || Helper.isZeroOrNull(ticketID) || Helper.isNullorEmpty(status))

            return null;

        return new Booking.Builder().setBookingID(bookingID)
                .setCustomerID(customerID).setTicketID(ticketID)
                .setBookingDate(bookingDate).setStatus(status).build();


    }





}
