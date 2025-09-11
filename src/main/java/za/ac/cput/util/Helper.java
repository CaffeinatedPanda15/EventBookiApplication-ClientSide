package za.ac.cput.util;

import java.util.regex.Pattern;

public class Helper {

   
    // Null & Empty checks
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isZeroOrNull(Integer i) {
        return i == null || i == 0;
    }

    
    // Email validation 
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;

        // Basic regex for email validation
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    
    // Postal code validation (1000–9999)
    public static boolean isValidPostalCode(Integer postalCode) {
        if (postalCode == null) return false;
        return postalCode >= 1000 && postalCode <= 9999;
    }


    // Street number validation (1–99999)
    public static boolean isValidStreetNumber(Integer streetNumber) {
        if (streetNumber == null) return false;
        return streetNumber >= 1 && streetNumber <= 99999;
    }


    // Erf number validation (1–99999)
    public static boolean isValidErfNumber(Integer erfNumber) {
        if (erfNumber == null) return false;
        return erfNumber >= 1 && erfNumber <= 99999;
    }


    // Unit number validation (1–99999)
    public static boolean isValidUnitNumber(Integer unitNumber) {
        if (unitNumber == null) return false;
        return unitNumber >= 1 && unitNumber <= 99999;
    }


    // Phone number validation (10 digits, starts with 0)
    public static boolean isValidPhoneNumber(String phone) {
        if (isNullOrEmpty(phone)) return false;

        // South African format: 0XXXXXXXXX (10 digits)
        String regex = "^0\\d{9}$";
        return Pattern.matches(regex, phone);
    }


    // Name validation (letters + spaces only)
    public static boolean isValidName(String name) {
        if (isNullOrEmpty(name)) return false;

        String regex = "^[A-Za-z ]+$";
        return Pattern.matches(regex, name.trim());
    }


    // ID number validation (South African 13-digit)
    public static boolean isValidSouthAfricanID(String id) {
        if (isNullOrEmpty(id)) return false;

        String regex = "^\\d{13}$";
        return Pattern.matches(regex, id);
    }
}
