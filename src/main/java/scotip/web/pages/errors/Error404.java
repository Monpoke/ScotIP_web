package scotip.web.pages.errors;

import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "Error404", urlPatterns = "/errors/404") // yes, it is for serving "/"
public class Error404 extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "error404";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        render("pages/errors/404.twig", req, resp);
    }


}
