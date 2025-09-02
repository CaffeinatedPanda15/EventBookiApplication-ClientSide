package za.ac.cput.domain.endusers;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "fullname")
    private String fullName;


    @Column(name = "emailaddress")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "usertype")
    private String userType;

    @Column(name = "address")
    private String address;

    @Column(name = "contactnumber")
    private String contactNumber;

    public Customer() {

    }

    private Customer(Builder builder) {
        this.userName = builder.userName;
        this.fullName = builder.fullName;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.userType = builder.userType;
        this.address = builder.address;
        this.contactNumber = builder.contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", Fullname='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String userName;
        private String fullName;
        private String emailAddress;
        private String password;
        private String userType;
        private String address;
        private String contactNumber;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUserType(String userType) {
            this.userType = userType;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }


        public Customer build() {
            return new Customer(this);
        }

        public Builder copy(Customer customer) {
            this.userName = customer.userName;
            this.fullName = customer.fullName;
            this.emailAddress = customer.emailAddress;
            this.password = customer.password;
            this.userType = customer.userType;
            this.address = customer.address;
            this.contactNumber = customer.contactNumber;
            return this;
        }
    }



}
