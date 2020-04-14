package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = "/loginFilter/",
//        filterName = "loginFilter4",
//        description = "filter redirects by role")
@WebFilter("/loginFilter/")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        String userRole = (String) session.getAttribute("role");


        if (userRole.equals("user")){
            resp.sendRedirect("/user/");
        }

        if (userRole.equals("admin")){
            resp.sendRedirect("/admin/");
        }

        if (userRole == null) {
            resp.sendRedirect("/");
        }


    }
}
