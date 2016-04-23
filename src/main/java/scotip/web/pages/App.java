package scotip.web.pages;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import scotip.util.SecurityUtil;
import scotip.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pierre on 18/04/2016.
 */
public class App extends HttpServlet {

    /**
     * Contains the called servlet.
     */
    protected String sidebarMenu = "noName";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        manageSessions(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        manageSessions(req);
    }

    /**
     * Manages the sessions
     *
     * @param req
     */
    private void manageSessions(HttpServletRequest req) {
        SessionUtil.manage(req);
    }

    /**
     * Renders a Twig template.
     *
     * @param template The filename to render.
     * @param req      The request class
     * @param resp     The response class
     */
    protected void render(String template, HttpServletRequest req, HttpServletResponse resp) {
        this.render(template, req, resp, new HashMap<>());
    }

    /**
     * Renders a Twig template.
     *
     * @param templatePath The filename to render.
     * @param req          The request class
     * @param resp         The response class
     * @param model        The variable to render inside the template
     */
    protected void render(String templatePath, HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {

        // Provide some values for model
        fillModelValues(req, model);

        try {

            PebbleEngine engine = new PebbleEngine.Builder().build();
            PebbleTemplate compiledTemplate = null;
            compiledTemplate = engine.getTemplate(templatePath);


            Writer writer = new StringWriter();
            compiledTemplate.evaluate(writer, model);

            // print some headers
            printHeaders(resp);

            // print template
            resp.getWriter().println(writer.toString());


        } catch (PebbleException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Fill some variables.
     *
     * @param req
     * @param model
     */
    private void fillModelValues(HttpServletRequest req, Map<String, Object> model) {
        /**
         * Gives the current visited page.
         */
        model.put("sidebarMenu", sidebarMenu);

        /**
         * Puts the method defaults.
         */
        if (!model.containsKey("POST")) {
            model.put("POST", new HashMap<>());
        }

        /**
         * Security manager
         */
        model.put("isLogged", SecurityUtil.isLogged(req.getSession()));
    }

    /**
     * Print basics headers
     *
     * @param resp
     */
    private void printHeaders(HttpServletResponse resp) {
        resp.addHeader("Content-type", "text/html; charset=UTF-8");
    }


}
