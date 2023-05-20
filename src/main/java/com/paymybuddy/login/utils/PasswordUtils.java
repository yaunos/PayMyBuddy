package com.paymybuddy.login.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static boolean comparePassword(String plainPassword, String hashedPassword)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(plainPassword.getBytes());
            String hashedPasswordToCheck = bytesToHex(hashedBytes);

            return hashedPasswordToCheck.equals(hashedPassword);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes)
        {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
