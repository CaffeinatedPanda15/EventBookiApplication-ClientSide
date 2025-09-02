

package za.ac.cput.factory.eventfactories;

import za.ac.cput.domain.eventdomains.Reservation;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class ReservationFactory {
    public static Reservation createReservation(String reservationId,
                                                int ticketID, LocalDateTime reservationDate,
                                                String status) {
        if (Helper.isNullorEmpty(reservationId)
                || Helper.isZeroOrNull(ticketID) || Helper.isNullorEmpty(status)) {
            return null;
        }

        return new Reservation.Builder()
                .setReservationId(reservationId)
                .setTicketID(ticketID)
                .setReservationDate(reservationDate)
                .setStatus(status)
                .build();
    }
}

