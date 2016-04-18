import com.lyncode.jtwig.JtwigModelMap;
import com.lyncode.jtwig.JtwigTemplate;
import com.lyncode.jtwig.configuration.JtwigConfiguration;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.exception.RenderException;
import com.lyncode.jtwig.resource.ClasspathJtwigResource;
import com.lyncode.jtwig.resource.JtwigResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
public class Contact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JtwigConfiguration config = new JtwigConfiguration();

        // resource
        ClasspathJtwigResource cp = new ClasspathJtwigResource("base.html");

        JtwigTemplate template = new JtwigTemplate(cp, config);
        JtwigModelMap model = new JtwigModelMap();

        String out = "No output...";

        try {
            out = template.output(model);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (CompileException e) {
            e.printStackTrace();
        } catch (RenderException e) {
            e.printStackTrace();
        }


        resp.getWriter().println(out);


    }
}
