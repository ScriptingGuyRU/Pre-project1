package Servlets.Admin;

import exception.DBException;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/AdminPages/DeleteUserPage.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Long id = Long.valueOf(req.getParameter("id"));

            if (userService.delete(id)) {
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
