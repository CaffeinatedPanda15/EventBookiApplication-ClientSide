

package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    private String reservationId;
    private int ticketID;
    private LocalDateTime reservationDate;
    private String status;

    public Reservation() {

    }

    public Reservation(Builder builder) {
        this.reservationId = builder.reservationId;
        this.ticketID = builder.ticketID;
        this.reservationDate = builder.reservationDate;
        this.status = builder.status;
    }

    public String getReservationId() {
        return reservationId;
    }


    public int getTicketID() {
        return ticketID;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void confirmReservation() {
        this.status = "Confirmed";
    }

    public void cancelReservation() {
        this.status = "Canceled";
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", ticketID=" + ticketID +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private String reservationId;
        private int ticketID;
        private LocalDateTime reservationDate;
        private String status;


        public Builder setReservationId(String reservationId) {
            this.reservationId = reservationId;
            return this;
        }


        public Builder setTicketID(int ticketID) {
            this.ticketID = ticketID;
            return this;
        }

        public Builder setReservationDate(LocalDateTime reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Reservation reservation) {
            this.reservationId = reservation.reservationId;
            this.ticketID = reservation.ticketID;
            this.reservationDate = reservation.reservationDate;
            this.status = reservation.status;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}

