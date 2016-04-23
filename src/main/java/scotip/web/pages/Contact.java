package scotip.web.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "Home", urlPatterns = "") // yes, it is for serving "/"
public class Contact extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "home";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);

        //render("pages/home/index.twig", req, resp);
    }


}
