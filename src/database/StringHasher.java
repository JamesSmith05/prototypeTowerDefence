package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHasher {

        public static String getHashValue(String text) {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (digest == null)
                return null;

            byte[] encodedHash = digest.digest(
                    text.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        }

        private static String bytesToHex(byte[] hash) {
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
}
