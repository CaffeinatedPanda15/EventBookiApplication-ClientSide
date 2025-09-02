package za.ac.cput.factory.endusers;

import za.ac.cput.domain.endusers.Customer;
import za.ac.cput.domain.endusers.UserType;

public class CustomerFactory {

    public static Customer createCustomer(String userName, String fullName, String emailAddress, String password,
                                          String userType, String address, String contactNumber) {
        if (userName == null || fullName == null || emailAddress == null || password == null || address == null || contactNumber == null) {
            return null;

        }
        return new Customer.Builder()
                .setUserName(userName)
                .setFullName(fullName)
                .setEmailAddress(emailAddress)
                .setPassword(password)
                .setUserType(userType)
                .setAddress(address)
                .setContactNumber(contactNumber)
                .build();
    }
}//end of class
