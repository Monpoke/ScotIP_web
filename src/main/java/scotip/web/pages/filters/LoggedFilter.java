package scotip.web.pages.filters;

import com.mysql.jdbc.log.Log4JLogger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pierre on 22/04/2016.
 */
@WebFilter(filterName = "LoggedFilter", urlPatterns = "/u/*")
public class LoggedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // REQUEST / RESPONSE
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // check logging
        boolean isLogged = false;

        HttpSession session = request.getSession();

        // is logged
        isLogged = (session!=null &&session.getAttribute("isLogged") != null && (boolean)session.getAttribute("isLogged") == true);

        System.out.println(session.toString());

        if(!isLogged){
            response.sendRedirect("/?needLogin");
        }

        // user
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
