package Servlets;

import entities.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = UserService.getAllUsers();
        req.setAttribute("users",users);
        RequestDispatcher rd = req.getRequestDispatcher("DeleteUserPage.jsp");
        rd.forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User (name,password);

        if (UserService.delete(user)) {
            req.setAttribute("result","Succses");
        } else {
            req.setAttribute("result","Oops, there was an error somewhere");
        }


        //редирект.
        List<User> users = UserService.getAllUsers();
        req.setAttribute("users",users);
        RequestDispatcher rd = req.getRequestDispatcher("DeleteUserPage.jsp");
        rd.forward(req,resp);
    }
}
