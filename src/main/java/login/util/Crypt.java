package login.util;

import org.mindrot.jbcrypt.BCrypt;

public class Crypt {
    public static String encryptPassword(String plainPW) {
        return BCrypt.hashpw(plainPW, BCrypt.gensalt());
    }

    public static Boolean decryptPassword(String plainPW, String encryptPW) {
        return BCrypt.checkpw(plainPW, encryptPW);
    }
}
