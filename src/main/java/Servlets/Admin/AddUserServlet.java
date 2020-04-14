package Servlets.Admin;

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

@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/AdminPages/AddUserPage.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (name == null || password == null) {
            resp.sendRedirect("/admin/");
        }

        if (userService.addUser( new User(name, password, role))) {
            resp.setStatus(200);
            resp.sendRedirect("/admin/");
        } else {
            resp.sendRedirect("/admin/");
        }
    }
}
