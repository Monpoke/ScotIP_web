package scotip.util;

import java.util.Random;

/**
 * Created by Pierre on 22/04/2016.
 */
public class RandomUtil {

    /**
     * Returns a random string.
     *
     * @return
     */
    public static String salt() {
        return salt(20);
    }

    /**
     * Returns a random string.
     *
     * @param nb
     * @return
     */
    public static String salt(int nb) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < nb) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
