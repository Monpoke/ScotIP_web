package scotip.web.pages.logged;

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
@WebServlet(name = "Dashboard", urlPatterns = {"/u/dashboard"}) // yes, it is for serving "/"
public class Dashboard extends AppLogged {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "home";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);

        // Model
        Map<String, Object> model = new HashMap<>();


        // Welcome back.
        if(req.getParameter("logged") != null){
            model.put("pageSpecialAlert", new AlertMessage("success","Welcome back!"));
        }

        render("pages/home/index.twig", req, resp, model);
    }


}
