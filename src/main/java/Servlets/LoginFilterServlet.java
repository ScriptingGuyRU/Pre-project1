package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginFilter/")
public class LoginFilterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

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
