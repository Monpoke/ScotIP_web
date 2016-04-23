package scotip.web.pages.nonLogged;

import scotip.fixtures.CompanyFixtures;
import scotip.web.pages.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
@WebServlet(name = "LoginTest", urlPatterns = "/logintest")
public class LoginTest extends App {

    @Override
    public void init() throws ServletException {
        super.init();
        sidebarMenu = "switchboard";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();

        /**
         * Testing
         */
        // You're logged, but it's wrong mwahaha
        session.setAttribute("isLogged", true);
        session.setAttribute("currentCompany", CompanyFixtures.getSampleCompany());

        resp.sendRedirect("/u/dashboard?logged=1");
    }


}
