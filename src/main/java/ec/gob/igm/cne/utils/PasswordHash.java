/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author desarrollo
 */
public class PasswordHash {

    public static final String SALT = "ec-gob-igm-cne-utils";

    public static String hash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }
        return hash.toString();
    }

    public static boolean verificar(char[] inputPass, String storedPass) {
        String saltedPassword = SALT + String.valueOf(inputPass);
        String hashedPassword = hash(saltedPassword);
        if (hashedPassword.equals(storedPass)) {
            return true;
        } else {
            return false;
        }        
    }
}
