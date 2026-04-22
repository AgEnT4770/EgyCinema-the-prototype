package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 
import java.security.SecureRandom;

public class UserAuth {

    public static byte[] passSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);//Generates a random salt using a secure random number generator
        return salt;
    }
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt); //Adds salt to hash first
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); //Hashes the password using SHA-256
            StringBuilder sb = new StringBuilder(); //Takes the salted hash and convert into hex string
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));//Formats each byte as a two-digit hexadecimal string
            }
            return sb.toString(); //Returns the salted hash as a hex string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verifyPassword(String password, byte[] salt, String hashedPassword) {
        String hashedInput = hashPassword(password, salt);//Hashes the input password with the same salt
        return hashedInput.equals(hashedPassword);//Compares the hashed input password with the stored hashed password
    }

    public static void main(String[] args) {
            String password = "mySecurePassword";
            byte[] salt = passSalt();
            String hashedPassword = UserAuth.hashPassword(password, salt);
            System.out.println("Original Password: " + password);
            System.out.println("Hashed Password: " + hashedPassword);
}
}