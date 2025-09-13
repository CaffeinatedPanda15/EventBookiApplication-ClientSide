package za.ac.cput.domain.endusers;



import java.time.LocalDateTime;


public class Admin {


    private String userName;


    private String fullName;


    private String emailAddress;


    private String password;


    private String status; // ACTIVE, INACTIVE, SUSPENDED


    private LocalDateTime lastLogin;


    private String createdBy; // who created this admin


    private LocalDateTime createdDate;

    public Admin() {}

    private Admin(Builder builder) {
        this.userName = builder.userName;
        this.fullName = builder.fullName;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.status = builder.status;
        this.lastLogin = builder.lastLogin;
        this.createdBy = builder.createdBy;
        this.createdDate = builder.createdDate;
    }


    public String getUserName() { return userName; }
    public String getFullName() { return fullName; }
    public String getEmailAddress() { return emailAddress; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public String getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedDate() { return createdDate; }

    @Override
    public String toString() {
        return "Admin{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", status='" + status + '\'' +
                ", lastLogin=" + lastLogin +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }


    public static class Builder {
        private String userName;
        private String fullName;
        private String emailAddress;
        private String password;
        private String status;
        private LocalDateTime lastLogin;
        private String createdBy;
        private LocalDateTime createdDate;

        public Builder setUserName(String userName) { this.userName = userName; return this; }
        public Builder setFullName(String fullName) { this.fullName = fullName; return this; }
        public Builder setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; return this; }
        public Builder setCreatedBy(String createdBy) { this.createdBy = createdBy; return this; }
        public Builder setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; return this; }

        public Admin build() { return new Admin(this); }

        public Builder copy(Admin admin) {
            this.userName = admin.userName;
            this.fullName = admin.fullName;
            this.emailAddress = admin.emailAddress;
            this.password = admin.password;
            this.status = admin.status;
            this.lastLogin = admin.lastLogin;
            this.createdBy = admin.createdBy;
            this.createdDate = admin.createdDate;
            return this;
        }
    }//end of builder
}//end of class
