package scotip.web.pages.nonLogged;

import scotip.infos.AlertMessage;
import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "Home", urlPatterns = {""}) // yes, it is for serving "/"
public class Welcome extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "home";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        // Model
        Map<String, Object> model = new HashMap<>();


        // Welcome back.
        if (req.getParameter("logout") != null) {
            model.put("pageSpecialAlert", new AlertMessage("danger", "You have been logout."));
        }else if (req.getParameter("needLogin") != null) {
            model.put("pageSpecialAlert", new AlertMessage("danger", "You have to be logged to view this page."));
        }

        render("pages/nonLogged/welcome.twig", req, resp, model);
    }


}
