package Servlets.Admin;

import dao.UserDAO;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet("/admin/editUser")
public class EditServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/AdminPages/EditUserPage.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

            if (id == null || name == null || password == null){
                throw new DBException();
            }

            User user = userService.getUserById(id);

            if (userService.editUser(user, name, password, role)) {
                resp.setStatus(200);
                resp.sendRedirect("/admin/");
            } else {
                throw new DBException();
            }

        } catch (DBException|NumberFormatException e) {
            resp.setStatus(403);
            resp.sendRedirect("/admin/");
        }
    }
}
