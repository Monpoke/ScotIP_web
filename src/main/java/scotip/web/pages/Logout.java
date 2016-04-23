package scotip.web.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name="Logout", urlPatterns = "/logout")
public class Logout extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "switchboard";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession() != null){
            req.getSession().invalidate();
            resp.sendRedirect("/?logout=1");
        }

    }


}
