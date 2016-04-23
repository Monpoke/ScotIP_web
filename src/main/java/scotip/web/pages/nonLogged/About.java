package scotip.web.pages.nonLogged;

import scotip.infos.AlertMessage;
import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "Home", urlPatterns = "/about") // yes, it is for serving "/"
public class About extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "about";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        render("pages/static/about.twig", req, resp);
    }


}
