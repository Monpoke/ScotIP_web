package scotip.web.pages;

import com.lyncode.jtwig.JtwigModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pierre on 18/04/2016.
 */
public class Contact extends App {

    public class Aurelien {
        public String hello(){
            return "AURELIEN HELLO";
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        JtwigModelMap model = new JtwigModelMap();
        model.put("title", "Coucou je teste");
        model.put("aurelien", new Aurelien());


       this.render("pages/home/index.twig", resp, model);

    }


}
