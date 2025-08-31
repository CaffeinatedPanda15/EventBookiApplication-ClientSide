
package za.ac.cput.domain.eventdomains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import za.ac.cput.domain.endusers.Customer;

import java.time.LocalDateTime;

@Entity
public class Booking extends Customer {

    @Id
    private String bookingId;
    private int ticketID;
    private LocalDateTime bookingDate  ;
    private String status;

    public Booking() {

    }

    public Booking(Builder builder) {
        this.bookingId = builder.bookingID;
        super.setUserName(String.valueOf(builder.customerID));;
        this.ticketID = builder.customerID;
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
                ", customerID=" + getUserName() +
                ", ticketID=" + ticketID +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static class Builder {
        private String bookingID;
        private int customerID;
        private int ticketID;
        private LocalDateTime bookingDate  ;
        private String status;


        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
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
            this.customerID = customerID;
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
