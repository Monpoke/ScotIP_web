package scotip.fixtures;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 21/04/2016.
 */
@WebServlet(name = "LoadFixtures",urlPatterns = "/fixtures/load")
public class FixturesLoader extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        // called only one time

        new LinesFixtures();
        new CompanyFixtures();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Fixtures loaded on init...");
    }
}
