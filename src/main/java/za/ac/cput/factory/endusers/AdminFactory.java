package za.ac.cput.factory.endusers;


import za.ac.cput.domain.endusers.Admin;

import java.time.LocalDateTime;

public class AdminFactory {
   public static Admin createAdmin(String userName, String fullName, String emailAddress, String password){
       //validation
       if(userName == null || userName.isEmpty()){
           throw new IllegalArgumentException("Invalid username");
       }
       if(fullName == null || fullName.isEmpty()){
           throw new IllegalArgumentException("Invalid full name");
       }
       if(emailAddress == null || emailAddress.isEmpty() || !emailAddress.contains("@")){
           throw new IllegalArgumentException("Invalid email address");
       }
       if(password == null || password.length() < 6){
           throw new IllegalArgumentException("Password must be at least 6 characters long");
       }



       return new Admin.Builder()
               .setUserName(userName)
               .setFullName(fullName)
               .setEmailAddress(emailAddress)
               .setPassword(password)
               .setCreatedDate(LocalDateTime.now())
               .build();
    }
}//end of class
