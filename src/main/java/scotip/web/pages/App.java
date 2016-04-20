package scotip.web.pages;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    private String servletName = "noName";


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

        // servlet name
        servletName = "nul name : "+  req.getServletContext().getServletContextName();

        // Provide some values for model
        fillModelValues(model);

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

    private void fillModelValues(Map<String, Object> model) {
        model.put("servletName", servletName);
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
