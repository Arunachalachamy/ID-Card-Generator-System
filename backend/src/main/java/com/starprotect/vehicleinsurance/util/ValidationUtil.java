package com.starprotect.vehicleinsurance.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    
    // Password should contain alphanumeric characters
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$");
    
    // Phone number should be 10 digits
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
    
    // Vehicle number pattern (max 10 characters)
    private static final Pattern VEHICLE_NO_PATTERN = Pattern.compile("^[A-Za-z0-9]{1,10}$");
    
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() > 30 || password.length() < 1) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    public static boolean isValidPhoneNumber(String phoneNo) {
        if (phoneNo == null) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNo).matches();
    }
    
    public static boolean isValidVehicleNo(String vehicleNo) {
        if (vehicleNo == null) {
            return false;
        }
        return VEHICLE_NO_PATTERN.matcher(vehicleNo).matches();
    }
    
    public static boolean isValidName(String name) {
        return name != null && name.length() <= 30 && name.trim().length() > 0;
    }
    
    public static boolean isValidCustomerName(String customerName) {
        return customerName != null && customerName.length() <= 50 && customerName.trim().length() > 0;
    }
    
    public static String getPasswordValidationMessage() {
        return "Password criteria doesn't match, please enter a combination of alphabets and numeric values";
    }
}