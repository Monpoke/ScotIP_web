package scotip.web.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name="DialplanConfig", urlPatterns = "/switch/dialplan/config")
public class DialplanConfig extends App {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        render("pages/switchboard/config.twig", req, resp);
    }


}
