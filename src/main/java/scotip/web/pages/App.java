package scotip.web.pages;

import com.lyncode.jtwig.JtwigModelMap;
import com.lyncode.jtwig.JtwigTemplate;
import com.lyncode.jtwig.configuration.JtwigConfiguration;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        this.render(template, resp, new JtwigModelMap());
    }

    /**
     * Renders a Twig template.
     *
     * @param templatePath  The filename to render.
     * @param resp      The response class
     * @param model The variable to render inside the template
     */
    protected void render(String templatePath, HttpServletResponse resp, JtwigModelMap model) {


        JtwigConfiguration config = new JtwigConfiguration();

        // resource
        ClasspathJtwigResource cp = new ClasspathJtwigResource(templatePath);

        JtwigTemplate template = new JtwigTemplate(cp, config);

        String out;

        try {
            out = template.output(model);
        } catch (ParseException e) {
            e.printStackTrace();
            out = "Couldn't parse template: " + e.getMessage();
        } catch (CompileException e) {
            e.printStackTrace();
            out = "Couldn't compile template: " + e.getMessage();
        } catch (RenderException e) {
            e.printStackTrace();
            out = "Couldn't render template: " + e.getMessage();
        }

        /**
         * Print headers
         */
        this.printHeaders(resp);

        try {
            resp.getWriter().println(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Print basics headers
     * @param resp
     */
    private void printHeaders(HttpServletResponse resp) {
        resp.addHeader("Content-type", "text/html; charset=UTF-8");
    }

}
