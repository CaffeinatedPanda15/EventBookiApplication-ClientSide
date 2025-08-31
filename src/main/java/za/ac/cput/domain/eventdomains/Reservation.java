

package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import za.ac.cput.domain.endusers.Customer;

import java.time.LocalDateTime;

@Entity
public class Reservation extends Customer {

    @Id
    private String reservationId;
    private int customerID;
    private int ticketID;
    private LocalDateTime reservationDate;
    private String status;

    public Reservation() {

    }

    public Reservation(Builder builder) {
        this.reservationId = builder.reservationId;
        this.customerID = builder.customerID;
        this.ticketID = builder.ticketID;
        this.reservationDate = builder.reservationDate;
        this.status = builder.status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public int getCustomerID() {
        return customerID;
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
                ", customerID=" + customerID +
                ", ticketID=" + ticketID +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder extends Customer.Builder {
        private String reservationId;
        private int customerID;
        private int ticketID;
        private LocalDateTime reservationDate;
        private String status;


        public Builder setReservationId(String reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder setCustomerID(int customerID) {
            this.customerID = customerID;
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
            this.customerID = reservation.customerID;
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

