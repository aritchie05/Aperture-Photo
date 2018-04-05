package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * Author: Joel Murach and Michael Urban
 * Source: Murach's Java Servlets and JSP 3rd Edition
 * Publisher: Shroff Publishers & Distributors
 * Utility class to hash and salt passwords.
 */
public class PasswordUtil {
    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b: mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String hashAndSaltPassword(String password, String salt) throws NoSuchAlgorithmException {
        return hashPassword(password + salt);
    }

    public static String generateRandomPassword() {
        Random r = new SecureRandom();
        byte[] newPassword = new byte[64];
        r.nextBytes(newPassword);
        return Base64.getEncoder().encodeToString(newPassword);
    }
}
