package Servlets.Admin;

import entities.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/")
public class AdminMainServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAllUsers();
        req.setAttribute("users",users);
        RequestDispatcher rd = req.getRequestDispatcher("/AdminPages/AdminMainPage.jsp");
        rd.forward(req,resp);
    }

}
