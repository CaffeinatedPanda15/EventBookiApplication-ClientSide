package za.ac.cput.domain.endusers;
/*

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email", unique = true)
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "usertype")
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
*/