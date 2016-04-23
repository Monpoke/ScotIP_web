package scotip.web.pages.nonLogged;

import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "login";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
        render("pages/static/construction.twig", req, resp);
    }


}
