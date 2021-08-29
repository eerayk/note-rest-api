package com.example.noterestapi.util;

import com.example.noterestapi.dto.NoteDto;
import com.example.noterestapi.model.Note;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class util {

    /**
     * Helper method to prepare hashed password.
     * @param strToHash String that will be hashed.
     * @return String Hashed password.
     */
    public static String hashSha256(String strToHash)
    {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswd = md.digest(strToHash.getBytes(StandardCharsets.UTF_8));

            BigInteger number = new BigInteger(1, hashedPasswd);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32)
            {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
            return "Error occured.";
        }
    }
}
