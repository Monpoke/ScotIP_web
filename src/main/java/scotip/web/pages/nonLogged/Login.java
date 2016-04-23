package scotip.web.pages.nonLogged;

import scotip.dao.CompanyDAO;
import scotip.entities.Company;
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);

        resp.addHeader("Content-type","text/html; charset=utf-8");

        CompanyDAO dao = new CompanyDAO();

        String login = req.getParameter("mail");
        String pass = req.getParameter("pass");
        Company comp = dao.getCompany(login);

        /**
         * Check password
         */
        if (comp.isGoodPassword(pass)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("isLogged", true);
            session.setAttribute("company", comp);
            resp.getWriter().print("ok");
        } else {
            resp.getWriter().print("error");
        }
    }

}
