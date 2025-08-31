package za.ac.cput.domain.endusers;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

    @Id
    private String userName;
    private String fullname;
    private String emailAddress;
    private String password;
    private UserType userType;


    public User() {
    }

    public  String getUserName() {
        return userName;
    }

    public  String getFullname() {
        return fullname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public  String getPassword() {
        return password;
    }

    public  UserType getUserType() {
        return userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", fullname='" + fullname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}//end of class
