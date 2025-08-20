//Muhammad Siddeeq Rabin
//STUDENT :221084096
//Group kN13

package za.ac.cput.domain;

import java.util.Date;

public class Payment {

    private int paymentID;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private Status status;

    public Payment() {

    }


    public Payment(int paymentID, double amount, Date paymentDate, String paymentMethod, Status status) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public enum Status {
        Successful, Failed, Pending
    }

    private Payment(Builder builder) {
        this.paymentID = builder.paymentID;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;

    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {
        private int paymentID;
        private double amount;
        private Date paymentDate;
        private String paymentMethod;
        private Status status;

        public Builder withPaymentID(int paymentID) {
            this.paymentID = paymentID;
            return this;
        }

        public Builder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder withPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder withPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder copy(Payment payment) {
            this.paymentID = payment.getPaymentID();
            this.amount = payment.getAmount();
            this.paymentDate = payment.getPaymentDate();
            this.paymentMethod = payment.getPaymentMethod();
            this.status = payment.getStatus();
            return this;
        }

        public Payment build() {
            return new Payment(this);

        }

    }
}