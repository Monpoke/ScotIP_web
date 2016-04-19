package scotip.web.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
public class DialplanConfig extends App {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        render("pages/switchboard/config.twig", resp);
    }


}
