package scotip.util;

import scotip.fixtures.CompanyFixtures;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pierre on 22/04/2016.
 */
public abstract class SecurityUtil {

    /**
     * Returns true if user is logged.
     *
     * @param session
     * @return
     */
    public static boolean isLogged(HttpSession session) {
        return session != null && session.getAttribute("isLogged") != null && (boolean) session.getAttribute("isLogged") == true;
    }


}
