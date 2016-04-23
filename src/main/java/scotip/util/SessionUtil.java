package scotip.util;

import scotip.fixtures.CompanyFixtures;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Pierre on 22/04/2016.
 */
public abstract class SessionUtil {

    /**
     * Manage a session.
     * @param req
     */
    public static void manage(HttpServletRequest req) {
        HttpSession session = req.getSession();

    }

    /**
     * Create one security token for the page.
     *
     * @param session
     * @return
     */
    public static String createSecurityToken(HttpSession session) {
        ArrayList<String> tokens = (ArrayList) session.getAttribute("security.tokens"); // create 1 token per page and disable it when used
        if (tokens == null) {
            tokens = new ArrayList<>();
        }

        String gen = RandomUtil.salt(10);

        tokens.add(gen);
        int limitSave=5;
        if (tokens.size() > limitSave) {

            for (int i = 0, t = tokens.size(); (i + limitSave) < t; i++) {
                tokens.remove(i);
            }
        }

        session.setAttribute("security.tokens", tokens);
        return gen;
    }


    /**
     * Returns a list of tokens.
     *
     * @param session
     * @return
     */
    public static List<String> getSecurityTokens(HttpSession session) {
        return (List<String>) session.getAttribute("security.tokens");
    }

    /**
     * Checks if a provided token exists
     *
     * @param session
     * @param token
     * @return
     */
    public static boolean isSecurityTokenValid(HttpSession session, String token) {
        List<String> securityTokens = getSecurityTokens(session);
        Iterator<String> iterator = securityTokens.iterator();
        while (iterator.hasNext()) {
            String compareToken = iterator.next();
            if (token.equals(compareToken)) {
                // delete, then return true
                securityTokens.remove(compareToken);
                return true;
            }
        }
        return false;
    }

}
