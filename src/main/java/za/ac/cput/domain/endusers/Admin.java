package za.ac.cput.domain.endusers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "fullname")
    private String fullname;

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

    public Admin() {
    }

    private Admin(Builder build) {
        this.userName = build.userName;
        this.fullname = build.fullname;
        this.emailAddress = build.emailAddress;
        this.password = build.password;
        this.userType = build.userType;
        this.address = build.address;
        this.contactNumber = build.contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullname() {
        return fullname;
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
        return "Admin{" +
                "userName='" + userName + '\'' +
                ", fullname='" + fullname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String userName;
        private String fullname;
        private String emailAddress;
        private String password;
        private String userType;
        private String address;
        private String contactNumber;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFullname(String fullname) {
            this.fullname = fullname;
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

        public Admin build() {
            return new Admin(this);
        }

        public Builder copy(Admin admin) {
            this.userName = admin.getUserName();
            this.fullname = admin.getFullname();
            this.emailAddress = admin.getEmailAddress();
            this.password = admin.getPassword();
            this.userType = admin.getUserType();
            this.address = admin.getAddress();
            this.contactNumber = admin.getContactNumber();
            return this;
        }
    }
}
