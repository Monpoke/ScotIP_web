package scotip.web.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 20/04/2016.
 */
@WebServlet(name="SwitchboardChoose", urlPatterns = "/u/switch/choose")
public class SwitchboardChoose extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "switchboard";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        render("pages/switchboard/choose.twig", req, resp);
    }


}
