
package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    private String bookingId;
    private int ticketID;
    private LocalDateTime bookingDate  ;
    private String status;

    public Booking() {

    }

    public Booking(Builder builder) {
        this.bookingId = builder.bookingID;
        this.bookingDate = builder.bookingDate;
        this.status = builder.status;
    }

    public String getBookingId() {
        return bookingId;
    }


    public int getTicketID() {
        return ticketID;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                ", ticketID=" + ticketID +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private String bookingID;
        private int ticketID;
        private LocalDateTime bookingDate  ;
        private String status;


        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }


        public Builder setTicketID(int ticketID) {
            this.ticketID = ticketID;
            return this;
        }

        public Builder setBookingDate(LocalDateTime bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Booking booking) {
            this.bookingID = bookingID;
            this.ticketID = ticketID;
            this.bookingDate = bookingDate;
            this.status = status;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}//end of class
