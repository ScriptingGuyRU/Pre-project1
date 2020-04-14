package Servlets;

import entities.User;
import exception.DBException;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("LoginPage.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


            String name = req.getParameter("name");
            String password = req.getParameter("password");

            User user = userService.getUserByNameAndPassword(name, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("name",name);
                session.setAttribute("password",password);
                session.setAttribute("role",user.getRole());
                resp.sendRedirect("/loginFilter/");
            } else {
                req.setAttribute("status","Oops, incorrect value");
                resp.setStatus(403);
            }
    }
}
