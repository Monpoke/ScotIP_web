package scotip.web.pages.nonLogged;

import scotip.infos.AlertMessage;
import scotip.infos.PricingPlan;
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
@WebServlet(name = "Princings", urlPatterns = "/pricings") // yes, it is for serving "/"
public class Pricings extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "pricings";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        // Model
        Map<String, Object> model = new HashMap<>();
        model.put("plans", PricingPlan.getCurrentPlans());

        render("pages/static/prices.twig", req, resp, model);
    }


}
