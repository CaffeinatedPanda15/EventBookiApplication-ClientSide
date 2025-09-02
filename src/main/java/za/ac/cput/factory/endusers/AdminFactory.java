package za.ac.cput.factory.endusers;

import za.ac.cput.domain.endusers.Customer;

public class AdminFactory {
    public static Customer createAdmin(String userName, String fullname, String emailAddress, String password, String userType, String address, String contactNumber) {
        if (userName == null || fullname == null || emailAddress == null || password == null || address == null || contactNumber == null) {
            return null;
        }
        return new Customer.Builder()
                .setUserName(userName)
                .setFullName(fullname)
                .setEmailAddress(emailAddress)
                .setPassword(password)
                .setUserType(userType)
                .setAddress(address)
                .setContactNumber(contactNumber)
                .build();
    }
}//end of class
