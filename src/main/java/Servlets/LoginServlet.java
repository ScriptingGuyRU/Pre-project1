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

        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            if (name == null || password == null) {
                throw new DBException();
            }

            User user = userService.getUserByNameAndPassword(name, password);

            if (user == null) { //Если нет такого юзера, то метод вернет null
                throw new DBException();
            }

            if (user.getRole().equals("admin")) {
                resp.setStatus(200);
                resp.sendRedirect("/admin/");
            }

            if (user.getRole().equals("user")) {
                resp.setStatus(200);
                resp.sendRedirect("/user/");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("status","Oops, incorrect value");
            resp.setStatus(403);
        }
    }
}
