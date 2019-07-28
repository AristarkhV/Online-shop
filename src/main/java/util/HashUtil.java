package util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final Logger logger = Logger.getLogger (HashUtil.class);

    public static String getSHA256SecurePassword(String passwordToHash) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance ("SHA-256");
            byte[] encodedhash = digest.digest (passwordToHash.getBytes (StandardCharsets.UTF_8));
            return bytesToHex (encodedhash);
        } catch (NoSuchAlgorithmException e) {
            logger.error ("Can't hash password", e);
        }
        return null;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer ( );
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString (0xff & hash[i]);
            if (hex.length ( ) == 1) hexString.append ('0');
            hexString.append (hex);
        }
        return hexString.toString ( );
    }
}
