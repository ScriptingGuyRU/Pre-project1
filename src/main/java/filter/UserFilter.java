package filter;

import entities.User;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = "/admin/*",
        filterName = "UserFilter",
        description = "Filtr to authorization")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("Зашел в фильтр");
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        String servletPath = req.getServletPath();

//        if (servletPath.contains("/login")) {
//            filterChain.doFilter(servletRequest,servletResponse);
//            return;
//        }

        String userRole = (String) session.getAttribute("role");

        if (userRole.equals("admin")) {
            filterChain.doFilter(servletRequest,servletResponse);
        }

        if (userRole.equals("user")){
            resp.sendRedirect("/user/");
        }

        if (userRole == null) {
            resp.sendRedirect("/");
        }



//        if (servletPath.contains("/admin")) {
//            if (session.getAttribute("role").equals("admin")){
//                filterChain.doFilter(servletRequest,servletResponse);
//                return;
//            } else {
//                RequestDispatcher rd = req.getRequestDispatcher("/UsersPages/UserMainPager.jsp");
//                rd.forward(servletRequest,servletResponse);
//                return;
//            }
//        }




    }
}
