package scotip.web.pages;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.http.HttpServlet;
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
     * Renders a Twig template.
     *
     * @param template The filename to render.
     * @param resp     The response class
     */
    protected void render(String template, HttpServletResponse resp) {
        this.render(template, resp, new HashMap<>());
    }

    /**
     * Renders a Twig template.
     *
     * @param templatePath The filename to render.
     * @param resp         The response class
     * @param model        The variable to render inside the template
     */
    protected void render(String templatePath, HttpServletResponse resp, Map<String,Object> model) {


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
     * Print basics headers
     *
     * @param resp
     */

    private void printHeaders(HttpServletResponse resp) {
        resp.addHeader("Content-type", "text/html; charset=UTF-8");
    }

}
